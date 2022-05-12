import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server {
    private ServerSocket serverSocket;
    private static final Object object = new Object();
    public Server(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch(IOException ioe) {
            System.out.println("Error " + ioe);
        }
    }
    public void go() {
        while(true) {
            synchronized(object) {
                Socket clientSocket;
                try {
                    clientSocket = serverSocket.accept();
            } catch(IOException ioe) {
                System.out.println("Error " + ioe);
                clientSocket = null;
            }
                if(clientSocket != null) {
                    MyClient client = new MyClient(clientSocket);
                    client.start();
                }
            }
        }
    }
}