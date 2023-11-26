package com.frei.spacefilemanager.entities;

import java.util.Date;

public class FileDetail {
    private String name;
    private long size;
    private Date lastModified;

    public FileDetail(String name, long size, Date lastModified) {
        this.name = name;
        this.size = size;
        this.lastModified = lastModified;
    }

    // Getters
    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public Date getLastModified() {
        return lastModified;
    }
}

