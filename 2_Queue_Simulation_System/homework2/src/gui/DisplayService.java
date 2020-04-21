package gui;
import model.Queue;
import java.util.List;

/**
 * Displays the existing queues using a thread
 * part of the GUI
 * @author Cezara
 *
 */
public class DisplayService extends Thread {


    SimulationView sv ;
    private List<Queue> queueList;

    public DisplayService(List<Queue> queueList, SimulationView sv){
        this.queueList = queueList;
        this.sv = sv;
    }

    /**
     * public overriden method that displays the queues once per second
     * @return No return value
     */

    @Override
    public void run(){
        while(true){
            int i = 0;
           /** for(Queue q : queueList){
                if(q.checkNotEmptyQueue() == true) {
                    System.out.println("queue " + q.getQueueId());
                    System.out.println();
                    System.out.println(q.toString());
                    System.out.println("--with average time: " + q.getAverageWaitingTime());
                    System.out.println("end of " + q.getQueueId());
                    System.out.println();

                    i++;
                }
            }**/
            sv.displayQueues(queueList);
            sv.displayAverages(queueList);
            try{
                sleep(1000);
            }
           catch (InterruptedException e){
               System.out.println("Error in trying to display queues");
           }
        }
    }
}
