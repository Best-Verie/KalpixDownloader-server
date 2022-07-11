package com.kalpix.downloader.backend.serviceImpl;

import com.kalpix.downloader.backend.models.Link;
import com.kalpix.downloader.backend.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private WebsiteServiceImpl websiteService;

    // save list of links to database
    public void saveLinks(List<Link> links) {
        for (Link link : links) {
            linkRepository.save(link);
        }
    }

    // get all
    public List<Link> getAll() {
        return linkRepository.findAll();
    }
}
