package me.dio.soccernews.ui.favorites;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.dio.soccernews.data.NewsRepository;
import me.dio.soccernews.data.NewsRepository;
import me.dio.soccernews.domain.News;

public class FavoritesViewModel extends ViewModel {

    public FavoritesViewModel() {

    }

    public LiveData<List<News>> loadFavoriteNews() {
        return (LiveData<List<News>>) NewsRepository.getInstance().getLocalDb().NewsDao().loadFavoriteNews();
    }

    public void saveNews(News news) {
        AsyncTask.execute(() -> NewsRepository.getInstance().getLocalDb().NewsDao().save(news));
    }

}