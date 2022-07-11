package com.kalpix.downloader.backend.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "website_links_tbl")
@Entity
public class Link {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID id;

    private String linkName;

    @ManyToOne
    private Website  website;

    private long totalElapsedTime;

    private long totalDownloadedFilesKilobytes;

    public Link() {
    }

    public Link(String linkName, Website website, long totalElapsedTime, long totalDownloadedFilesKilobytes) {
        this.linkName = linkName;
        this.website = website;
        this.totalElapsedTime = totalElapsedTime;
        this.totalDownloadedFilesKilobytes = totalDownloadedFilesKilobytes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public long getTotalElapsedTime() {
        return totalElapsedTime;
    }

    public void setTotalElapsedTime(long totalElapsedTime) {
        this.totalElapsedTime = totalElapsedTime;
    }

    public long getTotalDownloadedFilesKilobytes() {
        return totalDownloadedFilesKilobytes;
    }

    public void setTotalDownloadedFilesKilobytes(long totalDownloadedFilesKilobytes) {
        this.totalDownloadedFilesKilobytes = totalDownloadedFilesKilobytes;
    }
}
