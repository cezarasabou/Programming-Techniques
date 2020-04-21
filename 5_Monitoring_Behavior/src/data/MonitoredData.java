package data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MonitoredData {
    private  LocalDateTime startTime;
    private  LocalDateTime endTime;
    private  String activity;

    public MonitoredData(String startTimeString, String endTimeString, String activity){

        LocalDateTime startTime = formatString(startTimeString);
        LocalDateTime endTime = formatString(endTimeString);
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }
    public long duration() {
        Duration duration = Duration.between(endTime, startTime);
        return Math.abs(duration.toMinutes());
    }


   public LocalDate getDate(){
        return startTime.toLocalDate();
   }

    private LocalDateTime formatString(String string){

        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(string,f);

    }

    public  LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public  LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    public  String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }


    @Override
    public String toString() {
        return "MonitoredData{" +
                "startTime = " + startTime +
                ", endTime = " + endTime+
                ", activity = '" + activity + '\'' +
                '}';
    }

    public int smallerThanFive(){
        return duration()<5 ? 1: 0;
    }


}
