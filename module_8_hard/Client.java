package module_8_hard;

import java.io.*;
import java.net.*;
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
            String login;
            String password;


            System.out.print("Клиент: Введите логин: ");
            login = consoleInput.readLine();
            out.println(login);
            System.out.print("Клиент: Введите пороль: ");
            password = consoleInput.readLine();
            out.println(password);



            String clientMessage;
            String serverMessage;
            while (true){
//                System.out.print("Клиент: ");
//                clientMessage = consoleInput.readLine();
//                out.println(clientMessage);
//
//                if(clientMessage.equalsIgnoreCase("exit")){
//                    System.out.println("Клиент отключился: " + socket.getInetAddress());
//                    break;
//                }

                serverMessage = in.readLine();
                if(Objects.isNull(serverMessage) || serverMessage.equalsIgnoreCase("exit")){
                    System.out.println("Завершаем соединение...");
                    break;
                }

                System.out.println("Cервер: " + serverMessage);

                System.out.print("Клиент: ");
                clientMessage = consoleInput.readLine();
                out.println(clientMessage);


                if(clientMessage.equalsIgnoreCase("exit")){
                    System.out.println("Клиент отключился: " + socket.getInetAddress());
                    break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
