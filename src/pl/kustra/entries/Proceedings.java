package pl.kustra.entries;

import java.util.ArrayList;
import java.util.List;

/**
 * @see IEntries
 */
public class Proceedings implements IEntries {

    public Entry create() {
        Entry object = new Entry();
        object.type = "PROCEEDINGS";
        object.requiredAttributes = object.setRequiredAttributes(getRequiredAttributes());
        object.optionalAttributes = object.setOptionalAttributes(getOptionalAttributes());

        return object;
    }

    public List<String> getRequiredAttributes() {
        List<String> requiredAttributes = new ArrayList<>();
        requiredAttributes.add("title");
        requiredAttributes.add("year");

        return requiredAttributes;
    }

    public List<String> getOptionalAttributes() {
        List<String> optionalAttributes = new ArrayList<>();

        optionalAttributes.add("editor");
        optionalAttributes.add("volume");
        optionalAttributes.add("number");
        optionalAttributes.add("series");
        optionalAttributes.add("address");
        optionalAttributes.add("month");
        optionalAttributes.add("organization");
        optionalAttributes.add("publisher");
        optionalAttributes.add("note");
        optionalAttributes.add("key");

        return optionalAttributes;
    }
}