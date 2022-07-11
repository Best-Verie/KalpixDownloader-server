package com.kalpix.downloader.backend.controllers;

import com.kalpix.downloader.backend.dtos.UrlRequest;
import com.kalpix.downloader.backend.models.Link;
import com.kalpix.downloader.backend.models.Website;
import com.kalpix.downloader.backend.serviceImpl.LinkServiceImpl;
import com.kalpix.downloader.backend.serviceImpl.WebsiteExtractorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/websites-links")
public class LinkController {

    @Autowired
    private LinkServiceImpl linkService;

    @Autowired
    private WebsiteExtractorServiceImpl websiteExtractorService;

    @GetMapping("/get-all")
    public List<Link> getAll() {
        return linkService.getAll();
    }
    @GetMapping("/{id}")
    public Website findById(@PathVariable UUID id) {
        return null;
    }

    @PostMapping("/save-links")
    public ResponseEntity<?> create(@RequestBody UrlRequest urlRequest) {


        return ResponseEntity.created(null).build();
    }



}
