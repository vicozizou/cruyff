// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.model;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.model.Place;

privileged aspect Place_Roo_JavaBean {
    
    public String Place.getName() {
        return this.name;
    }
    
    public void Place.setName(String name) {
        this.name = name;
    }
    
    public Location Place.getLocation() {
        return this.location;
    }
    
    public void Place.setLocation(Location location) {
        this.location = location;
    }
    
    public String Place.getPrimaryAddress() {
        return this.primaryAddress;
    }
    
    public void Place.setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }
    
    public String Place.getSecondaryAddress() {
        return this.secondaryAddress;
    }
    
    public void Place.setSecondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }
    
    public Double Place.getLatitude() {
        return this.latitude;
    }
    
    public void Place.setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public Double Place.getLongitude() {
        return this.longitude;
    }
    
    public void Place.setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public Integer Place.getZoom() {
        return this.zoom;
    }
    
    public void Place.setZoom(Integer zoom) {
        this.zoom = zoom;
    }
    
}
