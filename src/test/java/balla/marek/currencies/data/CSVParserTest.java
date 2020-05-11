package balla.marek.currencies.data;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class CSVParserTest {

    @Test
    public void shouldParseCSV() throws IOException {
        InputStream csv = new ClassPathResource("eurofxref.csv").getInputStream();

        CSVParser reader = new CSVParser();
        List<Map<String, String>> records = reader.parse(csv);
        Assert.assertEquals(1, records.size());
        Assert.assertEquals(33, records.get(0).keySet().size());
        Assert.assertEquals("11.0695", records.get(0).get("NOK"));
    }
}
