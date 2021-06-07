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
    public static final String IMG_CLASS_NAME = "gallery-mosaic-asset__thumb";

    public static final int MAX_PAGE_NUM = 100;

    public static int pageNum = 0;

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
        if (pageNum <= MAX_PAGE_NUM) {
            try {
                Document document = Jsoup.connect(URL + pageNum).get();
                Elements imageTags = document.getElementsByClass(IMG_CLASS_NAME);
                for (Element e : imageTags) {
                    urls.add(e.absUrl("src"));
                }
            } catch (IOException e) {
                Log.e(TAG, "getImageUrls: ", e);
            }
        }
        return urls;
    }
}
