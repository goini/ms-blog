package com.ms.filter;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

/**
 * Created by go in on 01.11.2016.
 */
public class CatFilter {
    private String name, color;
    private int id;

    public Criteria prepare(Criteria criteria) {
        if(!StringUtils.isEmpty(getColor())) {
            criteria.add(Restrictions.eq("color", getColor()));
        }
        if(!StringUtils.isEmpty(getName())) {
            criteria.add(Restrictions.eq("name", getName()));
        }
        if(getId() > 0) {
            criteria.add(Restrictions.eq("id", getId()));
        }
        return criteria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
