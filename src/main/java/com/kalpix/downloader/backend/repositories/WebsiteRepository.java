package com.kalpix.downloader.backend.repositories;

import com.kalpix.downloader.backend.models.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, UUID> {
    // get web with websiteName
    public Website findByWebsiteName(String websiteName);
}
