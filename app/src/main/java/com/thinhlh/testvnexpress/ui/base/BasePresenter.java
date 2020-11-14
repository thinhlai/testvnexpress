/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: BasePresenter.java
 * Created: 2020/11/13
 * Last modified: 11/13/20 11:29 PM
 */

package com.thinhlh.testvnexpress.ui.base;

import com.thinhlh.testvnexpress.data.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Thinh Lai on 11/13/20.
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private T view;
    protected final DataManager mDataManager;

    @Inject
    public BasePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    protected T getView() {
        return view;
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
