/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: VNExpressService.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 10:54 PM
 */

package com.thinhlh.testvnexpress.data.service;

import com.thinhlh.testvnexpress.data.model.RssRoot;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Thinh Lai on 11/13/20.
 */
public interface VNExpressService {
    @GET("rss/tin-moi-nhat.rss")
    Single<RssRoot> getNews();
}
