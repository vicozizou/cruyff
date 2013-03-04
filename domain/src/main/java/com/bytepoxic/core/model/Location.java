package com.bytepoxic.core.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findLocationsByParent", "findLocationsByName", "findLocationsByCode" })
public class Location extends BaseEntity {

    @ManyToOne
    private com.bytepoxic.core.model.Location parent;

    @NotNull
    @Size(max = 64)
    private String name;

    @Column(unique = true)
    @Size(max = 2)
    private String code;

    @NotNull
    private Boolean appCurrent;

    private Double latitude;

    private Double longitude;

    private Integer zoom;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Set<com.bytepoxic.core.model.Location> children = new HashSet<com.bytepoxic.core.model.Location>();

    @ManyToOne
    @JoinColumn(name = "country")
    private Nationality nationality;
}
