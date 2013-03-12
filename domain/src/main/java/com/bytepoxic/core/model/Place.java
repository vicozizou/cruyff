package com.bytepoxic.core.model;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Place {

    @Size(max = 64)
    private String name;

    @NotNull
    @ManyToOne
    private Location location;

    @NotNull
    @Size(max = 256)
    private String primaryAddress;

    @Size(max = 256)
    private String secondaryAddress;

    private Double latitude;

    private Double longitude;

    private Integer zoom;
}
