package org.oopdev.contact.converters;


import com.beust.jcommander.IStringConverter;
import org.oopdev.contact.AppStarter;
import org.oopdev.contact.handler.ContactHandler;
import org.oopdev.contact.model.Contact;
import org.oopdev.contact.util.Utility;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Created by kamilbukum on 22/10/15.
 */
public class XMLConverter implements IStringConverter<List<Contact>> {

    public List<Contact> convert(String value){


        try {
            String filePath = Utility.filePathFinder(System.getProperty(AppStarter.CMD_CURRENT_PATH),value);
            File file=new File(filePath);
            FileInputStream inputStream = new FileInputStream(file);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ContactHandler handler=new ContactHandler();
            saxParser.parse(inputStream,handler);
            return  handler.getResultList();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
