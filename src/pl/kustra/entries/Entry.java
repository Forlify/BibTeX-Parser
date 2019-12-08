package pl.kustra.entries;

import java.util.*;

public class Entry {

    String type;
    Map<String, String> requiredAttributes;
    Map<String, String> optionalAttributes;

    public Entry() {

        this.type = "";
        this.requiredAttributes = new LinkedHashMap<>();
        this.optionalAttributes = new LinkedHashMap<>();
    }

    public Map<String, String> setRequiredAttributes(List<String> requiredAttributes) {
        Map<String, String> tempRequired = new LinkedHashMap<>();

        for (String attribute : requiredAttributes) {
            tempRequired.put(attribute, "");
        }

        return tempRequired;
    }

    public Map<String, String> setOptionalAttributes(List<String> optionalAttributes) {
        Map<String, String> tempOptional = new LinkedHashMap<>();

        for (String attribute : optionalAttributes) {
            tempOptional.put(attribute, "");
        }

        return tempOptional;
    }
}