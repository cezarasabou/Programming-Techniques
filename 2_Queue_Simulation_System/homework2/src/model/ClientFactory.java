package model;
/**
 * Model class that generates clients as long as the simulation is active
 * @author Cezara
 */

import controller.QueueSimulation;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class ClientFactory extends Thread{
   Logger LOGGER = Logger.getLogger(ClientFactory.class.getName());

    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private int clientId = 0;

    private BlockingQueue<Client> clientQueue = new ArrayBlockingQueue<>(1000);

    public ClientFactory(int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime){
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
    }

    ///aici ma gandesc ca iau ceva din task manager

    /**
     * method that generates random service time
     * @return a random service time value in the interval maxServiceTime and minServiceTime
     */
    public int getRandomServiceTime(){
        return new Random().nextInt((maxServiceTime - minServiceTime) + 1) + minServiceTime;
    }

    /**
     * creates new client with a new id and random service and ArrivalTime
     * @return Client object
     */
    public Client createNewClient(){
        getRandomArrivalTime();
        Client c = new Client(QueueSimulation.getApplicationTime(), getRandomServiceTime(), clientId);
        LOGGER.info("Created client "+clientId);
        clientId++;
        return c;
    }

    /**
     * method that generates random arrival time in the interval maxArrivalTime and minArrivalTime
     * @return the random value in the interval mentioned above
     */
    private int getRandomArrivalTime() {
        return new Random().nextInt((maxArrivalTime - minArrivalTime) + 1) + minArrivalTime;
    }

    /**
     * overriden method run that will create a client once per second until the simulation time is over
     * it adds newly created client to the clientQueue list
     * @return No return value
     */
    @Override
    public void run(){
        while(QueueSimulation.isActiveSimulation()){
            clientQueue.add(createNewClient());
            try{
                sleep(getRandomArrivalTime()*1000);
            }
            catch(InterruptedException e){
                System.out.println("Error in ClientFactory Thread");
            }
        }
    }

    public BlockingQueue<Client> getClientQueue(){
        return clientQueue;
    }

}
