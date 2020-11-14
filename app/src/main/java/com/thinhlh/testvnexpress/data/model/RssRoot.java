/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: News.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 10:56 PM
 */

package com.thinhlh.testvnexpress.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Thinh Lai on 11/13/20.
 */
@Root(name = "rss", strict = false)
public class RssRoot {
    @Element
    public RssChannel channel;

    public void parseDescription() {
        if (channel != null && channel.item != null) {
            for (int i = 0; i < channel.item.size(); i++) {
                channel.item.get(i).parseDescription();
            }
        }
    }
}

