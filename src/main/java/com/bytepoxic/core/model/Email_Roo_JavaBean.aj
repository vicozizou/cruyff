// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.Email;
import com.bytepoxic.core.model.EmailType;
import com.bytepoxic.core.model.Person;

privileged aspect Email_Roo_JavaBean {
    
    public String Email.getEmailAddress() {
        return this.emailAddress;
    }
    
    public void Email.setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public EmailType Email.getType() {
        return this.type;
    }
    
    public void Email.setType(EmailType type) {
        this.type = type;
    }
    
    public Person Email.getOwner() {
        return this.owner;
    }
    
    public void Email.setOwner(Person owner) {
        this.owner = owner;
    }
    
}