package com.bytepoxic.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findAppUsersByUsername" })
public class AppUser extends Person {

    @NotNull
    @Column(unique = true)
    @Size(max = 32)
    private String username;

    @NotNull
    @Size(max = 64)
    private String password;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<AppRole> roles = new HashSet<AppRole>();

    @Enumerated
    private UserStatus userStatus;

    private Integer loginAttempts;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lastLoginDate;

    private Integer daysToExpire;
}
