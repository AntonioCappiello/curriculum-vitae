package com.antoniocappiello.curriculumvitae.injector;


import com.antoniocappiello.curriculumvitae.espresso.CategoryActivityPersonalInfoTest;
import com.antoniocappiello.curriculumvitae.presenter.injector.AppComponent;
import com.antoniocappiello.curriculumvitae.presenter.injector.AppScope;

import dagger.Component;

@AppScope
@Component(modules = TestAppModule.class)
public interface TestAppComponent extends AppComponent {

    void inject(CategoryActivityPersonalInfoTest test);
}