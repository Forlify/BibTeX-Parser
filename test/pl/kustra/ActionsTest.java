package pl.kustra;

import org.junit.Test;
import pl.kustra.entries.Article;
import pl.kustra.entries.Book;
import pl.kustra.entries.Entry;
import pl.kustra.entries.Proceedings;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ActionsTest {


    @Test
    public void performAuthorFilter() {
        List<Entry> result = new ArrayList();
        Entry object1 = new Article().create();
        object1.type ="ARTICLE";
        object1.key ="key1";
        object1.requiredAttributes.put("author","Blasie A. Aamport");
        object1.requiredAttributes.put("title","The Gnats and Gnus Document Preparation System");
        object1.requiredAttributes.put("journal","\\mbox{G-Animal's} Journal");
        object1.requiredAttributes.put("year","1986");

        object1.optionalAttributes.put("volume","41");
        object1.optionalAttributes.put("pages","73+");
        object1.optionalAttributes.put("month","July");

        Entry object2 = new Book().create();
        object2.type ="BOOK";
        object2.key ="key2";
        object2.requiredAttributes.put("author","Leslie A. Aamport");
        object2.requiredAttributes.put("title","Seminumerical Algorithms");
        object2.requiredAttributes.put("publisher","Addison-Wesley");
        object2.requiredAttributes.put("year","2019");

        Entry object3 = new Proceedings().create();
        object3.type ="PROCEEDINGS";
        object3.key ="key3";
        object3.requiredAttributes.put("title","Proc. Fifteenth Annual");
        object3.requiredAttributes.put("year","1983");

        Actions actions = new Actions();

        result.add(object1);
        result.add(object2);
        result.add(object3);
        result = actions.performAuthorFilter(result, "Aamport Leslie");
        assertTrue(result.size() == 1);
        result.clear();

        result.add(object1);
        result.add(object2);
        result.add(object3);
        result = actions.performAuthorFilter(result, "Aamport");
        assertTrue(result.size() == 2);
        result.clear();

        result.add(object1);
        result.add(object2);
        result.add(object3);
        result = actions.performAuthorFilter(result, "Aamporte");
        assertTrue(result.size() == 0);
    }


    @Test
    public void performPublicationFilter() {
        List<Entry> result = new ArrayList();
        Entry object1 = new Article().create();
        object1.type ="ARTICLE";
        object1.key ="key1";
        object1.requiredAttributes.put("author","Blasie A. Aamport");
        object1.requiredAttributes.put("title","The Gnats and Gnus Document Preparation System");
        object1.requiredAttributes.put("journal","\\mbox{G-Animal's} Journal");
        object1.requiredAttributes.put("year","1986");

        object1.optionalAttributes.put("volume","41");
        object1.optionalAttributes.put("pages","73+");
        object1.optionalAttributes.put("month","July");

        Entry object2 = new Book().create();
        object2.type ="BOOK";
        object2.key ="key2";
        object2.requiredAttributes.put("author","Leslie A. Aamport");
        object2.requiredAttributes.put("title","Seminumerical Algorithms");
        object2.requiredAttributes.put("publisher","Addison-Wesley");
        object2.requiredAttributes.put("year","2019");

        Entry object3 = new Article().create();
        object1.type ="ARTICLE";
        object1.key ="key1";
        object1.requiredAttributes.put("author","Blasie A. Aamport");
        object1.requiredAttributes.put("title","The Gnats and Gnus Document Preparation System");
        object1.requiredAttributes.put("journal","\\mbox{G-Animal's} Journal");
        object1.requiredAttributes.put("year","1986");

        object1.optionalAttributes.put("volume","41");
        object1.optionalAttributes.put("pages","73+");
        object1.optionalAttributes.put("month","July");

        Actions actions = new Actions();

        result.add(object1);
        result.add(object2);
        result.add(object3);
        result = actions.performPublicationFilter(result, "ARTICLE");
        assertTrue(result.size() == 2);
        result.clear();

        result.add(object1);
        result.add(object2);
        result.add(object3);
        result = actions.performPublicationFilter(result, "BOOK");
        assertTrue(result.size() == 1);
        result.clear();

        result.add(object1);
        result.add(object2);
        result.add(object3);
        result = actions.performPublicationFilter(result, "ARTICLES");
        assertTrue(result.size() == 0);
    }
}
