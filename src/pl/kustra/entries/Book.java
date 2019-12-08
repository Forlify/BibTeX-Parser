package pl.kustra.entries;

import java.util.ArrayList;
import java.util.List;

public class Book{

    public Entry create() {
        Entry object = new Entry();
        object.type = "BOOK";
        object.requiredAttributes = object.setRequiredAttributes(getRequiredAttributes());
        object.optionalAttributes = object.setOptionalAttributes(getOptionalAttributes());

        return object;
    }

    public List<String> getRequiredAttributes() {
        List<String> requiredAttributes = new ArrayList<>();

        requiredAttributes.add("author");
        requiredAttributes.add("editor");
        requiredAttributes.add("title");
        requiredAttributes.add("publisher");
        requiredAttributes.add("year");

        return requiredAttributes;
    }

    public List<String> getOptionalAttributes() {
        List<String> optionalAttributes = new ArrayList<>();

        optionalAttributes.add("volume");
        optionalAttributes.add("series");
        optionalAttributes.add("address");
        optionalAttributes.add("edition");
        optionalAttributes.add("month");
        optionalAttributes.add("note");
        optionalAttributes.add("key");

        return optionalAttributes;
    }
}