package org.oopdev.contact.util;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.oopdev.contact.DatabaseConfiguration;

/**
 * Created by kamilbukum on 24/10/15.
 */
public class DbUtility {

    /**
     * Create Mongo DataSource for connect Database.
     * @param morphia
     * @param databaseConfiguration
     * @return
     */
    public static Datastore createDataSource(final Morphia morphia, DatabaseConfiguration databaseConfiguration){
        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage(databaseConfiguration.getScanPackage());
        /**
         * Connect Mongo Database
         */
        MongoClient mongoClient = new MongoClient(databaseConfiguration.getHost() , databaseConfiguration.getPort());

        // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(mongoClient, databaseConfiguration.getDb());
        datastore.ensureIndexes();
        return  datastore ;

    }
}
