package module_8_medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Client {

    public static boolean disconnecting = false;
    private static final int TIMEOUT_MS = 30_000;

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 7182;

        try (
                Socket socket = new Socket(serverAddress, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Подключаемся...");

            final Timer timer = new Timer(true);
            final Object lock = new Object();
            final Runnable timeoutAction = () -> {
                System.out.println("Нет активности 30 секунд — закрываем соединение.");
                disconnecting = true;
                try {
                    socket.close();
                } catch (IOException ignored) {}
            };

            TimerTask[] timeoutTask = new TimerTask[1];

            Runnable resetTimer = () -> {
                synchronized (lock) {
                    if (timeoutTask[0] != null) {
                        timeoutTask[0].cancel();
                    }
                    timeoutTask[0] = new TimerTask() {
                        @Override
                        public void run() {
                            timeoutAction.run();
                        }
                    };
                    timer.schedule(timeoutTask[0], TIMEOUT_MS);
                }
            };

            resetTimer.run(); // Старт таймера при подключении

            String clientMessage;
            String serverMessage;

            while (true) {
                System.out.print("Клиент: ");
                clientMessage = consoleInput.readLine();
                out.println(clientMessage);
                resetTimer.run(); // сброс таймера при отправке

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Клиент отключился: " + socket.getInetAddress());
                    break;
                }

                serverMessage = in.readLine();
                if (Objects.isNull(serverMessage) || serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Завершаем соединение...");
                    break;
                }

                resetTimer.run(); // сброс таймера при получении

                if (Client.disconnecting) {
                    System.out.println("30 секунд вышло! Завершаем соединение...");
                    break;
                }

                System.out.println("Сервер: " + serverMessage);
            }

            timer.cancel(); // отключаем таймер при завершении

        } catch (IOException e) {
            if (disconnecting) {
                System.out.println("Соединение закрыто из-за неактивности.");
            } else {
                e.printStackTrace();
            }
        }
    }
}