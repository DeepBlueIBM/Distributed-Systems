import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ActionsForClients extends Thread{

    private Broker broker;
    private String action;
    private Socket socket;

    ActionsForClients(Broker broker,String action,Socket socket ){
        this.broker = broker;
        this.action = action;
        this.socket=socket;
    }

    public void run() {

        try {
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            action = br.readLine();
            //stands for broker list
            System.out.println("Action is: "+action);
            if (action.equals("123")) {
                broker.brokerList = broker.getOtherBrokers();
                System.out.println("[Updated brokerList, " + br.readLine() + " was added]");
            }else if(action.equals("an o xrisths pathsei to 2")){

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}