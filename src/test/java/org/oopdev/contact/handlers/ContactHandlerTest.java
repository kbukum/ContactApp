package org.oopdev.contact.handlers;

import org.junit.Test;
import org.oopdev.contact.handler.ContactHandler;
import org.oopdev.contact.model.Contact;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by kamilbukum on 24/10/15.
 */
public class ContactHandlerTest {

    //@Test
    public void testContactHandler() throws ParserConfigurationException, SAXException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        try {
            InputStream is =getClass().getClassLoader().getResourceAsStream("examples/handler.xml");
            ContactHandler handler = new ContactHandler();
            saxParser.parse(is, handler);

            List<Contact> expectedList = new LinkedList<Contact>();
            Contact contact = new Contact();
            contact.setId(1L);
            contact.setName("Name1");
            contact.setLastName("LastName1");

            Set<String> phones = new HashSet<String>();
            phones.add("905552223344");
            phones.add("905552223344");

            expectedList.add(contact);


            contact = new Contact();
            contact.setId(2L);
            contact.setName("Name2");
            contact.setLastName("LastName2");
            contact.setPhones(phones);
            phones = new HashSet<String>();
            phones.add("905552223344");
            phones.add("905552223344");
            contact.setPhones(phones);

            expectedList.add(contact);

            assertEquals(handler.getResultList(), expectedList);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
