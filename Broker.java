import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Broker {

    String brokerID;
    int port=10000;
    static String IP = "127.0.0.1";
    ArrayList<Broker> brokerList;
    HashMap<String, ArrayList<String>> topics;
    ArrayList<UserNode> registeredPublishers;
    ArrayList<UserNode> registeredConsumers;

    public static void main(String[] args) throws UnknownHostException {

        Broker newBroker = new Broker();

        newBroker.getPort();
        newBroker.topics = new HashMap<String, ArrayList<String>>();
        newBroker.registeredPublishers = new ArrayList<UserNode>();
        newBroker.registeredConsumers = new ArrayList<UserNode>();
        //newBroker.brokerList = newBroker.updateBrokers();
        //Server thread = new Server(newBroker);	//
        //thread.start();

    }

    void getPort() {

        int portNumber=1100;
        int lines = fileRead();

        fileWrite("Broker"+(lines+1)+" "+(portNumber+(100*lines)));

        lines = fileRead();
        System.out.println("lines "+lines);

        brokerID = "Broker"+(lines);
        port = portNumber+(100*(lines-1));

        System.out.println("BrokerID is: "+brokerID+" port is: "+port);
    }

    public int fileRead() {

        int lines=0;
        try {
            File obj=new File("C:\\Users\\giorg\\Desktop\\Dis\\Conf.txt");
            Scanner Reader = new Scanner(obj);
            while(Reader.hasNextLine()) {
                    String processorsString = Reader.nextLine();
                    lines++;
            }
            //System.out.println("lines "+lines);
            Reader.close();
        }catch (IOException e1) {
            System.out.println("An error occurred.");
            e1.printStackTrace();
        }
        finally {
            return lines;
        }
    }

    public void fileWrite(String write) {
        try {
            File obj = new File("C:\\Users\\giorg\\Desktop\\Dis\\Conf.txt");

            FileWriter myWrite = new FileWriter(obj, true);
            int lines = fileRead();
            if(lines == 0){
                myWrite.write(write);
                myWrite.flush();
            }else{
                myWrite.write("\n"+write);
                myWrite.flush();
            }
        } catch (IOException e1) {
            System.out.println("An error occurred.");
            e1.printStackTrace();
        }
    }
}
