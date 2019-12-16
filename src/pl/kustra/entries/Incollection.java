package pl.kustra.entries;

import java.util.ArrayList;
import java.util.List;

/**
 * @see IEntries
 */
public class Incollection implements IEntries {

    public Entry create() {
        Entry object = new Entry();
        object.type = "INCOLLECTION";
        object.requiredAttributes = object.setRequiredAttributes(getRequiredAttributes());
        object.optionalAttributes = object.setOptionalAttributes(getOptionalAttributes());

        return object;
    }

    public List<String> getRequiredAttributes() {
        List<String> requiredAttributes = new ArrayList<>();

        requiredAttributes.add("author");
        requiredAttributes.add("title");
        requiredAttributes.add("booktitle");
        requiredAttributes.add("publisher");
        requiredAttributes.add("year");

        return requiredAttributes;
    }

    public List<String> getOptionalAttributes() {
        List<String> optionalAttributes = new ArrayList<>();

        optionalAttributes.add("editor");
        optionalAttributes.add("volume");
        optionalAttributes.add("number");
        optionalAttributes.add("series");
        optionalAttributes.add("type");
        optionalAttributes.add("chapter");
        optionalAttributes.add("pages");
        optionalAttributes.add("address");
        optionalAttributes.add("edition");
        optionalAttributes.add("month");
        optionalAttributes.add("note");
        optionalAttributes.add("key");

        return optionalAttributes;
    }
}