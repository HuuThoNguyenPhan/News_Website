package com.news.utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MyUtils {
    public static Document connectURL (String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
