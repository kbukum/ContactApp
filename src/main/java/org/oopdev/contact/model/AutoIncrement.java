package org.oopdev.contact.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by kamilbukum on 25/10/15.
 */
@Entity(noClassnameStored = true)
public class AutoIncrement {

    @Id
    protected String key;

    protected Long value = 0L;

    protected AutoIncrement() {
        super();
    }

    /**
     * Set the key name — class or class with some other attribute(s).
     */
    public AutoIncrement(final String key) {
        this.key = key;
    }

    /**
     * Set the key name and initialize the value so it won't start at 1.
     */
    public AutoIncrement(final String key, final Long startValue) {
        this(key);
        value = startValue;
    }

    public Long getValue() {
        return value;
    }

    public Long increase(){
        value = value + 1;
        return value;
    }
}