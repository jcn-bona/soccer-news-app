package me.dio.soccernews.data;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import me.dio.soccernews.App;
import me.dio.soccernews.data.local.NewsDatabase;
import me.dio.soccernews.data.remote.NewsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRepository {

    private static final String REMOTE_API_URL = "https://jcn-bona.github.io/soccer-news-api//";
    private static final String LOCAL_DB_NAME = "soccer-news";

    //Encapsulam o acesso a API (Retrofit) e banco de dados local (Room).
    private NewsApi remoteApi;
    private NewsDatabase localDb;

    public NewsApi getRemoteApi() {
        return remoteApi;
    }

    public NewsDatabase getLocalDb() {
        return localDb;
    }

    //Técnica Singleton: garante uma instância única dos atributos relacionados ao Retrofit e Room.
    private NewsRepository () {
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi.class);

        localDb = Room.databaseBuilder(App.getInstance(), NewsDatabase.class, LOCAL_DB_NAME).build();
    }

    public static NewsRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final NewsRepository INSTANCE = new NewsRepository();
    }
}