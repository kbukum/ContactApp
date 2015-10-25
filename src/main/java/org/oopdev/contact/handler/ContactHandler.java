package org.oopdev.contact.handler;

import org.oopdev.contact.model.Contact;
import org.oopdev.contact.util.ValidationUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by kamilbukum on 24/10/15.
 */
public class ContactHandler extends DefaultHandler implements XMLHandler<Contact> {

    private List<Contact> contactList=new LinkedList<Contact>();
    private Contact tempContact=null;
    private String tempField=null;

    public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
        if(tempContact!=null){
            tempField = qName.toLowerCase();
        }
        if("contact".equalsIgnoreCase(qName)){
            tempContact=new Contact();
        }

    }

    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        if("contact".equalsIgnoreCase(qName)){
            contactList.add(tempContact);
        }
        tempField = null;
    }

    public void characters(char ch[], int start, int length) throws SAXException {

        if(tempContact!=null && tempField != null) {
            String value = new String(ch, start, length);
            if (tempField.equals("name")) {
                    tempContact.setName(value);
            } else if (tempField.equals("lastname")) {
                    tempContact.setLastName(value);
            } else if (tempField.equals("phones")) {
                    String[] values = value.split(",");
                    for(String phone:values){
                        if(!ValidationUtil.isEmpty(phone)){
                            phone = phone.replaceAll("[^0-9]","");
                            tempContact.getPhones().add(phone);
                        }

                    }
            }
        }
    }
    public List<Contact> getResultList() {
        return contactList;
    }

}
