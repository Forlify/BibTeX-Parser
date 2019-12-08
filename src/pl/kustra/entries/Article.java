package pl.kustra.entries;

import java.util.ArrayList;
import java.util.List;

public class Article implements IEntries {

    public Entry create() {
        Entry object = new Entry();
        object.type = "ARTICLE";
        object.requiredAttributes = object.setRequiredAttributes(getRequiredAttributes());
        object.optionalAttributes = object.setOptionalAttributes(getOptionalAttributes());

        return object;
    }

    public List<String> getRequiredAttributes() {
        List<String> requiredAttributes = new ArrayList<>();

        requiredAttributes.add("author");
        requiredAttributes.add("title");
        requiredAttributes.add("journal");
        requiredAttributes.add("year");

        return requiredAttributes;
    }

    public List<String> getOptionalAttributes() {
        List<String> optionalAttributes = new ArrayList<>();

        optionalAttributes.add("volume");
        optionalAttributes.add("number");
        optionalAttributes.add("pages");
        optionalAttributes.add("month");
        optionalAttributes.add("note");
        optionalAttributes.add("key");

        return optionalAttributes;
    }
}