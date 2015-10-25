package org.oopdev.contact.handler;

import org.oopdev.contact.model.Contact;

import java.util.List;

/**
 * Created by kamilbukum on 24/10/15.
 */
public interface XMLHandler<T>{
    List<T> getResultList();
}
