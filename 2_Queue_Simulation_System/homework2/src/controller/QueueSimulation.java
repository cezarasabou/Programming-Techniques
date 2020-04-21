package controller;
/**
 * this class plays the role of the controller
 * it links the model and the view and it processes the input
 *
 * @author Cezara
 */

import exception.InvalidInputException;
import gui.DisplayService;
import gui.SimulationView;
import model.Queue;
import model.TaskManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class QueueSimulation extends Thread {

    private static int maxNumberOfQueues = 4;
    private static int minArrivalTime = 1;
    private static int maxArrivalTime = 4;
    private static int minServiceTime = 20;
    private static int maxServiceTime = 30;
    private static int simulationInterval = 100;
    private SimulationView sv;
    private static AtomicInteger timeUnit = new AtomicInteger(0);
    Logger LOGGER = Logger.getLogger(QueueSimulation.class.getName());

    public QueueSimulation(SimulationView sv){
        this.sv = sv;
    }

    public static int getMaxNumberOfQueues() {
        return maxNumberOfQueues;
    }

    public static void setMaxNumberOfQueues(int maxNumberOfQueues) {
        QueueSimulation.maxNumberOfQueues = maxNumberOfQueues;
    }

    public static int getMinArrivalTime() {
        return minArrivalTime;
    }

    public static void setMinArrivalTime(int minArrivalTime) {
        QueueSimulation.minArrivalTime = minArrivalTime;
    }

    public static int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public static void setMaxArrivalTime(int maxArrivalTime) {
        QueueSimulation.maxArrivalTime = maxArrivalTime;
    }

    public static int getMinServiceTime() {
        return minServiceTime;
    }

    public static void setMinServiceTime(int minServiceTime) {
        QueueSimulation.minServiceTime = minServiceTime;
    }

    public static int getMaxServiceTime() {
        return maxServiceTime;
    }

    public static void setMaxServiceTime(int maxServiceTime) {
        QueueSimulation.maxServiceTime = maxServiceTime;
    }

    public static int getSimulationInterval() {
        return simulationInterval;
    }

    public static void setSimulationInterval(int simulationInterval) {
        QueueSimulation.simulationInterval = simulationInterval;
    }


    /**
     * the time is AtomicInteger because it is needed for comparison in diferent threads
     */


    public static int getApplicationTime() {
        return timeUnit.get();
    }

    public static boolean isActiveSimulation() {
        return timeUnit.get() < simulationInterval;
    }

    /**
     * method that checks the input value given from the user in the textfield
     * @param inputValue - textfield value
     * @param inputType - could be : min or max intervals of time, nr of queues, simulation interval
     * @return the integer result of the input, in order to start simulations
     * @throws InvalidInputException -- if bad input, the error message will pop up on the screen
     */
    public int getAndCheckInput(String inputValue, String inputType) throws InvalidInputException{
        int res = 0;

        if(inputType == "numberOfQueues"){
            res = Integer.parseInt(inputValue);
            if(res>10){
                throw new InvalidInputException("Invalid Input! Please enter a queue number less than 10. Thank you!");
            }
        }
        if(inputType == "minArrivalTime"){
            res = Integer.parseInt(inputValue);
        }
        if(inputType == "maxArrivalTime"){
            res = Integer.parseInt(inputValue);
        }
        if(inputType == "minServiceTime"){
            res = Integer.parseInt(inputValue);
        }
        if(inputType == "maxServiceTime"){
            res = Integer.parseInt(inputValue);
        }
        if(inputType == "simulationInterval"){
            res = Integer.parseInt(inputValue);
        }

        return res;
    }

    public String getAverageWaitingTimes(List<Queue> List){
        StringBuilder result = new StringBuilder();
        for(Queue q: List){
            result.append("Q").append(q.getId()).append(" avg: ").append(q.getAverageWaitingTime() + " ");
        }
        return result.toString();
    }

    /**
     * overriden method creates a display service thread and prints actual time
     * @return no return value
     */
    @Override
    public void run() {
        TaskManager taskManager = new TaskManager(minArrivalTime, maxArrivalTime, minServiceTime,
                maxServiceTime, maxNumberOfQueues);
        DisplayService displayService = new DisplayService(taskManager.getClientQueueList(),sv);
        taskManager.start();
        LOGGER.info("Simulation has started");
        displayService.start();

        while (timeUnit.get() < simulationInterval) {
            System.out.println(timeUnit.get());
            timeUnit.incrementAndGet();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error ocurred in queue simulation");
            }
        }


    }

}
