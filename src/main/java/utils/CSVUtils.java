package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static Object[][] readCSV(String fileName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("testdata/" + fileName);
        if (inputStream == null) {
            throw new FileNotFoundException("File not found in resources/testdata: " + fileName);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<Object[]> data = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(",");
            data.add(row);
        }
        reader.close();
        return data.toArray(new Object[0][]);
    }

}
