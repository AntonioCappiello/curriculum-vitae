/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/11/16 6:46 PM
 */

package com.antoniocappiello.curriculumvitae.injector;


import com.antoniocappiello.curriculumvitae.espresso.CategoryActivityPersonalInfoTest;
import com.antoniocappiello.curriculumvitae.mock.MockWebApi;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookEntityOrchestrator;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookSaver;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookValidator;
import com.antoniocappiello.curriculumvitae.presenter.injector.AppComponent;
import com.antoniocappiello.curriculumvitae.presenter.injector.AppScope;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApi;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiService;

import dagger.Component;

@AppScope
@Component(modules = TestAppModule.class)
public interface TestAppComponent extends AppComponent {

    void inject(CategoryActivityPersonalInfoTest test);

    WebApi webApi();
    WebApiService webApiService();

    BookValidator bookValidator();
    BookSaver bookSaver();
    BookEntityOrchestrator bookEntityOrchestrator();
}