package model;

/**
 * Model class that represents the clinet
 * Keeps track of each client's arrival, service and finishing time
 * consists of constructors and getters and setters
 * @author Cezara
 */
public class Client {

    private int arrivalTime;
    private int finishTime;
    private int serviceTime;
    private int clientId;

    public Client(int arrivalTime, int serviceTime, int id){
        this.arrivalTime = arrivalTime;
        this.serviceTime =serviceTime;
        this.clientId = id;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getFinishTime(){
        return finishTime;
    }
    public int getId(){
        return this.clientId;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }
}
