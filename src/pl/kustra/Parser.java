package pl.kustra;

import pl.kustra.entries.*;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main parsing class, which does most of program's work.
 */
class Parser {
    Map<String, String> stringVariables = new HashMap<>();

    /**
     * Constructor with StringVariables init (months parser)
     */
    Parser() {
        stringVariables.put("jan", "January");
        stringVariables.put("feb", "February");
        stringVariables.put("mar", "March");
        stringVariables.put("apr", "April");
        stringVariables.put("may", "May");
        stringVariables.put("jun", "June");
        stringVariables.put("jul", "July");
        stringVariables.put("aug", "August");
        stringVariables.put("sep", "September");
        stringVariables.put("oct", "October");
        stringVariables.put("nov", "November");
        stringVariables.put("dec", "December");
    }

    /**
     * Main parsing method that parse file to array of objects, it uses other methods as helpers in parsing.
     * It also validate if all required attributes are provided.
     *
     * @param filePath path to file
     * @return All parsed objects
     * @throws FileNotFoundException when file wasn't found
     */
    List<Entry> parse(String filePath) throws FileNotFoundException {
        List<Entry> result = new ArrayList();

        Scanner text = new Scanner(new File(filePath));

        while (text.hasNextLine()) {
            if (text.findInLine(Pattern.compile("@(\\w+)\\s*\\{")) != null) {
                String type = text.match().group(1).toUpperCase();

                if (type.equals("STRING")) {
                    parseStringVariable(text);
                } else {
                    Entry entry = parseEntry(text, type.toUpperCase());
                    if (entry != null) {
                        entry = makeCrossref(entry, result);
                        checkRequired(entry);
                        result.add(entry);
                    }
                }
            }
            if (text.hasNextLine()) text.nextLine();
        }
        return result;
    }

    /**
     * Parse individual Entry.
     *
     * @param text Scanner with unparsed bibtex
     * @param type Type of Entry to create
     * @return Parsed entry with all passed attributes + key and crossref values
     * @throws IllegalArgumentException when passed attribute has wrong pattern (e.g. comma in the value)/when attribute is invalid (Entry that do not contain passed field)
     */
    private Entry parseEntry(Scanner text, String type) {
        Entry object;
        switch (type) {
            case "ARTICLE":
                object = new Article().create();
                break;
            case "BOOK":
                object = new Book().create();
                break;
            case "BOOKLET":
                object = new Booklet().create();
                break;
            case "INBOOK":
                object = new Inbook().create();
                break;
            case "INCOLLECTION":
                object = new Incollection().create();
                break;
            case "INPROCEEDINGS":
                object = new Inproceedings().create();
                break;
            case "MANUAL":
                object = new Manual().create();
                break;
            case "MASTERSTHESIS":
                object = new Mastersthesis().create();
                break;
            case "MISC":
                object = new Misc().create();
                break;
            case "PHDTHESIS":
                object = new Phdthesis().create();
                break;
            case "TECHREPORT":
                object = new Techreport().create();
                break;
            case "UNPUBLISHED":
                object = new Unpublished().create();
                break;
            case "PROCEEDINGS":
                object = new Proceedings().create();
                break;
            default:
                return null;
        }

        String[] bodyWithKey = parseInside(text).split(",");
        object.key = bodyWithKey[0];
        String[] body = Arrays.copyOfRange(bodyWithKey, 1, bodyWithKey.length);

        for (String entryVariable : body) {
            if (Pattern.compile("\\s+").matcher(entryVariable).matches()) continue;

            Matcher attribute = Pattern.compile("\\s*([a-zA-Z]\\w*)\\s*=\\s*(\\S.*)\\s*").matcher(entryVariable);
            if (!attribute.matches())
                throw new IllegalArgumentException("Error while reading record with key: " + object.key + ", wrong input pattern! (should be: <argument> = <value>,)");

            if (object.requiredAttributes.get(attribute.group(1)) == "") {
                object.requiredAttributes.put(attribute.group(1), parseAttribute(attribute.group(2)));
            } else if (object.optionalAttributes.get(attribute.group(1)) == "") {
                object.optionalAttributes.put(attribute.group(1), parseAttribute(attribute.group(2)));
            } else if (attribute.group(1).equals("crossref")) {
                object.crossref = parseAttribute(attribute.group(2));
            } else
                throw new IllegalArgumentException("Error while reading record with key: " + object.key + ". There is no attribute called \"" + attribute.group(1) + "\"");
        }
        return object;
    }

    /**
     * Parse individual String Variable, saving it in order to use it later.
     *
     * @param text Scanner with unparsed bibtex
     * @throws IllegalArgumentException when passed attribute has wrong pattern (e.g. comma in the value)
     */
    void parseStringVariable(Scanner text) {
        String stringVariable = parseInside(text);
        Matcher attribute = Pattern.compile("\\s*([a-zA-Z]\\w*)\\s*=\\s*(\\S.*)\\s*").matcher(stringVariable);
        if (!attribute.matches())
            throw new IllegalArgumentException("Error while reading string variable record: " + stringVariable + ", wrong input pattern! (should be: <argument> = <value>)");

        stringVariables.put(attribute.group(1), parseAttribute(attribute.group(2)));
    }

