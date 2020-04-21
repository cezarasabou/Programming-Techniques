package main;

import data.MonitoredData;
import data.MonitoredDataOperations;
import fileProcessor.ReadFile;
import java.util.*;

public class MainApplication {

    public static void main(String[] args){
        System.out.println("this is tema 5");

        ReadFile readFile = new ReadFile();
        ArrayList<MonitoredData> monitoredData1 = readFile.processFile();

        MonitoredDataOperations monitoredDataOperations = new MonitoredDataOperations(monitoredData1);
        System.out.println("number of monitored days");
        monitoredDataOperations.displayNumberOfMonitoredDays();
        System.out.println();
        System.out.println("number of activities");
        System.out.println(monitoredDataOperations.countNumberOfActivities());
        System.out.println();
        System.out.println("total number of apparitions");
        monitoredDataOperations.displayTotalNumberOfApparitions();
        System.out.println();
        System.out.println("daily number of activities");
        monitoredDataOperations.displayNumberOfApparitionsPerDay();
        System.out.println();
        System.out.println("display all durations");
        monitoredDataOperations.displayAllDurations();
        System.out.println();
        System.out.println("entire duration of each activity");
        System.out.println();
        monitoredDataOperations.displayEntireActivityDuration();
        System.out.println();
        System.out.println("all percentages");
        System.out.println();
        monitoredDataOperations.displayPercentages();
        System.out.println();
        System.out.println("percentage greater than 90");
        monitoredDataOperations.greaterThan90();




    }
}
