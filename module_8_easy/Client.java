package module_8_easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class Client {

    public static void main(String[] args) {
        String serverAdress = "127.0.0.1";
        int port = 7182;

        try (Socket socket = new Socket(serverAdress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //читаем данные со стороны сервера
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //отправляем сообщения в сервер
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Подключаемся...");

            String clientMessage;
            String serverMessage;
            while (true){
                System.out.print("Клиент: ");
                clientMessage = consoleInput.readLine();
                out.println(clientMessage);

                if(clientMessage.equalsIgnoreCase("exit")){
                    System.out.println("Клиент отключился: " + socket.getInetAddress());
                    break;
                }

                serverMessage = in.readLine();
                if(Objects.isNull(serverMessage) || serverMessage.equalsIgnoreCase("exit")){
                    System.out.println("Завершаем соединение...");
                    break;
                }

                System.out.println("Cервер: " + serverMessage);
            }


        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
