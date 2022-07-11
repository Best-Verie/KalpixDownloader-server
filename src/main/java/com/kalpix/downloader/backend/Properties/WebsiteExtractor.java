package com.kalpix.downloader.backend.Properties;

import com.kalpix.downloader.backend.models.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebsiteExtractor {

    // extract links from a file
    public Timestamp extractLinks(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a[href]");
            for(Element link : links) {

                // download the content of the link
                System.out.println("link: " + link.attr("href"));
                downloadWebsite(link.attr("href"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // now timestamp
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        return timestamp;

    }


    public List<Link> saveLinks(String url) {
        List<Link> linksSaved =  new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a[href]");
            for(Element link : links) {

                // download the content of the link
                System.out.println("link: " + link.attr("href"));

                long totalSize =   new WebsiteExtractor().countTotalKilobytes("src/main/java/downloadedPages/"+new WebsiteExtractor().extractWebname(url)+"/"+link.attr("href"));

                // save to database
                Link linkFound = new Link();
                linkFound.setLinkName(link.attr("href"));
                linkFound.setTotalElapsedTime(7);
                linkFound.setTotalDownloadedFilesKilobytes(totalSize);

                linksSaved.add(linkFound);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // now timestamp
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        return linksSaved;

    }

    // extract a filename from url
    public String extractFilename(String url) {
        String filename = url.substring(url.lastIndexOf('/') + 1);

        // check if the filename contains a extension
        if(filename.contains(".")) {
            return filename;
        } else {
            return "";
        }
    }

    // extract a path to url
    public String extractWebname(String url) {
        String webname = url.substring(url.indexOf('.') + 1);
        webname = webname.substring(0, webname.indexOf('.'));
        return webname;
    }

    //extract a path from url
    public String extractPath(String url) throws MalformedURLException {

        String path = new URL(url).getPath();


        // check if the path is equal /
        if(! path.equals("/")) {
            path = path.replace("/", "-");
        }
        // convert to commel case

        path = path.toUpperCase();
        return path;
    }

    // count total kilobytes of files in certain directory
    public long  countTotalKilobytes(String path) {
        long totalKilobytes = 0;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                totalKilobytes += listOfFiles[i].length();
            }
        }
        return totalKilobytes;
    }



    public void downloadWebsite(String webpage) {
        try {

            // Create URL object
            URL url = new URL(webpage);
            BufferedReader readr =
                    new BufferedReader(new InputStreamReader(url.openStream()));

            //extract a file name
            String filename = extractFilename(webpage);

            if(filename.equals("")) {
                filename = "index.html";
            }


            String folderName = "src/main/java/downloadedPages/" + extractWebname(webpage) + "/" + extractPath(webpage);
            // get folder name

            // replace - with / in folder name
            folderName = folderName.replace("/-/", "/");

            // create a folder if it doesn't exist
            File folder = new File( folderName );
            if(!folder.exists()) {

                folder.mkdir();
                System.out.println("reached here" + folderName);
            }


            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(folderName + "/"+ filename ));

            File file = new File(filename);

            if (file.exists()) {
                if (file.isDirectory())
                    throw new IOException("File '" + file + "' is a directory");

                if (!file.canWrite())
                    throw new IOException("File '" + file + "' cannot be written");
            } else {
                File parent = file.getParentFile();
                if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
                    throw new IOException("File '" + file + "' could not be created");
                }
            }

            FileOutputStream output = new FileOutputStream(file);

            // read each line from stream till end
            String line;

            while ((line = readr.readLine()) != null) {
                writer.write(line);

                //append the line to the file

                output.write(line.getBytes());

            }

            output.close();
            readr.close();
            writer.close();
            System.out.println("Successfully Downloaded.");


        }

        // Exceptions
        catch (MalformedURLException mue) {
            System.out.println("Malformed URL Exception raised");
        }
        catch (IOException ie) {

            System.out.println("IOException raised" + ie.getMessage());
        }
    }


    public static void  main(String[] args) throws Exception {
//        String url = "https://www.geeksforgeeks.org/";
//
//        new WebsiteExtractor().downloadWebsite(url);
//
//        new WebsiteExtractor().extractLinks(url);

//        long total = new WebsiteExtractor().countTotalKilobytes("src/main/java/downloadedPages/"+new WebsiteExtractor().extractWebname("https://www.geeksforgeeks.org/"));
//
//        System.out.println("Total Kilobytes: " + total);
    }
}
