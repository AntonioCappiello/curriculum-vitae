package com.antoniocappiello.curriculumvitae.presenter.injector;

import android.content.Context;

import com.antoniocappiello.curriculumvitae.App;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookEntityOrchestrator;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookSaver;
import com.antoniocappiello.curriculumvitae.presenter.entityhandler.BookValidator;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApi;
import com.antoniocappiello.curriculumvitae.presenter.webapi.WebApiService;
import com.antoniocappiello.curriculumvitae.view.CategoryActivity;

import dagger.Component;

@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(CategoryActivity activity);
    void inject(App app);

    @AppScope
    Context appContext();

    WebApi webApi();
    WebApiService webApiService();
    BookValidator bookValidator();
    BookSaver bookSaver();
    BookEntityOrchestrator bookEntityOrchestrator();
}