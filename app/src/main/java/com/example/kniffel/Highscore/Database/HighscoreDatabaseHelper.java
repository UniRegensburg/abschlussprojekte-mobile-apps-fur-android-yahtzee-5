package com.example.kniffel.Highscore.Database;

import android.app.Activity;

import androidx.room.Delete;
import androidx.room.Room;

import com.example.kniffel.Highscore.HighscoreItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Diese Hilfsklasse unterstützt den Zugriff auf die Datenbank möglichst gut vom Rest
 * der Anwendung zu Trennen. Nur in dieser Klasse finden Datenbankoperationen statt. Alle Komponenten,
 * die Informationen aus der Datenbank benötigen oder in die Datenbank schreiben wollen, tun dies über
 * eine Instanz dieser Klasse. HighscoreDatabaseHelper arbeitet als "Vermittler" zwischen der Room-API
 * und den übrigen Teilen unserer Anwendung.
 */
public class HighscoreDatabaseHelper {
    /**
     * Name der Datenbank
     */
    private static final String DATABASE_NAME = "highscore-db";
    private HighscoreDatabase db;

    private final Activity activityContext;

    public HighscoreDatabaseHelper(Activity activityContext) {
        /**
         * Erstellen der Datenbank bzw. falls Datenbank schon vorhanden ist, wird bereits vorhandene Datenbank geladen
         */
        this.activityContext = activityContext;
        initDatabase();
    }


    /**
     * Dem Konstruktor der Hilfsklasse wird eine Activity als Parameter übergeben, damit deren
     * Kontext für die Erstellung bzw. Bereitstellung der Datenbank verwendet werden kann.
     */
    private void initDatabase() {
        db = Room.databaseBuilder(activityContext, HighscoreDatabase.class, DATABASE_NAME).build();
    }

    public void addHighscoreItem(HighscoreItem highscoreItem) {
        AddHighscoreItem task = new AddHighscoreItem(highscoreItem);
        task.start();
    }

    public void fetchAllHighscoreItems(HighscoreQueryListener listener) {
        GetAllHighscoreItems task = new GetAllHighscoreItems(listener);
        task.start();
    }

    public void deleteHighscoreItem(HighscoreItem highscoreItem){
        DeleteHighscoreItem task = new DeleteHighscoreItem(highscoreItem);
        task.start();
    }

    private static abstract class DBTask implements Runnable {

        void start() {
            Executors.newSingleThreadExecutor().submit(this);
        }

    }

    private class AddHighscoreItem extends DBTask {

        HighscoreItem highscoreItem;

        public AddHighscoreItem(HighscoreItem highscoreItem) {
            this.highscoreItem = highscoreItem;
        }

        @Override
        public void run() {
            db.highscoreItems().addHighscoreItem(highscoreItem);
        }
    }

    private class GetAllHighscoreItems extends DBTask {

        HighscoreQueryListener listener;

        public GetAllHighscoreItems(HighscoreQueryListener listener) {
            this.listener = listener;
        }

        @Override
        public void run() {
            final List<HighscoreItem> highscoreList = db.highscoreItems().getAll();

            activityContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.onHighscoreQueryResult(highscoreList);
                }
            });

        }
    }

    private class DeleteHighscoreItem extends DBTask {
        private HighscoreItem highscoreItem;

        public DeleteHighscoreItem(HighscoreItem highscoreItem){
            this.highscoreItem = highscoreItem;
        }

        @Override
        public void run() {
            db.highscoreItems().delete(highscoreItem);

        }
    }
}
