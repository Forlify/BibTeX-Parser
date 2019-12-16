package pl.kustra;

import pl.kustra.entries.*;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Main class of the program and regular user will only interact with it.
 */
public class Main {

    /**
     * Main method of the program, calling other methods to parse records in provided file, filter them according to passed arguments and display results.
     * @param args - array of 3 elements: file path, authors to filter and publication types to filter
     */
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 3 || args[0].equals("")) {
            System.out.println("Arguments were not correct! Three arguments have to be passed:\n" + "1. Path to BibTeX file\n" + "2. Author, <lastname> or <firstname lastname>, for example: \"Polak\" or \"Polak Stanislaw\" \n" + "3. List of publication types\n" + "First argument is required, while other two can be left as empty strings");
            return;
        }

        Parser parser = new Parser();
        List<Entry> result = parser.parse(args[0]);

        Actions actions = new Actions();
        if (!args[1].equals("")) result = actions.performAuthorFilter(result, args[1]);
        if (!args[2].equals("")) result = actions.performPublicationFilter(result, args[2]);

        System.out.println(new OutputBuilder().buildTable(result));
        if (!args[1].equals("")) System.out.println("Author filter: " + args[1].toUpperCase());
        if (!args[2].equals("")) System.out.println("Type filter: " + args[2].toUpperCase());
    }
}

