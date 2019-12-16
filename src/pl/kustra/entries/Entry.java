package pl.kustra.entries;

import java.util.*;

/**
 * Objects of this class represent entire single BibTeX record, e. g. section from @RECORDTYPE{, through tags and their values, to closing bracket }.
 */
public class Entry {

    public String type;
    public String key;
    public String crossref;
    public Map<String, String> requiredAttributes;
    public Map<String, String> optionalAttributes;

    /**
     * Initializes recordType and key as empty Strings to avoid NullPointerException. Also initializes attributes lists as empty lists.
     */
    public Entry() {
        this.type = "";
        this.key = "";
        this.crossref = "";

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