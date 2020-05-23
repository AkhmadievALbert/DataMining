package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Task2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("data/transactions.csv"));
        Map<String, Integer> map = new TreeMap<>();
        while (sc.hasNext()){
            String line = sc.nextLine();
            String[] strings = line.split(";");
            if(map.containsKey(strings[0])) {
                map.put(strings[0], map.get(strings[0]) + 1);
            }else{
                map.put(strings[0],1);
            }
        }
        FileWriter fileWriter = new FileWriter(new File("data/result"));
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(x -> {
                    try {
                        fileWriter.write(x.getKey() + " " + x.getValue() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
