/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: Presenter.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 11:29 PM
 */
package com.thinhlh.testvnexpress.ui.base;

public interface Presenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
