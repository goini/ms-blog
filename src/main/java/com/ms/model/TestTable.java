package com.ms.model;

import javax.persistence.*;

/**
 * Created by mshemet on 1/11/2016.
 */
@Entity
@Table(name = "test_table")
public class TestTable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
