package com.bytepoxic.core.model;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public abstract class NameValueSetting extends I18NableEntity {

    @NotNull
    @Column(unique = true)
    @Size(max = 64)
    private String name;

    @Size(max = 128)
    private String theValue;

    @NotNull
    @Enumerated
    private FieldType type;
}
