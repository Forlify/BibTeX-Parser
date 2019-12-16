package pl.kustra;

import pl.kustra.entries.Entry;

import java.util.ArrayList;
import java.util.List;

public class Actions {
    /**
     * Method that return a list of entries filtered by provided author.
     *
     * @param entries All parsed entries
     * @param authors Author to filter all entries
     * @return List of filtered entries
     */
    public static List<Entry> performAuthorFilter(List<Entry> entries, String authors) {

        List<Entry> result = new ArrayList();
        String[] authorsArray = authors.split(" ");
        String lastname = authorsArray[0].toLowerCase();
        String firstname = null;

        if (authorsArray.length == 2) firstname = authorsArray[1].toLowerCase();

        for (Entry object : entries) {
            if (object.requiredAttributes.get("author") != null) {
                if (isAuthorMatched(object.requiredAttributes.get("author").toLowerCase(), firstname, lastname))
                    result.add(object);
                continue;
            }

            if (object.requiredAttributes.get("editor") != null) {
                if (isAuthorMatched(object.requiredAttributes.get("editor").toLowerCase(), firstname, lastname))
                    result.add(object);
                continue;
            }

            if (object.optionalAttributes.get("author") != null) {
                if (isAuthorMatched(object.optionalAttributes.get("author").toLowerCase(), firstname, lastname))
                    result.add(object);
                continue;
            }

            if (object.optionalAttributes.get("editor") != null) {
                if (isAuthorMatched(object.optionalAttributes.get("editor").toLowerCase(), firstname, lastname))
                    result.add(object);
                continue;
            }
        }

        return result;
    }

    /**
     * Service to help performAuthorFilter deciding if single Entry match name filter.
     *
     * @param authors
     * @param firstname First name filter
     * @param lastname Last name filter
     * @return True if authors match names
     */
    static boolean isAuthorMatched(String authors, String firstname, String lastname) {
        String[] authorsArray = authors.split(" and ");
        for (String author : authorsArray) {
            if (firstname != null) {
                if (author.contains(firstname) && author.contains(lastname)) return true;
            } else if (author.contains(lastname)) return true;
        }
        return false;
    }

    /**
     * Method that return a list of entries filtered by provided type of Entry.
     *
     * @param entries
     * @param type Type of Entry that it look for
     * @return List of filtered entries
     */
    public static List<Entry> performPublicationFilter(List<Entry> entries, String type) {
        List<Entry> result = new ArrayList();
        for (Entry object : entries)
            if(object.type.toLowerCase().equals(type.toLowerCase()))
                result.add(object);

        return result;
    }
}
