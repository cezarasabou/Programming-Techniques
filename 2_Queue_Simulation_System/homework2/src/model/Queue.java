package model;
/**
 * Model class that holds the blocking queue dealing with the clients
 * it's only purpose is to receive clients and remove them after service time is over
 * @author Cezara
 */

import controller.QueueSimulation;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Queue extends Thread{
    private BlockingQueue<Client> queue = new ArrayBlockingQueue<>(1000);
    private boolean isActiveQueue;
    private int queueId;
    private int averageWaitingTime = 0;
    private int totalNumberOfClients = 0, totalWaitingTime = 0;
    Logger LOGGER = Logger.getLogger(Queue.class .getName());

    public int getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void setAverageWaitingTime(int averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public Queue(int id){
        this.queueId = id;
    }

    public int getQueueId() {
        return queueId;
    }

    public boolean isActiveQueue() {
        return isActiveQueue;
    }

    public void setActiveQueue(boolean activeQueue) {
        this.isActiveQueue = activeQueue;
    }

    public void addClient(Client c){
        queue.add(c);
    }
    public boolean checkNotEmptyQueue(){
        if(queue.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * overriden method takes current client, computes the new average waiting value and then waits
     * the average waiting time is calculated by the sum of total service times over the number of clients
     */
    @Override
    public void run(){
        while(isActiveQueue ){
            try{
                Client c = queue.take();
                LOGGER.info("took out c"+c.getId());
                totalNumberOfClients++;
                totalWaitingTime += c.getServiceTime();
                this.setAverageWaitingTime(totalWaitingTime/totalNumberOfClients);
                c.setFinishTime(QueueSimulation.getApplicationTime());
                sleep(c.getServiceTime()*1000);
            }
            catch (InterruptedException e){
                System.out.println("error in extracting client from Queue");
            }
        }
    }

    public int getWaitingTime(){
        int res = 0;
        for(Client c: queue){
            res += c.getServiceTime();
        }
        return res;
    }

    public void  printQueue(){
        for(Client c: queue){
            System.out.print("c"+c.getId()+" ");
        }
        ///System.out.println();
    }

    /**
     * toString holds the id of the client in order to be printed
     * @returns string resulted after appending each client to the existing queue
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Client c: queue){
            result.append("C").append(c.getId()).append(" ");
        }

        return result.toString();
    }
}