    /**
     * Parse individual Entry to String value of what entry has inside of its brackets.
     *
     * @param text Scanner with unparsed bibtex
     * @return String with text of individual Entry inside of brackets
     * @throws IllegalArgumentException when input is invalid - opening brackets do not match with closing brackets
     */
    String parseInside(Scanner text) {
        StringBuilder stringBuilder = new StringBuilder();
        char sign = ' ';
        int bracketsCount = 1;

        while (text.hasNext() && bracketsCount > 0) {
            try {
                sign = text.findInLine(".").charAt(0);
            } catch (NullPointerException e) {
                stringBuilder.append("\n");
                text.nextLine();
                continue;
            }
            if (sign == '{') bracketsCount++;
            else if (sign == '}') bracketsCount--;

            if (bracketsCount > 0) stringBuilder.append(sign);
            if (bracketsCount == 0) return stringBuilder.toString();
        }
        throw new IllegalArgumentException("Error while reading record: " + stringBuilder.toString().split("\n")[0] + " There is no closing bracket \"}\"");
    }

    /**
     * Parse value of single attribute which could contain String Variables.
     *
     * @param text Scanner with unparsed bibtex
     * @return value of individual attribute
     * @throws IllegalArgumentException when value of attribute do not exist (Strings Variables)
     */
    String parseAttribute(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] textParts = text.split("#");

        for (String value : textParts) {
            value = value.trim();
            if (value.charAt(0) != '"' && value.charAt(0) != '{' && !(value.charAt(0) >= '0' && value.charAt(0) <= '9')) {

                String mapValue = stringVariables.get(value.trim());
                if (mapValue == null)
                    throw new IllegalArgumentException("Error while reading record. There is no string variable called \"" + value.trim() + "\"");

                stringBuilder.append(mapValue);
                continue;
            }

            if ((value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') || (value.charAt(0) == '{' && value.charAt(value.length() - 1) == '}'))
                value = value.substring(1, value.length() - 1);

            stringBuilder.append(value);
        }
        return stringBuilder.toString();

    }

    /**
     * Checks if all fields required for given entry type are filled.
     *
     * @param object Entry object to checked
     * @throws IllegalArgumentException when required argument don't have value
     */
    void checkRequired(Entry object) {

        if (object.requiredAttributes.containsKey("author") && object.requiredAttributes.containsKey("editor")) {
            if (!object.requiredAttributes.get("author").equals("") && !object.requiredAttributes.get("editor").equals(""))
                throw new IllegalArgumentException("Error while reading Entry with key: " + object.key + ", author and editor provided at the same time.");

            if (object.requiredAttributes.get("author").equals("") && object.requiredAttributes.get("editor").equals(""))
                throw new IllegalArgumentException("Error while reading Entry with key: " + object.key + ", required argument author or editor had no value!");

        } else if (object.requiredAttributes.containsKey("author")) {
            if (object.requiredAttributes.get("author").equals(""))
                throw new IllegalArgumentException("Error while reading record with key: " + object.key + ", required argument author had no value!");

        } else if (object.requiredAttributes.containsKey("editor")) {
            if (object.requiredAttributes.get("editor").equals(""))
                throw new IllegalArgumentException("Error while reading record with key: " + object.key + ", required argument editor had no value!");
        }

        for (Map.Entry<String, String> attribute : object.requiredAttributes.entrySet()) {
            if (!attribute.getKey().equals("author") && !attribute.getKey().equals("editor") && attribute.getValue().equals(""))
                throw new IllegalArgumentException("Error while reading record with key: " + object.key + ", required argument " + attribute.getKey() + " had no value!");
        }
    }

    /**
     * Make reference to another object based on crossref attribute
     *
     * @param object to be referenced from
     * @param result to find object to reference to
     * @return object with Attributes referenced from crossref value
     */
    Entry makeCrossref(Entry object, List<Entry> result) {
        if (!object.crossref.trim().isEmpty()) {

            String crossref = object.crossref.toUpperCase();
            Entry referenceObject = null;

            for (Entry find : result) {
                if (find.key.toUpperCase().equals(crossref)) {
                    referenceObject = find;
                    break;
                }
            }
            if (referenceObject == null)
                throw new IllegalArgumentException("Error while referencing record with key: " + object.key + ", object to reference does not exist.");
            if (referenceObject.type != object.type)
                throw new IllegalArgumentException("Error while referencing record witk key: " + object.key + ", types of objects does not match.");

            for (Map.Entry<String, String> attribute : referenceObject.requiredAttributes.entrySet()) {
                if (object.requiredAttributes.get(attribute.getKey()).equals("")) {
                    object.requiredAttributes.put(attribute.getKey(), attribute.getValue());
                }
            }

            for (Map.Entry<String, String> attribute : referenceObject.optionalAttributes.entrySet()) {
                if (object.optionalAttributes.get(attribute.getKey()).equals("")) {
                    object.optionalAttributes.put(attribute.getKey(), attribute.getValue());
                }
            }

        }

        return object;
    }
}
