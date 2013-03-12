package com.bytepoxic.core.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findAppRolesByName" })
public class AppRole extends I18NableEntity {

    @NotNull
    @Column(unique = true)
    @Size(max = 32)
    private String name;

    @Size(max = 128)
    private String description;
}
