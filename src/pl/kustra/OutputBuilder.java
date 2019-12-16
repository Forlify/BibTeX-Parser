package pl.kustra;

import de.vandermeer.asciitable.AsciiTable;
import pl.kustra.entries.Entry;

import java.util.List;
import java.util.Map;

/**
 * Simple, default class preparing final program output.
 */
public class OutputBuilder {
    /**
     * Main method building final results of the program, preparing it to be printed. It uses ASCII-table library from maven to build tables.
     *
     * @param entries list of entries to be printed
     * @return String to be printed
     */

    public String buildTable(List<Entry> entries) {
        if (entries.isEmpty()) return "";

        StringBuilder stringBuilder = new StringBuilder();


        for (Entry object : entries) {
            AsciiTable at = new AsciiTable();
            at.addRule();
            at.addRow(null, object.type + ": (" + object.key + ")");
            at.addRule();
            if(!object.crossref.equals("")) {
                at.addRow("crossref", object.crossref);
                at.addRule();
            }
            for (Map.Entry<String, String> entry : object.requiredAttributes.entrySet()) {
                if (entry.getValue().trim() != "") {

                    at.addRow(entry.getKey(), entry.getValue());
                    at.addRule();
                }
            }

            for (Map.Entry<String, String> entry : object.optionalAttributes.entrySet()) {
                if (entry.getValue().trim() != "") {
                    at.addRow(entry.getKey(), entry.getValue());
                    at.addRule();
                }
            }
            stringBuilder.append("\u001B[33m" + at.render() + "\n\n" + "\u001B[0m");
        }

        return stringBuilder.toString();
    }
}
