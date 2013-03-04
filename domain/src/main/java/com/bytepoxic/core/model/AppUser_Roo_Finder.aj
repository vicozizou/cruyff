// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.AppUser;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect AppUser_Roo_Finder {
    
    public static TypedQuery<AppUser> AppUser.findAppUsersByUsername(String username) {
        if (username == null || username.length() == 0) throw new IllegalArgumentException("The username argument is required");
        EntityManager em = AppUser.entityManager();
        TypedQuery<AppUser> q = em.createQuery("SELECT o FROM AppUser AS o WHERE o.username = :username", AppUser.class);
        q.setParameter("username", username);
        return q;
    }
    
}
