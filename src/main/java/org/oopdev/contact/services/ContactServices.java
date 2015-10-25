package org.oopdev.contact.services;

import org.oopdev.contact.model.Contact;
import org.oopdev.contact.repository.ContactRepository;

import java.util.List;

/**
 * Created by kamilbukum on 24/10/15.
 */
public class ContactServices {

    /**
     * Using operate Contact changes on Database
     */
    private final ContactRepository contactRepository;
    public ContactServices(ContactRepository contactRepository){
        this.contactRepository = contactRepository ;
    }

    /**
     * Add Contacts to Database
     * @param contactList
     */
    public void addContacts(List<Contact> contactList){
        for(Contact contact:contactList){
            addContact(contact);
        }
    }

    /**
     * Search Contact name and surname. if Contact exist on Database then update it
     * else add new contact to database.
     * @param contact
     */
    public void addContact(Contact contact){

        Contact qContact  = contactRepository.findByNameAndLastName(contact.getName(), contact.getLastName());

        if(qContact != null ){
            qContact.getPhones().addAll(contact.getPhones());
            contact  = qContact;
        }
        contactRepository.save(contact);
    }

    public List<Contact> search(Contact contact,boolean bothOfThem) {
        List<Contact> contacts = contactRepository.likeByNameLastName(contact.getName(), contact.getLastName(), bothOfThem);
        return contacts;
    }

    public List<Contact> list() {
        return contactRepository.list();
    }
}
