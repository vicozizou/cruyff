package com.bytepoxic.core.model;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Email {

    @NotNull
    @Column(unique = true)
    @Size(max = 64)
    private String emailAddress;

    @NotNull
    @Enumerated
    private EmailType type;

    @ManyToOne
    private Person owner;
}
