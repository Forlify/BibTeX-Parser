package pl.kustra.entries;

import java.util.ArrayList;
import java.util.List;

public class Phdthesis implements IEntries {

    public Entry create() {
        Entry object = new Entry();
        object.type = "PHDTHESIS";
        object.requiredAttributes = object.setRequiredAttributes(getRequiredAttributes());
        object.optionalAttributes = object.setOptionalAttributes(getOptionalAttributes());

        return object;
    }

    public List<String> getRequiredAttributes() {
        List<String> requiredAttributes = new ArrayList<>();

        requiredAttributes.add("author");
        requiredAttributes.add("title");
        requiredAttributes.add("school");
        requiredAttributes.add("year");

        return requiredAttributes;
    }

    public List<String> getOptionalAttributes() {
        List<String> optionalAttributes = new ArrayList<>();

        optionalAttributes.add("type");
        optionalAttributes.add("address");
        optionalAttributes.add("month");
        optionalAttributes.add("note");
        optionalAttributes.add("key");

        return optionalAttributes;
    }
}