package org.oopdev.contact.repository;


import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.oopdev.contact.model.Contact;

import java.util.List;

/**
 * Created by kamilbukum on 24/10/15.
 */
public class ContactRepository extends BaseRepository<Contact>{
        public ContactRepository(org.mongodb.morphia.Datastore dataStore){
            super(dataStore);
        }

    public Contact findByNameAndLastName(String name,String lastName){
        Criteria[] arrayB = {
                getDataStore().createQuery(Contact.class).criteria("name").equal(name),
                getDataStore().createQuery(Contact.class).criteria("lastName").equal(lastName)
        };
        Query<Contact> query = getDataStore().createQuery(Contact.class);
        query.and(arrayB);
        Contact queryContact = query.get();
        return queryContact;
    }

    public List<Contact> likeByNameLastName(String name,String lastName,boolean bothOfThem){
        Criteria[] arrayB = {
                getDataStore().createQuery(Contact.class).criteria("name").containsIgnoreCase(name),
                getDataStore().createQuery(Contact.class).criteria("lastName").containsIgnoreCase(lastName)
        };

        Query q = getDataStore().createQuery(Contact.class);
        if(bothOfThem){
            q.and(arrayB);
        }else{
            q.or(arrayB);
        }
        return q.asList();
    }

}
