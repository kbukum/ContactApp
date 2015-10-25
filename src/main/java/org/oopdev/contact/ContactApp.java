package org.oopdev.contact;



import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.oopdev.contact.commands.ContactCommands;
import org.oopdev.contact.model.Contact;
import org.oopdev.contact.repository.ContactRepository;
import org.oopdev.contact.services.ContactServices;
import org.oopdev.contact.services.ContactShower;
import org.oopdev.contact.util.DbUtility;
import org.oopdev.contact.util.ValidationUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kamilbukum on 24/10/15.
 */
public class ContactApp {


    /**
     * Use Morphia Framework for Object operations.
     */
    final Morphia morphia = new Morphia();

    final Datastore dataStore;

    /**
     * Check Contact and get or change it by using ContactRepository
     */
    private final ContactServices contactServices;

    public ContactApp(DatabaseConfiguration databaseConfiguration){
        /**
         * Create new Database configuration
         */
        dataStore = DbUtility. createDataSource(morphia, databaseConfiguration);

        /**
         * Create new repository and inject it in Contact Services.
         */
        contactServices = new ContactServices(new ContactRepository(dataStore));
    }


    /**
     * Add Contact which obtain from the files.
     * @param contactsMap
     */
    public void addContacts(Map<String, List<Contact>> contactsMap){
        for(Map.Entry<String,List<Contact>> contacts:contactsMap.entrySet()){
            contactServices.addContacts(contacts.getValue());
            System.out.println(contacts.getKey() + " added to database.");
        }
    }

    /**
     * Check search commands name and surname.
     * if user want to search by name and surname then must type 2 words
     * otherwise always search name or surname contain text which user type.
     * @param contactCommands
     */
    public void search (ContactCommands contactCommands) {
        /**
         *
         *
         */
        List<String> keys  = contactCommands.getKeys();
        String key1;
        String key2;
        boolean bothOfThem=keys.size() > 1;
        if(bothOfThem){
            key1  = keys.get(0);
            key2  = keys.get(1);
        }else{
            key1 = keys.get(0);
            key2 = keys.get(0);
        }
        Contact contact = new Contact();
        contact.setName(key1);
        contact.setLastName(key2);
        List<Contact> contacts = contactServices.search(contact,bothOfThem);

        ContactShower.showTable(contacts);


    }

    /**
     * get all contacts from database
     */
    public void list() {
        List<Contact> contacts = contactServices.list();
        ContactShower.showTable(contacts);
    }


    /**
     * Select operation from command which operations must be execute.
     * @param commands
     * @param contactCommands
     */
    public void operate(List<String> commands, ContactCommands contactCommands) {
            String mainCommand = commands.get(0);
            if("-add".equals(mainCommand)){
                Map<String,List<Contact>> contactsMap=new LinkedHashMap<String, List<Contact>>();

                int i = 1 ;
                for(List<Contact> contacts:contactCommands.getContactsList()){
                    contactsMap.put(commands.get(i),contacts);
                    i++;
                }
                addContacts(contactsMap);
            }else if("-search".equals(mainCommand)){
                if(ValidationUtil.isEmpty(contactCommands.getKeys())){
                    list();
                }else{
                    search(contactCommands);
                }
            }else if("-list".equals(mainCommand)){
                list();
            }
    }
}
