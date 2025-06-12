package module_8_medium;

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Server {

    private static final int TIMEOUT_MS = 30_000;

    public static void main(String[] args) {
        int port = 7182;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен, ждем соединения...");

            while (true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
                ) {
                    System.out.println("Клиент подключился: " + clientSocket.getInetAddress());

                    final Timer timer = new Timer(true); // true = daemon
                    final Object lock = new Object(); // нужен для синхронного сброса таймера
                    final Runnable timeoutAction = () -> {
                        System.out.println("Нет активности 30 секунд — закрываем соединение.");
                        try {
                            clientSocket.close();
                        } catch (IOException ignored) {}
                    };

                    TimerTask[] timeoutTask = new TimerTask[1]; // нужен для перезапуска
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

                    // Изначальный запуск таймера
                    resetTimer.run();

                    String clientMessage;
                    String serverMessage;

                    while (true) {
                        clientMessage = in.readLine();
                        if (clientMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                            System.out.println("Клиент отключился");
                            break;
                        }

                        resetTimer.run(); // сбрасываем таймер при получении сообщения

                        if (clientMessage.equalsIgnoreCase("/time")) {
                            LocalTime time = LocalTime.now();
                            serverMessage = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                            out.println(serverMessage);

                        }

                        System.out.println("Клиент: " + clientMessage);
                        System.out.print("Сервер: ");
                        serverMessage = consoleInput.readLine();

                        out.println(serverMessage);
                        resetTimer.run(); // сбрасываем таймер при отправке

                        if (serverMessage.equalsIgnoreCase("exit")) {
                            System.out.println("Завершаем соединение...");
                            break;
                        }
                    }

                    timer.cancel(); // отключаем таймер по завершению
                } catch (IOException e) {
                    System.out.println("Соединение прервано: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}