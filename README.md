# My curriculum vitae as an app
Since I am an android developer I thought that would be cool to present myself via an app.

[![](http://www.saveonfoods.com/sites/default/files/pictures/google-play.jpg)](https://play.google.com/store/apps/details?id=com.antoniocappiello.curriculumvitae)

Feel free to fork it to create your own cv as an app.

**Becuse the app uses Crashlitics, from [Fabric] (https://www.crashlytics.com/login), an api secret and key are required in the project.**
For privacy I have exluded the file *fabric.properties* from git, therefore, if you get some fabric build failed error, then you need to add the file *app/fabric.properties* with the following content:

> apiSecret=[your Fabric api secret]

> apiKey=[your Fabric api key]

The Fabric api key and secret could be found in your Fabric settings, once you signed-up online.
If you don't want use Fabric, just remove it from the dependencies and from the code.

![](https://dl.dropboxusercontent.com/u/3491134/cv_app_screenshots_for_github/1.png)
![](https://dl.dropboxusercontent.com/u/3491134/cv_app_screenshots_for_github/2.png)
![](https://dl.dropboxusercontent.com/u/3491134/cv_app_screenshots_for_github/3.png)
![](https://dl.dropboxusercontent.com/u/3491134/cv_app_screenshots_for_github/4.png)
![](https://dl.dropboxusercontent.com/u/3491134/cv_app_screenshots_for_github/5.png)
![](https://dl.dropboxusercontent.com/u/3491134/cv_app_screenshots_for_github/6.png)

### Introduction
In this app I show how to use different type of technologies, libraries and design patterns. The app is a work in progress and may contains different ways for solving a problem. The reason for this is that I am going to use various code snippets as example also on my [blog](antoniocappiello.com/blog). 

### Overview of topic covered:
* Build types and product flavor
* Handy libraries like [Logger](https://github.com/noveogroup/android-logger), [Prefs](https://github.com/Pixplicity/EasyPreferences), [ImageLoader](https://github.com/nostra13/Android-Universal-Image-Loader), [EventBus](https://github.com/greenrobot/EventBus), [Gson](https://github.com/google/gson)
* Local database with [DBFlow](https://github.com/Raizlabs/DBFlow)
* [Dagger](http://google.github.io/dagger/) for injecting dependency both in app and in tests (ex. webapi, entity handlers etc)
* [ReactiveX](https://github.com/ReactiveX/RxAndroid) for Android
* Architectural style: Model View Presenter
* Design patterns: Factory, Factory Method, Builder, Composite...
* Integration with [Crashlytics](https://try.crashlytics.com/)
* Material design animations
* WebView
* CustomViews
* ...more will be added, stay tuned!
