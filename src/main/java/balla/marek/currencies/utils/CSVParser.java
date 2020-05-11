package balla.marek.currencies.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CSVParser {

    public List<Map<String, String>> parse(InputStream is) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String[] headers = br.readLine().split(", ");
            List<Map<String, String>> records =
                    br.lines().map(s -> s.split(", "))
                      .map(t -> IntStream.range(0, t.length)
                                         .boxed()
                                         .collect(Collectors.toMap(i -> headers[i], i -> t[i])))
                      .collect(Collectors.toList());
            return records;
        }
    }
}
