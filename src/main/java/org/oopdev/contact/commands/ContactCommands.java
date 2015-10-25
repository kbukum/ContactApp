package org.oopdev.contact.commands;

import com.beust.jcommander.Parameter;
import org.oopdev.contact.converters.XMLConverter;
import org.oopdev.contact.model.Contact;

import java.util.*;

/**
 * Created by kamilbukum on 21/10/15.
 */
public class ContactCommands {

    @Parameter(names = "--help", help = true)
    private boolean help;
    @Parameter(names = "-add", description = "Added files List",variableArity = true,converter = XMLConverter.class)

    private List<List<Contact>> contactsList=new LinkedList<List<Contact>>();

    @Parameter(names = "-search", description = "Search By Name",variableArity = true)
    private List<String> keys;

    @Parameter(names = "-list", description = "List All Contacts")
    private boolean list;

    public boolean isHelp() {
        return help;
    }

    public boolean isList() {
        return list;
    }

    public List<List<Contact>> getContactsList() {
        return contactsList;
    }

    public List<String> getKeys() {
        return keys;
    }


    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public void setContactsList(List<List<Contact>> contactsList) {
        this.contactsList = contactsList;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "usage: [--help] [-add file1[,file2[,...]]] \n" +
                "      [--list] [-search (name or lastName) or name,lastName \n" +
                "      [--quit] "+
                "The most commonly used git commands are:\n" +
                "   -add       Add file contents to the contacts database\n" +
                "   -search    Find Contacts by name or lastName \n" +
                "   -list      List,all contacts\n" +
                "   -quit      exit from Application\n" +
                "\n";
    }
}
