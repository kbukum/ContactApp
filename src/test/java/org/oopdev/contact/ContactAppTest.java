package org.oopdev.contact;

import org.junit.Test;
import org.oopdev.contact.commands.ContactCommands;
import org.oopdev.contact.model.Contact;

import java.util.*;

/**
 * Created by kamilbukum on 21/10/15.
 */

public class ContactAppTest {



    @Test
    public void addContact(){
        ContactApp contactApp = new ContactApp(configureDatabase());

        HashMap<String,List<Contact>> contactList = new LinkedHashMap<String, List<Contact>>();

        List<Contact> list = new LinkedList<Contact>();

        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("Kamil");
        contact.setLastName("Bukum");
        Set<String> phones = new HashSet<String>() ;
        phones.add("+90 539 369 03 93");
        contact.setPhones(phones);

        list.add(contact);
        contactList.put("mockfile.xml",list);
        contactApp.addContacts(contactList);
    }


    @Test
    public void list(){
        ContactApp contactApp = new ContactApp(configureDatabase());
        contactApp.list();

    }

    @Test
    public void search(){
        ContactApp contactApp = new ContactApp(configureDatabase());

        ContactCommands commands = new ContactCommands();
        List<String> keys = new LinkedList<String>();
        keys.add("Kami");
        commands.setKeys(keys);
        contactApp.search(commands);

    }

    private DatabaseConfiguration configureDatabase(){
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
        databaseConfiguration.setDb("ContactsTest");
        System.setProperty(DatabaseConfiguration.SCAN_MODEL_PACKAGE,"org.oopdev.contact");
        return databaseConfiguration;
    }
}
