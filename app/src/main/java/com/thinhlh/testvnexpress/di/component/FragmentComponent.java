package com.thinhlh.testvnexpress.di.component;

import com.thinhlh.testvnexpress.di.PerFragment;
import com.thinhlh.testvnexpress.di.module.FragmentModule;

import dagger.Subcomponent;


@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

}
