package com.bytepoxic.core.model;

import java.util.Date;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findUserTracksByTracked", "findUserTracksByTrackingDate", "findUserTracksByTrackedAndTrackingDate" })
public class UserTrack {

    @ManyToOne
    private AppUser tracked;

    @NotNull
    @Enumerated
    private TrackingType trackingType;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date trackingDate;
}
