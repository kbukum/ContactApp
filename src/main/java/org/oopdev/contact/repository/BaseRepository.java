package org.oopdev.contact.repository;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.oopdev.contact.model.AutoIncrement;
import org.oopdev.contact.model.BaseEntity;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by kamilbukum on 25/10/15.
 */
public class BaseRepository<T extends BaseEntity>{

    private final org.mongodb.morphia.Datastore dataStore;

    final Class<T> clazz ;

    public BaseRepository(org.mongodb.morphia.Datastore dataStore){
        this.dataStore = dataStore;
        clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public long count(){
       Query<T> query = dataStore.createQuery(clazz);
       return query.countAll();
    }

    public void save(T object){

        if(object.getId() <= 0){
            AutoIncrement autoIncrement = dataStore
                    .createQuery(AutoIncrement.class)
                    .field("key").equal(object.getClass().getName()).get();
            if(autoIncrement == null){
                autoIncrement=new AutoIncrement(object.getClass().getName());
            }

            object.setId(autoIncrement.increase());
            dataStore.save(autoIncrement);
        }
        getDataStore().save(object);

    }
    public List<T> list() {
        return getDataStore().createQuery(clazz).asList();
    }

    public Datastore getDataStore() {
        return dataStore;
    }

}
