package module_8_lecture;

import java.io.*;
import java.net.*;
import java.util.Objects;

public class Server {
    public static void main(String[] args) {
        int port = 7182;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен, ждем соединения...");

            while (true){
                try(Socket clientSocket = serverSocket.accept(); //подключаем клиента
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //читаем данные со стороны клиента
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))){

                    System.out.println("Клиент подключился: " + clientSocket.getInetAddress());
                    String clientMessage;
                    String serverMessage;

                    while(true){
                        clientMessage = in.readLine();
                        if(Objects.isNull(clientMessage) || clientMessage.equalsIgnoreCase("exit")){
                            System.out.println("Клиент отключился");
                        }

                        System.out.println("Клиента: " + clientMessage);
                        System.out.print("Сервер: ");
                        serverMessage = consoleInput.readLine();
                        out.println(serverMessage);
                        if(serverMessage.equalsIgnoreCase("exit")){
                            System.out.println("Завершаем соединение...");
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
