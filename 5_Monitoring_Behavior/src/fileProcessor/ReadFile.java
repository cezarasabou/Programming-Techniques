package fileProcessor;

import data.MonitoredData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ReadFile {

    public ArrayList<MonitoredData> processFile() {
        ArrayList<MonitoredData> monitoredData = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("Activities.txt"))) {
            stream
                    .map(line -> line.split("\t\t"))
                    .map(a -> (new MonitoredData(a[0], a[1], a[2])))
                    .collect(Collectors.toCollection(() -> monitoredData));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return monitoredData;
    }

}
