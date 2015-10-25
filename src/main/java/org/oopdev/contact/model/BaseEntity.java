package org.oopdev.contact.model;

import org.mongodb.morphia.annotations.Id;

/**
 * Created by kamilbukum on 25/10/15.
 */

public class BaseEntity {
    @Id
    private Long id  = 0L;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
