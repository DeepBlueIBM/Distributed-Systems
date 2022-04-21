import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    private Broker newBroker;

    Server(Broker newBroker){
        this.newBroker=newBroker;
    }

    ServerSocket providerSocket;

    public void run() {

        Socket connection = null;

        try {
            String action = "";
            providerSocket = new ServerSocket(newBroker.port);

            while (true) {
                connection = providerSocket.accept();

                ActionsForClients t = new ActionsForClients(newBroker,action,connection);
                t.start();

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