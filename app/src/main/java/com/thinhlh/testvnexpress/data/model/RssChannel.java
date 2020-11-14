/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: RssChannel.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 10:58 PM
 */

package com.thinhlh.testvnexpress.data.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Thinh Lai on 11/13/20.
 */

@Root(name = "channel", strict = false)
public class RssChannel {
    @ElementList(inline = true, required = false)
    public List<RssItem> item;
}
