package com.example.gettyimagesgallery;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlGettyImages {

    private static final String TAG = "CrawlGettyImages";

    public static final String URL = "https://www.gettyimages.com/photos/collaboration?page=";
    public static final String ImageClass = "gallery-mosaic-asset__thumb";

    public static int pageNum = 1;

    private static CrawlGettyImages crawler = new CrawlGettyImages();

    private CrawlGettyImages() {

    }

    public static CrawlGettyImages getInstance() {
        return crawler;
    }

    public List<String> getNextImagesUrls() {
        pageNum++;
        return getImageUrls();
    }

    public List<String> getImageUrls() {
        List<String> urls = new ArrayList<>();
        if (pageNum <= 100) {
            try {
                Document document = Jsoup.connect(URL + pageNum).get();
                Elements imageTags = document.getElementsByClass(ImageClass);
                for (Element e : imageTags) {
                    System.out.println(e.toString());
                    urls.add(e.absUrl("src").toString());
                }
            } catch (IOException e) {
                Log.e(TAG, "getImageUrls: ", e);
            }
        }
        return urls;
    }
}
