package pl.kustra.entries;

import java.util.ArrayList;
import java.util.List;

/**
 * @see IEntries
 */
public class Misc implements IEntries {

    public Entry create() {
        Entry object = new Entry();
        object.type = "MISC";
        object.requiredAttributes = object.setRequiredAttributes(getRequiredAttributes());
        object.optionalAttributes = object.setOptionalAttributes(getOptionalAttributes());

        return object;
    }

    public List<String> getRequiredAttributes() {
        List<String> requiredAttributes = new ArrayList<>();

        return requiredAttributes;
    }

    public List<String> getOptionalAttributes() {
        List<String> optionalAttributes = new ArrayList<>();

        optionalAttributes.add("author");
        optionalAttributes.add("title");
        optionalAttributes.add("howpublished");
        optionalAttributes.add("month");
        optionalAttributes.add("year");
        optionalAttributes.add("note");
        optionalAttributes.add("key");

        return optionalAttributes;
    }
}