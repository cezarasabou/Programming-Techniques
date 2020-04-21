package model;
/**
 * Model class that simply takes the newly created clients from client factory
 * and distributes them to the queue with the smallest waitingTime
 * @author Cezara
 */

import controller.QueueSimulation;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class TaskManager extends Thread{
    ///el decide de cate queue-uri am nevoie ca in functie de cati clienti se fabrica..
    Logger LOGGER = Logger.getLogger(TaskManager.class.getName());
    private int maxNumberOfQueues;

    private ArrayList<Queue> clientQueueList;
    private BlockingQueue<Client> factoryClientQueue;


    private ClientFactory clientFactory;
    public TaskManager(int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, int maxNumberOfQueues){
        this.maxNumberOfQueues = maxNumberOfQueues;

        clientFactory = new ClientFactory(minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
        clientQueueList = new ArrayList<>();
    }

    public ArrayList<Queue> getClientQueueList() {
        return clientQueueList;
    }

    /**
     * searches smallest queue by waiting time
     * @return the smallest queue in the clientQueueList
     */
    private Queue getSmallestQueue(){
        if(clientQueueList.isEmpty()){
            return null;
        }

        Queue smallestQueue = clientQueueList.get(0);
        for(Queue q : clientQueueList){
            if(q.getWaitingTime() < smallestQueue.getWaitingTime()){
                smallestQueue = q;
            }
        }
        return smallestQueue;
    }

    /**
     * initializes the queues and assigns them  unigue IDs
     */
    private void createInitialClientQueueList(){

        for(int i = 0; i< maxNumberOfQueues; i++){
            Queue q = new Queue(i);
            clientQueueList.add(q);
        }
    }

    /**
     * starts the threads for each queue and displays message
     */
    private void startInitialClientQueues(){
        clientQueueList.forEach(clientQueue-> {
            clientQueue.setActiveQueue(true);
            clientQueue.start();
            LOGGER.info("Started queue number: "+clientQueue.getQueueId());
        });
    }

    private void printFactoryQueueContent(){
        int i = 0;
        for(Client c: factoryClientQueue){
            System.out.print("C" + i + " " + c.getServiceTime() + " ");
            i++;
        }
    }

    /**
     * overriden method that will extract client and add it to smallest queue
     */
    public void run(){
        //get clients from factory and give them away to available queues
        ///System.out.println("running client factory");
        clientFactory.start();
        //System.out.println("print factory content");
        factoryClientQueue = clientFactory.getClientQueue();

//        while(true){
//
//            try{
//
//                sleep(1000);
//
//                printFactoryQueueContent();
//            }
//            catch (InterruptedException e){
//                System.out.println("Error in printing clients");
//            }
//        }

        createInitialClientQueueList();
        startInitialClientQueues();

        while(QueueSimulation.isActiveSimulation()){

            try{
                Client extractedClient = factoryClientQueue.take();
                addClientToQueue(extractedClient);
            }
            catch (InterruptedException e){
                System.out.println("Error in extracting new client");
            }
        }



    }

    /**
     * method that adds client to smallest queue
     * @param c
     */
    private void addClientToQueue(Client c) {
        Queue q = getSmallestQueue();
        if(q != null){
            q.addClient(c);
        }
    }
}
