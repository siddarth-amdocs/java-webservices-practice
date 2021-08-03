package com.animasugit.rest.webservices.entity;

import com.animasugit.rest.webservices.component.Name;

public class PersonV2 {
    private Name name;


    public PersonV2() {
    }

    public PersonV2(Name name) {
        this.name = name;
    }

    public Name getName() {
        return this.name;
    }

    public void setName(Name name) {
        this.name = name;
    }

}
