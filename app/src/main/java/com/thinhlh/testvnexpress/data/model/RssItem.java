/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: RssItem.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 11:01 PM
 */

package com.thinhlh.testvnexpress.data.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Thinh Lai on 11/13/20.
 */
@Root(name = "item", strict = false)
public class RssItem {
    @Element
    public String title;
    @Element
    public String description;
    @Element
    public String pubDate;
    @Element
    public String link;

    public String imageUrl;
    public String content;

    public void parseDescription() {
        Document doc = Jsoup.parse(description);
        Elements imgE = doc.select("img");
        imageUrl = imgE.attr("src");
        content = doc.text();
    }
}
