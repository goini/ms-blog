package com.ms.entity;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by max shemet on 11/2/2016.
 */
@Entity
@Table(name = "articles")
public class Article extends BaseEntity {

    @NotBlank(message = "title could not be empty")
    @Column(name = "title", nullable = false, columnDefinition = "varchar(255) default ''")
    private String title;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
