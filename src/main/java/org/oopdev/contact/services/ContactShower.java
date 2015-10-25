package org.oopdev.contact.services;

import dnl.utils.text.table.TextTable;
import org.oopdev.contact.model.Contact;
import org.oopdev.contact.util.ValidationUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kamilbukum on 25/10/15.
 */
public class ContactShower {

    public static void showTable(List<Contact> contactList) {
        String[] columnNames = {
                "ID",
                "Name",
                "LastName",
                "Phones"
        };

        Object[][] data = new Object[contactList.size()][4];
        for( int i=0 ; i<contactList.size() ; i++ ){
            Contact contact = contactList.get(i);
            data[i][0] = contact.getId() ;
            data[i][1] = contact.getName() ;
            data[i][2] = contact.getLastName() ;
            data[i][3] = maskPhones(contact.getPhones()) ;

        }

        TextTable tt = new TextTable(columnNames, data);
// this adds the numbering on the left
        tt.setAddRowNumbering(true);
// sort by the first column
        tt.setSort(0);
        tt.printTable();
    }
    public static Set<String> maskPhones(Set<String> phones){
       Set<String> tempPhones = new HashSet<String>();
      if(phones != null && phones.size() > 0){
          for(String phone : phones){
                  tempPhones.add(maskPhone(phone));
          }
      }
        return tempPhones;
    }
    private static String maskPhone(String phone){
        if(ValidationUtil.isEmpty(phone)){
            return "";
        }
        String newPhone =  "";
        if(phone.length()-2 >= 0){
            newPhone = " "+phone.substring(phone.length()-2,phone.length());
        }

        if(phone.length()-4 >= 0){
            newPhone = " "+phone.substring(phone.length()-4,phone.length()-2) + newPhone;
        }
        if(phone.length()-7 >= 0){
            newPhone = " "+phone.substring(phone.length()-7,phone.length()-4) + newPhone;
        }
        if(phone.length()-10 >= 0){
            newPhone = " "+phone.substring(phone.length()-10,phone.length()-7) + newPhone;
        }
        newPhone = " "+phone.substring(0,phone.length()-10) + newPhone;
        return newPhone ;
    }
}
