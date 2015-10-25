package org.oopdev.contact.model;

import org.mongodb.morphia.annotations.*;

import javax.xml.parsers.SAXParser;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kamilbukum on 24/10/15.
 */
@Entity("contacts")
@Indexes(
        @Index(value = "CONTACT_NAME_INDEX", fields = {@Field("name"),@Field("lastName")})
)
public class Contact extends BaseEntity{


    @Property("name")
    private String name;
    @Property("lastName")
    private String lastName;
    @Property("phones")
    private Set<String> phones = new HashSet<String>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;
        return !(phones != null ? !phones.equals(contact.phones) : contact.phones != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        return result;
    }
}
