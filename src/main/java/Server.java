import model.UserL;
import service.UserLService;

import java.io.*;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.Objects;


public class Server {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(8888)) {
            System.out.println("Server started!");
            while (true) {
                Stream stream = new Stream(server);
                new Thread(() -> {
                    String msg1;
                    String msg2;
                    String msg3;
                    String msg4;
                    try {
                         msg1 = stream.readObj();
                         msg2 = stream.readObj();

                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("client> " + msg1);
                    System.out.println("client> " + msg2);
                    UserLService userService = new UserLService();
                    try {
                        UserL userL = new UserL();
                        userL.setLogin(msg1);
                        userL.setPassword(msg2);
                        userService.add(userL);

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    stream.writeObg("Successfully!");
                    System.out.println("Successfully!");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        msg3 = stream.readObj();
                        msg4 = stream.readObj();

                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("client> " + msg3);
                    System.out.println("client> " + msg4);
                    if (Objects.equals(userService.getLogin(msg3), msg3) && (Objects.equals(userService.getPass(msg4), msg4))) {
                        stream.writeObg("Successfully!");
                        System.out.println("Successfully!");
                    } else {
                        stream.writeObg("Failed! Вам нужно зарегистрироваться");
                        System.out.println("Failed!");
                    }

                }).start();

            }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    }

