import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Stream implements Closeable{
    private final Socket socket;
    private final ObjectInputStream reader;
    private final ObjectOutputStream writer;
    public Stream(String ip, int port) { //Client
        try {
            this.socket = new Socket(ip, port);
            this.reader = createReader();
            this.writer = createWriter();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
    public Stream(ServerSocket server) {  //Server
            try {
                this.socket = server.accept();
                this.reader = createReader();
                this.writer = createWriter();
            }catch (IOException e){
                throw new RuntimeException();
            }
    }
    public void writeObg(String message){
        try {
            writer.writeObject(message);
            writer.flush();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
    public String readObj() throws IOException, ClassNotFoundException {
            return (String) reader.readObject();

    }
    private ObjectInputStream createReader() throws IOException {
            return new  ObjectInputStream(socket.getInputStream());

    }
    private ObjectOutputStream createWriter() throws IOException {
            return new ObjectOutputStream(socket.getOutputStream());

    }

    @Override
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}
