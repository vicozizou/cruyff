// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.AppUser;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AppUser_Roo_Jpa_ActiveRecord {
    
    public static long AppUser.countAppUsers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM AppUser o", Long.class).getSingleResult();
    }
    
    public static List<AppUser> AppUser.findAllAppUsers() {
        return entityManager().createQuery("SELECT o FROM AppUser o", AppUser.class).getResultList();
    }
    
    public static AppUser AppUser.findAppUser(Long id) {
        if (id == null) return null;
        return entityManager().find(AppUser.class, id);
    }
    
    public static List<AppUser> AppUser.findAppUserEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM AppUser o", AppUser.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public AppUser AppUser.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        AppUser merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
