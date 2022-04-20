import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    private Broker newBroker;

    Server(Broker newBroker){
        this.newBroker=newBroker;
    }

    ServerSocket providerSocket;
    Socket connection = null;

    public void run() {
        try {
            providerSocket = new ServerSocket(newBroker.port);

            while (true) {
                connection = providerSocket.accept();

                //Thread t = new ActionsForClients(connection);
                //t.start();

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                providerSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
