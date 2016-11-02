package com.ms.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by max shemet on 11/2/2016.
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable = false)
    private Calendar created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date", nullable = false)
    private Calendar updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getCreated() {
        return created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    @PrePersist
    protected void onCreate() {
        updated = created = Calendar.getInstance();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = Calendar.getInstance();
    }
}
