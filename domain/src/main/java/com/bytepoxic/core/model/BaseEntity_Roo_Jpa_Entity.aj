// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

privileged aspect BaseEntity_Roo_Jpa_Entity {
    
    declare @type: BaseEntity: @Entity;
    
    declare @type: BaseEntity: @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS);
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long BaseEntity.id;
    
    @Version
    @Column(name = "version")
    private Integer BaseEntity.version;
    
    public Long BaseEntity.getId() {
        return this.id;
    }
    
    public void BaseEntity.setId(Long id) {
        this.id = id;
    }
    
    public Integer BaseEntity.getVersion() {
        return this.version;
    }
    
    public void BaseEntity.setVersion(Integer version) {
        this.version = version;
    }
    
}
