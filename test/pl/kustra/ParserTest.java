package pl.kustra;

import org.junit.Test;
import pl.kustra.entries.Article;
import pl.kustra.entries.Book;
import pl.kustra.entries.Entry;
import pl.kustra.entries.Proceedings;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void parse() throws FileNotFoundException {
        Parser parser = new Parser();
        Entry result = parser.parse("/Users/blazejkustra/Documents/java/BibTeX Parser/test/pl/kustra/files/file1.txt").get(0);

        Entry object1 = new Article().create();
        object1.type = "ARTICLE";
        object1.key = "key1";
        object1.requiredAttributes.put("author", "L[eslie] A. Aamport");
        object1.requiredAttributes.put("title", "The Gnats and Gnus Document Preparation System");
        object1.requiredAttributes.put("journal", "\\mbox{G-Animal's} Journal");
        object1.requiredAttributes.put("year", "1986");

        object1.optionalAttributes.put("volume", "41");
        object1.optionalAttributes.put("pages", "73+");
        object1.optionalAttributes.put("month", "July");

        assertTrue(result.requiredAttributes.get("author").equals(object1.requiredAttributes.get("author")));
        assertTrue(result.requiredAttributes.get("title").equals(object1.requiredAttributes.get("title")));
        assertTrue(result.requiredAttributes.get("journal").equals(object1.requiredAttributes.get("journal")));
        assertTrue(result.requiredAttributes.get("year").equals(object1.requiredAttributes.get("year")));
        assertTrue(result.optionalAttributes.get("volume").equals(object1.optionalAttributes.get("volume")));
        assertTrue(result.optionalAttributes.get("pages").equals(object1.optionalAttributes.get("pages")));
        assertTrue(result.optionalAttributes.get("month").equals(object1.optionalAttributes.get("month")));
    }

    @Test
    public void parseStringVariable() throws FileNotFoundException {
        Parser parser = new Parser();
        List<Entry> result = parser.parse("/Users/blazejkustra/Documents/java/BibTeX Parser/test/pl/kustra/files/file1.txt");

        assertFalse(parser.stringVariables.isEmpty());

        String check = parser.stringVariables.get("STOCkey");
        assertTrue(check.equals("OX{\\singleletter{stoc}}"));

        check = parser.stringVariables.get("ACM");
        assertTrue(check.equals("The OX Association for Computing Machinery"));

        check = parser.stringVariables.get("STOC");
        assertTrue(check.equals(" Symposium on the Theory of Computing"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkRequired_Wrong1() {
        Entry object1 = new Article().create();
        object1.type = "ARTICLE";
        object1.key = "key";
        object1.requiredAttributes.put("author", "L[eslie] A. Aamport");
        object1.requiredAttributes.put("editor", "L[eslie] A. Aamport");
        object1.requiredAttributes.put("title", "The Gnats and Gnus Document Preparation System");
        object1.requiredAttributes.put("journal", "\\mbox{G-Animal's} Journal");
        object1.requiredAttributes.put("year", "1986");

        object1.optionalAttributes.put("volume", "41");
        object1.optionalAttributes.put("pages", "73+");
        object1.optionalAttributes.put("month", "July");

        Parser parser = new Parser();
        parser.checkRequired(object1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkRequired_Wrong2() {
        Entry object2 = new Book().create();
        object2.type = "BOOK";
        object2.key = "key";
        object2.requiredAttributes.put("author", "");
        object2.requiredAttributes.put("title", "Seminumerical Algorithms");
        object2.requiredAttributes.put("publisher", "Addison-Wesley");
        object2.requiredAttributes.put("year", "2019");

        Parser parser = new Parser();
        parser.checkRequired(object2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkRequired_Wrong3() {
        Entry object3 = new Proceedings().create();
        object3.type = "PROCEEDINGS";
        object3.key = "key";
        object3.requiredAttributes.put("year", "1983");

        Parser parser = new Parser();
        parser.checkRequired(object3);
    }

    @Test
    public void checkRequired_Right() {
        Entry object1 = new Article().create();
        object1.type = "ARTICLE";
        object1.key = "key1";
        object1.requiredAttributes.put("author", "L[eslie] A. Aamport");
        object1.requiredAttributes.put("title", "The Gnats and Gnus Document Preparation System");
        object1.requiredAttributes.put("journal", "\\mbox{G-Animal's} Journal");
        object1.requiredAttributes.put("year", "1986");

        object1.optionalAttributes.put("volume", "41");
        object1.optionalAttributes.put("pages", "73+");
        object1.optionalAttributes.put("month", "July");

        Entry object2 = new Book().create();
        object2.type = "BOOK";
        object2.key = "key2";
        object2.requiredAttributes.put("author", "Donald E. Knuth");
        object2.requiredAttributes.put("title", "Seminumerical Algorithms");
        object2.requiredAttributes.put("publisher", "Addison-Wesley");
        object2.requiredAttributes.put("year", "2019");

        Entry object3 = new Proceedings().create();
        object3.type = "PROCEEDINGS";
        object3.key = "key3";
        object3.requiredAttributes.put("title", "Proc. Fifteenth Annual");
        object3.requiredAttributes.put("year", "1983");

        Parser parser = new Parser();
        parser.checkRequired(object1);
        parser.checkRequired(object2);
        parser.checkRequired(object3);
    }
}



