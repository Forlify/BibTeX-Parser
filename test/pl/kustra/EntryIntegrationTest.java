package pl.kustra;

import org.junit.Test;
import org.junit.runner.RunWith;
import pl.kustra.entries.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class EntryIntegrationTest {

    @Test
    public void articleTest() {
        Entry object = new Article().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;
        assertEquals(object.type, "ARTICLE");
        assertEquals(requiredAttributes.get("author"), "");
        assertEquals(requiredAttributes.get("title"), "");
        assertEquals(requiredAttributes.get("journal"), "");
        assertEquals(requiredAttributes.get("year"), "");

        assertEquals(requiredAttributes.get("years"), null);
        assertEquals(requiredAttributes.get("manager"), null);

        assertEquals(optionalAttributes.get("volume"), "");
        assertEquals(optionalAttributes.get("number"), "");
        assertEquals(optionalAttributes.get("pages"), "");
        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("note"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get("notes"), null);
        assertEquals(optionalAttributes.get("authors"), null);
    }

    @Test
    public void bookTest() {
        Entry object = new Book().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;

        assertEquals(object.type, "BOOK");
        assertEquals(requiredAttributes.get("author"), "");
        assertEquals(requiredAttributes.get("editor"), "");
        assertEquals(requiredAttributes.get("title"), "");
        assertEquals(requiredAttributes.get("publisher"), "");
        assertEquals(requiredAttributes.get("year"), "");

        assertEquals(requiredAttributes.get("years"), null);
        assertEquals(requiredAttributes.get("authors"), null);

        assertEquals(optionalAttributes.get("volume"), "");
        assertEquals(optionalAttributes.get("series"), "");
        assertEquals(optionalAttributes.get("address"), "");
        assertEquals(optionalAttributes.get("edition"), "");
        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("note"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get("keys"), null);
        assertEquals(optionalAttributes.get("author"), null);
    }

    @Test
    public void inproceedingsTest() {
        Entry object = new Inproceedings().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;
        assertEquals(object.type, "INPROCEEDINGS");
        assertEquals(requiredAttributes.get("author"), "");
        assertEquals(requiredAttributes.get("title"), "");
        assertEquals(requiredAttributes.get("booktitle"), "");
        assertEquals(requiredAttributes.get("year"), "");

        assertEquals(requiredAttributes.get("AUTHORS"), null);
        assertEquals(requiredAttributes.get("titles"), null);

        assertEquals(optionalAttributes.get("editor"), "");
        assertEquals(optionalAttributes.get("volume"), "");
        assertEquals(optionalAttributes.get("number"), "");
        assertEquals(optionalAttributes.get("series"), "");
        assertEquals(optionalAttributes.get("pages"), "");
        assertEquals(optionalAttributes.get("address"), "");
        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("organization"), "");
        assertEquals(optionalAttributes.get("publisher"), "");
        assertEquals(optionalAttributes.get("note"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get("kkkkk"), null);
        assertEquals(optionalAttributes.get("byebye"), null);
    }

    @Test
    public void bookletTest() {
        Entry object = new Booklet().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;

        assertEquals(object.type, "BOOKLET");
        assertEquals(requiredAttributes.get("title"), "");

        assertEquals(requiredAttributes.get("titles"), null);
        assertEquals(requiredAttributes.get("author"), null);

        assertEquals(optionalAttributes.get("author"), "");
        assertEquals(optionalAttributes.get("howpublished"), "");
        assertEquals(optionalAttributes.get("address"), "");
        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("year"), "");
        assertEquals(optionalAttributes.get("note"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get("title"), null);
        assertEquals(optionalAttributes.get("titles"), null);
    }

    @Test
    public void inbookTest() {
        Entry object = new Inbook().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;

        assertEquals(object.type, "INBOOK");
        assertEquals(requiredAttributes.get("author"), "");
        assertEquals(requiredAttributes.get("editor"), "");
        assertEquals(requiredAttributes.get("title"), "");
        assertEquals(requiredAttributes.get("chapter"), "");
        assertEquals(requiredAttributes.get("pages"), "");
        assertEquals(requiredAttributes.get("publisher"), "");
        assertEquals(requiredAttributes.get("year"), "");

        assertEquals(requiredAttributes.get("volume"), null);
        assertEquals(requiredAttributes.get("series"), null);

        assertEquals(optionalAttributes.get("volume"), "");
        assertEquals(optionalAttributes.get("number"), "");
        assertEquals(optionalAttributes.get("series"), "");
        assertEquals(optionalAttributes.get("type"), "");
        assertEquals(optionalAttributes.get("address"), "");
        assertEquals(optionalAttributes.get("edition"), "");
        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("note"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get("year"), null);
        assertEquals(optionalAttributes.get("author"), null);
    }

    @Test
    public void incollectionTest() {
        Entry object = new Incollection().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;

        assertEquals(object.type, "INCOLLECTION");
        assertEquals(requiredAttributes.get("author"), "");
        assertEquals(requiredAttributes.get("title"), "");
        assertEquals(requiredAttributes.get("booktitle"), "");
        assertEquals(requiredAttributes.get("publisher"), "");
        assertEquals(requiredAttributes.get("year"), "");

        assertEquals(requiredAttributes.get("YEARS"), null);
        assertEquals(requiredAttributes.get(""), null);

        assertEquals(optionalAttributes.get("editor"), "");
        assertEquals(optionalAttributes.get("volume"), "");
        assertEquals(optionalAttributes.get("number"), "");
        assertEquals(optionalAttributes.get("series"), "");
        assertEquals(optionalAttributes.get("type"), "");
        assertEquals(optionalAttributes.get("chapter"), "");
        assertEquals(optionalAttributes.get("pages"), "");
        assertEquals(optionalAttributes.get("address"), "");
        assertEquals(optionalAttributes.get("edition"), "");
        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("note"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get(""), null);
        assertEquals(optionalAttributes.get(""), null);
    }

    @Test
    public void manualTest() {
        Entry object = new Manual().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;

        assertEquals(object.type, "MANUAL");
        assertEquals(requiredAttributes.get("title"), "");

        assertEquals(requiredAttributes.get("AUTHOR"), null);
        assertEquals(requiredAttributes.get(""), null);

        assertEquals(optionalAttributes.get("author"), "");
        assertEquals(optionalAttributes.get("organization"), "");
        assertEquals(optionalAttributes.get("address"), "");
        assertEquals(optionalAttributes.get("edition"), "");
        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("year"), "");
        assertEquals(optionalAttributes.get("note"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get(""), null);
        assertEquals(optionalAttributes.get("keys"), null);
    }

    @Test
    public void mastersthesisTest() {
        Entry object = new Mastersthesis().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;
        assertEquals(object.type, "MASTERSTHESIS");
        assertEquals(requiredAttributes.get("author"), "");
        assertEquals(requiredAttributes.get("title"), "");
        assertEquals(requiredAttributes.get("school"), "");
        assertEquals(requiredAttributes.get("year"), "");

        assertEquals(requiredAttributes.get(""), null);
        assertEquals(requiredAttributes.get("AUTHOR"), null);

        assertEquals(optionalAttributes.get("type"), "");
        assertEquals(optionalAttributes.get("address"), "");
        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("note"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get("KEYS"), null);
        assertEquals(optionalAttributes.get(""), null);
    }

    @Test
    public void phdthesisTest() {
        Entry object = new Phdthesis().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;

        assertEquals(object.type, "PHDTHESIS");
        assertEquals(requiredAttributes.get("author"), "");
        assertEquals(requiredAttributes.get("title"), "");
        assertEquals(requiredAttributes.get("school"), "");
        assertEquals(requiredAttributes.get("year"), "");

        assertEquals(requiredAttributes.get(""), null);
        assertEquals(requiredAttributes.get("editor"), null);

        assertEquals(optionalAttributes.get("type"), "");
        assertEquals(optionalAttributes.get("address"), "");
        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("note"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get("keys"), null);
        assertEquals(optionalAttributes.get("yasr"), null);
    }

    @Test
    public void techreportTest() {
        Entry object = new Techreport().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;

        assertEquals(object.type, "TECHREPORT");
        assertEquals(requiredAttributes.get("author"), "");
        assertEquals(requiredAttributes.get("title"), "");
        assertEquals(requiredAttributes.get("institution"), "");
        assertEquals(requiredAttributes.get("year"), "");

        assertEquals(requiredAttributes.get("editor"), null);
        assertEquals(requiredAttributes.get("EDITOR"), null);

        assertEquals(optionalAttributes.get("editor"), "");
        assertEquals(optionalAttributes.get("volume"), "");
        assertEquals(optionalAttributes.get("number"), "");
        assertEquals(optionalAttributes.get("series"), "");
        assertEquals(optionalAttributes.get("address"), "");
        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("organization"), "");
        assertEquals(optionalAttributes.get("publisher"), "");
        assertEquals(optionalAttributes.get("note"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get("keys"), null);
        assertEquals(optionalAttributes.get("year"), null);
    }

    @Test
    public void miscTest() {
        Entry object = new Misc().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;

        assertEquals(object.type, "MISC");
        assertEquals(requiredAttributes.get("author"), null);
        assertEquals(requiredAttributes.get("title"), null);
        assertEquals(requiredAttributes.get("institution"), null);
        assertEquals(requiredAttributes.get("year"), null);

        assertEquals(optionalAttributes.get("author"), "");
        assertEquals(optionalAttributes.get("title"), "");
        assertEquals(optionalAttributes.get("howpublished"), "");
        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("year"), "");
        assertEquals(optionalAttributes.get("note"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get(""), null);
        assertEquals(optionalAttributes.get("KEYS"), null);
    }

    @Test
    public void unpublishedTest() {
        Entry object = new Unpublished().create();
        Map<String, String> requiredAttributes = object.requiredAttributes;
        Map<String, String> optionalAttributes = object.optionalAttributes;

        assertEquals(object.type, "UNPUBLISHED");
        assertEquals(requiredAttributes.get("author"), "");
        assertEquals(requiredAttributes.get("title"), "");
        assertEquals(requiredAttributes.get("note"), "");

        assertEquals(requiredAttributes.get("EDITOR"), null);
        assertEquals(requiredAttributes.get("editor"), null);

        assertEquals(optionalAttributes.get("month"), "");
        assertEquals(optionalAttributes.get("year"), "");
        assertEquals(optionalAttributes.get("key"), "");

        assertEquals(optionalAttributes.get("months"), null);
        assertEquals(optionalAttributes.get("author"), null);
    }

    @Test
    public void myTest() {
    }

}
