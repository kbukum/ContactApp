package org.oopdev.contact;

import com.beust.jcommander.Parameter;


/**
 * Created by kamilbukum on 24/10/15.
 */
public class DatabaseConfiguration {

    public static final String SCAN_MODEL_PACKAGE =  "app.scan.package";

    @Parameter(names = { "-host"}, description = "URL for connect Mongo")
    private String host = "localhost";

    @Parameter(names = "-port", description = "Mongo DB port")
    private Integer port = 27017;

    @Parameter(names = "-db" , description = "Mongo Database Name ")
    private String db;


    public Integer getPort() {
        return port;
    }

    public String getDb() {
        return db;
    }

    public String getHost() {
        return host;
    }


    public String  getScanPackage() {
         return System.getProperty(SCAN_MODEL_PACKAGE);
    }

    public void setDb(String db) {
        this.db = db;
    }
}
