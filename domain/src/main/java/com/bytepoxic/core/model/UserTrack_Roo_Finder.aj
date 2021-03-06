// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.UserTrack;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect UserTrack_Roo_Finder {
    
    public static TypedQuery<UserTrack> UserTrack.findUserTracksByTracked(AppUser tracked) {
        if (tracked == null) throw new IllegalArgumentException("The tracked argument is required");
        EntityManager em = UserTrack.entityManager();
        TypedQuery<UserTrack> q = em.createQuery("SELECT o FROM UserTrack AS o WHERE o.tracked = :tracked", UserTrack.class);
        q.setParameter("tracked", tracked);
        return q;
    }
    
    public static TypedQuery<UserTrack> UserTrack.findUserTracksByTrackedAndTrackingDate(AppUser tracked, Date trackingDate) {
        if (tracked == null) throw new IllegalArgumentException("The tracked argument is required");
        if (trackingDate == null) throw new IllegalArgumentException("The trackingDate argument is required");
        EntityManager em = UserTrack.entityManager();
        TypedQuery<UserTrack> q = em.createQuery("SELECT o FROM UserTrack AS o WHERE o.tracked = :tracked AND o.trackingDate = :trackingDate", UserTrack.class);
        q.setParameter("tracked", tracked);
        q.setParameter("trackingDate", trackingDate);
        return q;
    }
    
    public static TypedQuery<UserTrack> UserTrack.findUserTracksByTrackingDate(Date trackingDate) {
        if (trackingDate == null) throw new IllegalArgumentException("The trackingDate argument is required");
        EntityManager em = UserTrack.entityManager();
        TypedQuery<UserTrack> q = em.createQuery("SELECT o FROM UserTrack AS o WHERE o.trackingDate = :trackingDate", UserTrack.class);
        q.setParameter("trackingDate", trackingDate);
        return q;
    }
    
}
