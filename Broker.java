import java.io.*;
import java.net.Socket;
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

    public static void main(String[] args) {

        Broker newBroker = new Broker();

        newBroker.getPort();
        newBroker.topics = new HashMap<String, ArrayList<String>>();
        newBroker.registeredPublishers = new ArrayList<UserNode>();
        newBroker.registeredConsumers = new ArrayList<UserNode>();
        newBroker.brokerList = newBroker.getOtherBrokers();

        System.out.println("New broker created");
        Server thread = new Server(newBroker);
        thread.start();

            for (Broker diffBroker: newBroker.brokerList) {	//koitaei oloys toys brokers apo thn lista ton broker
                try {

                    Socket socket = new Socket(IP, diffBroker.port);	//kanei syndesh me tous allous brokers pairnontas to Ip

                    System.out.println("Broker connected");
                    OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
                    BufferedWriter bw = new BufferedWriter(osw);
                    bw.write("bl");
                    bw.newLine();
                    bw.write(newBroker.brokerID);

                    //that's it, we are done
                    bw.close();
                    osw.close();
                    socket.close();
                } catch (NumberFormatException | IOException e) {
                    e.printStackTrace();
                }

            }


    }

    void getPort() {

        int portNumber=1100;
        int lines = fileRead();

        fileWrite("Broker"+(lines+1)+" "+(portNumber+(100*lines)));

        lines = fileRead();
        //System.out.println("lines "+lines);

        brokerID = "Broker"+(lines);
        port = portNumber+(100*(lines-1));

        //System.out.println("BrokerID is: "+brokerID+" port is: "+port);
    }

    ArrayList<Broker> getOtherBrokers() {

        ArrayList<Broker> otherBrokers = new ArrayList<Broker>();

        try {
            File obj=new File("C:\\Users\\giorg\\Desktop\\Dis\\Conf.txt");
            Scanner Reader = new Scanner(obj);
            while(Reader.hasNextLine()) {
                String line=Reader.nextLine();
                String[] wordArray = line.split(" ");
                //System.out.println(wordArray[1]);
                if((Integer.valueOf(wordArray[1]) != port)){
                    otherBrokers.add(new Broker());
                    otherBrokers.get(otherBrokers.size()-1).brokerID = wordArray[0];
                    otherBrokers.get(otherBrokers.size()-1).port = Integer.valueOf(wordArray[1]);
                    otherBrokers.get(otherBrokers.size()-1).topics = new HashMap<String, ArrayList<String>>();
                }
            }
        } catch (IOException e1) {
            System.out.println("An error occurred.");
            e1.printStackTrace();
        }
        return otherBrokers;
    }

    void updateTopics(Broker thisBroker){

        for(Broker diffBroker:thisBroker.brokerList){

            try{

                Socket socket = new Socket(diffBroker.IP,diffBroker.port);

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int fileRead() {

        int lines=0;
        try {
            File obj=new File("C:\\Users\\giorg\\Desktop\\Dis\\Conf.txt");
            Scanner Reader = new Scanner(obj);
            while(Reader.hasNextLine()) {
                Reader.nextLine();
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