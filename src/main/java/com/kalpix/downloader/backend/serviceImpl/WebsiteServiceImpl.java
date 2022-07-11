package com.kalpix.downloader.backend.serviceImpl;

import com.kalpix.downloader.backend.exceptions.WebsiteNotFoundException;
import com.kalpix.downloader.backend.models.Website;
import com.kalpix.downloader.backend.repositories.WebsiteRepository;
import com.kalpix.downloader.backend.services.WebsiteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WebsiteServiceImpl implements WebsiteServices {

    @Autowired
    private WebsiteRepository websiteRepository;

    @Override
    public List<Website> findAll() {
        return websiteRepository.findAll();
    }

    public Website checkExistence(UUID id) {
        Optional<Website> patientFound = websiteRepository.findById(id);

        if(!patientFound.isPresent()) throw new WebsiteNotFoundException("website with id " + id+ " is not found");

        return patientFound.get();
    }

    // get by websiteName
    public Website findByWebsiteName(String websiteName) {
        return websiteRepository.findByWebsiteName(websiteName);
    }

    @Override
    public Website findById(UUID id) {
        return checkExistence(id);
    }

    @Override
    public Website create(Website website) {

        Website websitSaved = new Website(
                website.getWebsiteName(),
                website.getDownloadStartTime(),
                website.getDownloadEndTime(),
                website.getTotalElapsedTime(),
                website.getTotalDownloadedFilesKilobytes()
        );

        return websiteRepository.save(websitSaved);
    }

    @Override
    public Website delete(UUID id) {
        return null;
    }


}
