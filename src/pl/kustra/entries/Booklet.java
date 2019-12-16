package pl.kustra.entries;

import java.util.ArrayList;
import java.util.List;

/**
 * @see IEntries
 */
public class Booklet {

    public Entry create() {
        Entry object = new Entry();
        object.type = "BOOKLET";

        object.requiredAttributes = object.setRequiredAttributes(getRequiredAttributes());
        object.optionalAttributes = object.setOptionalAttributes(getOptionalAttributes());

        return object;
    }

    public List<String> getRequiredAttributes() {
        List<String> requiredAttributes = new ArrayList<>();

        requiredAttributes.add("title");

        return requiredAttributes;
    }

    public List<String> getOptionalAttributes() {
        List<String> optionalAttributes = new ArrayList<>();

        optionalAttributes.add("author");
        optionalAttributes.add("howpublished");
        optionalAttributes.add("address");
        optionalAttributes.add("month");
        optionalAttributes.add("year");
        optionalAttributes.add("note");
        optionalAttributes.add("key");

        return optionalAttributes;
    }
}