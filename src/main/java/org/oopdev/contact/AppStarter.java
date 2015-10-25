package org.oopdev.contact;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.oopdev.contact.commands.ContactCommands;
import org.oopdev.contact.util.Utility;
import org.oopdev.contact.util.ValidationUtil;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by kamilbukum on 24/10/15.
 */
public class AppStarter {


    /**
     * Application Configuration properties.
     */
    public static final String APP_PROPERTY_PATH = "app.properties" ;
    public static final String CMD_CURRENT_PATH = "cmd.current.path";
    public static final String DEFAULT_DATABASE_NAME_KEY="app.db.name";

    private static boolean exit = false;


    public static void main(String[] args) {


        try {

            /**
             * Load Application Properties
             */
            Utility.loadPropertyToSystemProperty(ContactApp.class.getClassLoader().getResourceAsStream(APP_PROPERTY_PATH));


            /**
             * read main arguments and get Mongo DB information.
             */
            DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
            new JCommander(databaseConfiguration, args);

            System.out.print(databaseConfiguration.getHost());
            System.out.print(databaseConfiguration.getPort());

            if(ValidationUtil.isEmpty(databaseConfiguration.getDb())){
                databaseConfiguration.setDb(System.getProperty(DEFAULT_DATABASE_NAME_KEY));
                System.out.println("Set Default Database ! Database Name : " +databaseConfiguration.getDb());
            }else{
                System.out.print(databaseConfiguration.getDb());
            }


            ContactApp contactApp = new ContactApp(databaseConfiguration);


            /**
             * Get Current Path
             */
            {
                System.setProperty(CMD_CURRENT_PATH, new File("").getAbsolutePath());
            }

            System.out.println(MessageFormat.format(System.getProperty("app.mongo.connected"), databaseConfiguration.getHost(), databaseConfiguration.getPort().toString(), databaseConfiguration.getDb()));

            /**
             * Repeat listen commands and execute them
             */
            String typeOfConsoleStart = "\n-" + System.getProperty(CMD_CURRENT_PATH) + "$ > \n";

            while (!exit) {
                System.out.println( typeOfConsoleStart );
                List<String> commands = Utility.readCommandFromCLI();
                try {

                    if(commands.size() == 0){
                        System.err.println("Please type an command . --help ?");
                    }
                    String mainCommand = commands.get(0);
                    if (mainCommand.equals("quit")||mainCommand.equals("-quit")) {
                        exit = true;
                        break;
                    }
                    /**
                     * Read Commands
                     */
                    ContactCommands contactCommands = new ContactCommands();
                    new JCommander(contactCommands, commands.toArray(new String[]{}));

                    /**
                     * help commands for see how use commands
                     */
                    if(mainCommand.equals("--help")){
                        System.out.println(contactCommands.toString());
                        continue;
                    }else{
                        /**
                         * operate commands
                         */
                        contactApp.operate(commands, contactCommands);
                    }
                } catch (ParameterException pe) {
                    System.err.println(pe.getLocalizedMessage());
                } catch (Exception e){
                    System.err.println(e.getLocalizedMessage());
                }

            }

            System.out.println("Exit from " + System.getProperty("app.name") + " application");

        } catch (ParameterException pe) {
            System.out.println(pe.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
