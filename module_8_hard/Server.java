package module_8_hard;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Objects;

public class Server {

    static HashMap<String, String> passwords = new HashMap<>();

    public static void main(String[] args) {
        passwords.put("user1", "1234");




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



                    String login;
                    String password;



                    login = in.readLine();
                    password = in.readLine();


                    if(passwords.containsKey(login) && password.equals(passwords.get(login))){
                        serverMessage = "Добро пожаловать, " + login;
                        out.println(serverMessage);
                    }

                    while(true){
                        clientMessage = in.readLine();
                        if(Objects.isNull(clientMessage) || clientMessage.equalsIgnoreCase("exit")){
                            System.out.println("Клиент отключился");
                        }

                        System.out.println("Клиент: " + clientMessage);
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

