package pl.kustra;

import pl.kustra.entries.Entry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Parser {
    String readFile(String filePath) {
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.getProperty("line.separator"));
            }
        } catch (FileNotFoundException exc) {
            System.out.println("File not found!");
        } catch (IOException exc) {
            System.out.println("Error while reading line!");
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException exc) {
                    System.out.println("Error while closing reader!");
                }
        }
        return stringBuilder.toString();
    }

    List<Entry> parse(String orginalText) {
        return null;
    }
}
