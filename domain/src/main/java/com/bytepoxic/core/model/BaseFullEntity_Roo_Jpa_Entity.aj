// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.BaseFullEntity;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

privileged aspect BaseFullEntity_Roo_Jpa_Entity {
    
    declare @type: BaseFullEntity: @Entity;
    
    declare @type: BaseFullEntity: @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS);
    
}
