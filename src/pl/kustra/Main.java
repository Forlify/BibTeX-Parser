package pl.kustra;

import pl.kustra.entries.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        if (args.length != 3 || args[0].equals("")) {
            System.out.println("Arguments were not correct! There should be:\n" +
                    "1. Path to BibTeX file\n" +
                    "2. List of authors\n" +
                    "3. List of publication types\n" +
                    "First argument must ALWAYS be provided, while other two can be left as empty strings"
            );
            return;
        }


        Parser parser = new Parser();

        String orginalText = parser.readFile(args[0]);
        List<Entry> result = parser.parse(orginalText);

        Actions actions = new Actions();
        if (!args[1].equals("")) {
            result = actions.performAuthorFilter(result, args[1]);
        }

        if (!args[2].equals("")) {
            result = actions.performPublicationFilter(result, args[2]);
        }

        OutputBuilder outputBuilder = new OutputBuilder();
        System.out.println(outputBuilder.build(result,"#"));
    }
}
