package com.kalpix.downloader.backend.serviceImpl;

import com.kalpix.downloader.backend.Properties.WebsiteExtractor;
import com.kalpix.downloader.backend.dtos.UrlRequest;
import com.kalpix.downloader.backend.models.Link;
import com.kalpix.downloader.backend.models.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class WebsiteExtractorServiceImpl {

    @Autowired
    private LinkServiceImpl linkService;

    @Autowired
    private WebsiteServiceImpl websiteService;

    @Autowired
    private WebsiteExtractor extractor;

    public void extractWebsite(UrlRequest urlRequest) {
        // TODO

        // download home page

        extractor.downloadWebsite(urlRequest.getUrlName());

        Long datetime = System.currentTimeMillis();
        Timestamp startTime = new Timestamp(datetime);

        // extract links from home page
        Timestamp endTime = extractor.extractLinks(urlRequest.getUrlName());

        // count total size
        long totalSize =   extractor.countTotalKilobytes("src/main/java/downloadedPages/"+new WebsiteExtractor().extractWebname(urlRequest.getUrlName()));

        // time ellapsed between start time  and end time
        long timeEllapsed = endTime.getTime() - startTime.getTime();

        // save to database

        Website website = new Website();
        website.setWebsiteName(urlRequest.getUrlName());
        website.setDownloadStartTime(startTime);
        website.setDownloadEndTime(endTime);
        website.setTotalDownloadedFilesKilobytes( totalSize);
        website.setTotalElapsedTime(timeEllapsed);

        websiteService.create(website);

        //  save links to database

        Website websiteFound = websiteService.findByWebsiteName(urlRequest.getUrlName());

        List<Link> links = extractor.saveLinks(urlRequest.getUrlName());

        for (Link link : links) {
            link.setWebsite(websiteFound);
        }

        linkService.saveLinks(links);

    }
}
