import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    Socket client = null;
    ObjectInputStream ins = null;
    ObjectOutputStream out = null;
    String  msg1, msg2 = "";
   int port = 8888;
    public static void main(String[] args)  {
        Client socketClient = new Client();
        socketClient.setConnection();

        }
    void setConnection()
    {
        try {
            client = new Socket("127.0.0.1",port);
                    System.out.println("Connected to server");
            out = new ObjectOutputStream(client.getOutputStream());
            out.flush();
            ins = new ObjectInputStream(client.getInputStream());
            do {
                Scanner in = new Scanner(System.in);
                System.out.println("Регистрация:");

                String msg1 = "Введите логин";
                System.out.println(msg1);
                msg1 = in.nextLine();

                String msg2 = "Введите пароль";
                System.out.println(msg2);
                msg2 = in.nextLine();

                if(msg1 == null || msg2 == null){
                    msg1 = "";
                    msg2 ="";
                }
                sendMessage(msg1);
                sendMessage(msg2);
                String response = (String) ins.readObject();
                System.out.println("Server: " + response);

                System.out.println("Вход:");
                String msg3 = "Введите логин";
                System.out.println(msg3);
                msg3 = in.nextLine();

                String msg4 = "Введите пароль";
                System.out.println(msg4);
                msg4 = in.nextLine();

                sendMessage(msg3);
                sendMessage(msg4);
                String responds = (String) ins.readObject();
                System.out.println("Server: " + responds);
                try {

                    msg1 = (String) ins.readObject();
                    msg2 = (String) ins.readObject();
                    msg3 = (String) ins.readObject();
                    msg4 = (String) ins.readObject();

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Client.
                                    class.getName()).
                            log(Level.SEVERE, null, ex);
                }

            } while (!msg1.equals("exit") || !msg2.equals("exit"));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally
        {
            try {
                if(ins != null)
                    ins.close();
                if(out != null)
                    out.close();
                if(client != null)
                    client.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.
                                class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
    void sendMessage(String msg)
    {
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

}



