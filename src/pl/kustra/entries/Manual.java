package pl.kustra.entries;

import java.util.ArrayList;
import java.util.List;

public class Manual implements IEntries {

    public Entry create() {
        Entry object = new Entry();
        object.type = "MANUAL";
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
        optionalAttributes.add("organization");
        optionalAttributes.add("address");
        optionalAttributes.add("edition");
        optionalAttributes.add("month");
        optionalAttributes.add("year");
        optionalAttributes.add("note");
        optionalAttributes.add("key");

        return optionalAttributes;
    }
}