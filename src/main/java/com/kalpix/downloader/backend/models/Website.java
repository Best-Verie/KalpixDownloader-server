package com.kalpix.downloader.backend.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table( name = "websites_tbl" )
public class Website {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID id;

    private String websiteName;

    private Timestamp downloadStartTime;

    private Timestamp downloadEndTime;

    private long totalElapsedTime;

    private long totalDownloadedFilesKilobytes;

    public Website() {
    }

    public Website(String websiteName, Timestamp downloadStartTime, Timestamp downloadEndTime, long totalElapsedTime, long totalDownloadedFilesKilobytes) {
        this.websiteName = websiteName;
        this.downloadStartTime = downloadStartTime;
        this.downloadEndTime = downloadEndTime;
        this.totalElapsedTime = totalElapsedTime;
        this.totalDownloadedFilesKilobytes = totalDownloadedFilesKilobytes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public Timestamp getDownloadStartTime() {
        return downloadStartTime;
    }

    public void setDownloadStartTime(Timestamp downloadStartTime) {
        this.downloadStartTime = downloadStartTime;
    }

    public Timestamp getDownloadEndTime() {
        return downloadEndTime;
    }

    public void setDownloadEndTime(Timestamp downloadEndTime) {
        this.downloadEndTime = downloadEndTime;
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
