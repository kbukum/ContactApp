package org.oopdev.contact.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kamilbukum on 24/10/15.
 */
public class Utility {

    /**
     * Regex for parse commands
     */
    private static final String regExp = "\"(\\\"|[^\"])*?\"|[^ ]+";

    /**
     * init command regex
     */

    private static final Pattern pattern = Pattern.compile( regExp, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE );

    /**
     * Parse Command Like parse it "-add file1 file2" and get list {"-add","file1","file2"}
     * @param cmd
     * @return
     */
    public static List<String> parseCommand( String cmd )
    {
        List< String > matches = new ArrayList<String>();
        if( cmd == null || cmd.length() == 0 ) {
            return matches;
        }
        cmd = cmd.trim();
        Matcher matcher = pattern.matcher( cmd );

        while( matcher.find() ) {
            String value = matcher.group();
            if(value.length()>1&&value.startsWith("\"")&&value.endsWith("\"")){
                value = value.substring(1,value.length()-1);
            }
            matches.add(value);
        }
        return matches;
    }

    /**
     * Load Properties from InputStream to System Property.
     * @param is
     * @throws IOException
     */
    public static void loadPropertyToSystemProperty(InputStream is) throws IOException {
        Properties appProperties;
        appProperties=new Properties();
        appProperties.load(is);

        /**
         * Write All Application Properties
         */

        for(Enumeration<String> prop = (Enumeration<String>) appProperties.propertyNames();prop.hasMoreElements();){
            String key = prop.nextElement();
            System.setProperty(key,appProperties.getProperty(key));
            System.out.println(key+" = " + appProperties.getProperty(key));
        }
    }


    /**
     * Read Command Line
     * @return
     */
    public static List<String> readCommandFromCLI(){
        Scanner scan = new Scanner(System.in);
        return Utility.parseCommand(scan.nextLine());
    }

    public static String filePathFinder(String currentPath,String filePath) throws FileNotFoundException{
           if(ValidationUtil.isEmpty(filePath)){
               return  null;
           }

            if(filePath.startsWith("/")){
                return  filePath;
            }


            int topNode = 0;

            while (filePath.startsWith("../")){
                topNode = topNode + 1;
                filePath = new String(filePath.toCharArray(), 3, filePath.length()-3);
            }

            if(topNode > 0) {
                String[] currentPaths = currentPath.split("/");


                if(topNode > currentPaths.length){
                    throw new FileNotFoundException("Root path not found for "+filePath) ;
                }

                for(int i = 1 ; i <= topNode ; i++){
                    filePath=currentPaths[currentPaths.length-i] + "/" + filePath;
                }

                return filePath;
            }
        return currentPath + "/" + filePath ;
    }
}
