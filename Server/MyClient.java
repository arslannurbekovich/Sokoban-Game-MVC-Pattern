import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyClient extends Thread {
    private Socket socket;
    public static String level;
    public MyClient(Socket socket) {
        this.socket = socket;
        level = "";
    }
    public void run() {
        try {
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader buffer = new BufferedReader(in);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            level = buffer.readLine();
            Desktop desktop = new Desktop();
            out.writeObject(desktop.getDesktop());
            socket.close();
        } catch(IOException ioe) {
            System.out.println("Error " + ioe);
        }
    }
}
