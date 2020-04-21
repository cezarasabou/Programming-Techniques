package data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MonitoredDataOperations {
    ArrayList<MonitoredData> monitoredData;

    public MonitoredDataOperations(ArrayList<MonitoredData> monitoredData){
        this.monitoredData = monitoredData;
    }
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
    public long countNumberOfMonitoredDays(){
        return  monitoredData
                .stream()
                .filter(distinctByKey(MonitoredData::getDate))
                .count();
    }

    public void displayNumberOfMonitoredDays(){
        System.out.println("number of monitored days is : " + countNumberOfMonitoredDays());
    }

    public long countNumberOfActivities(){
        return monitoredData
                .stream()
                .distinct()
                .count();
    }


    private Map<String, Long> totalNumberOfApparitions(){
      Map<String, Long> apparitionsMap = monitoredData
              .stream()
              .collect(Collectors.groupingBy(MonitoredData :: getActivity,Collectors.counting()));
        return apparitionsMap;
    }

    public void displayTotalNumberOfApparitions() {
        totalNumberOfApparitions()
                .keySet()
                .forEach(activity -> System.out.println( activity + " has a total number of apparitions of " + totalNumberOfApparitions().get(activity)));
    }

    private Map<LocalDate, Map<String, Long>> groupByDays(){
        return monitoredData
               .stream()
               .collect(Collectors.groupingBy(MonitoredData :: getDate,Collectors.groupingBy(MonitoredData :: getActivity,Collectors.counting())));
   }

    public void displayNumberOfApparitionsPerDay(){
        groupByDays()
                .keySet()
                .forEach(day -> System.out.println(day + " " + day.getDayOfWeek() + " has " + groupByDays().get(day)));
    }

    private Map<String, Long> computeDuration(){
        return monitoredData
                .stream()
                .collect(Collectors.toMap(MonitoredData :: toString,MonitoredData::duration));
    }

    public void displayAllDurations(){
        computeDuration()
                .keySet()
                .forEach(newMonitoredData -> System.out.println(newMonitoredData + " has a duration of" + computeDuration().get(newMonitoredData)));
    }

    private Map<String, Long> computeEntireActivityDurations(){
        return monitoredData
                .stream()
                .collect(Collectors.groupingBy(MonitoredData :: getActivity,Collectors.summingLong((MonitoredData :: duration))));
    }
    public void displayEntireActivityDuration(){
        Map<String, Long> activityDuration = computeEntireActivityDurations();

        computeEntireActivityDurations()
                .keySet()
                .forEach(activity -> System.out.println(activity + " has a total duration of : " +activityDuration.get(activity)));
    }


    private Map<String, Double> numberOfEachActivitySmallerThanFive() {
        return monitoredData
                .stream()
                .collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.summingDouble(MonitoredData::smallerThanFive)));
    }

    private Map<String, Double> percentages(){
        Map<String, Double> percentages = numberOfEachActivitySmallerThanFive();

        for(String string : percentages.keySet()){
            percentages.compute(string, (key, val) -> val = (val*100)/totalNumberOfApparitions().get(key));
        }
        return percentages;
    }
    public void greaterThan90(){
       Map<String, Double> greaterThan90= percentages()
               .entrySet()
               .stream()
               .filter(value -> value.getValue() > 90)
               .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        greaterThan90
                .keySet()
                .forEach(activity -> System.out.println(activity + " has a percentage of " + greaterThan90.get(activity) + " which is greater than 90"));
    }
    public void displayPercentages(){
        System.out.println("total");
        totalNumberOfApparitions()
                .keySet()
                .forEach(activity -> System.out.println(activity + "has a total number of apparitons of " + totalNumberOfApparitions().get(activity)));

        System.out.println("smaller than five");
        numberOfEachActivitySmallerThanFive()
                .keySet()
                .forEach(activity -> System.out.println(activity  + " category has " + numberOfEachActivitySmallerThanFive().get(activity) + " smaller than 5 activities"));
        System.out.println("percentages");
        percentages()
                .keySet()
                .forEach(activity -> System.out.println(activity + " has a percentage of " + percentages().get(activity) + " smaller than 5 "));
    }
}
