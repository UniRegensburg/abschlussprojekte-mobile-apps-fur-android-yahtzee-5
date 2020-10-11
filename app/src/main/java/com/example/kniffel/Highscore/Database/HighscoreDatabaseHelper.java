package com.example.kniffel.Highscore.Database;

import android.app.Activity;

import androidx.room.Room;

import com.example.kniffel.Highscore.HighscoreItem;

import java.util.concurrent.Executors;

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

    /**
     * Wird von anderen Teilen der Anwendung aufgerufen, um das übergebene HighscoreItem-Objekt in der
     * Datenbank zu speichern.
     */
    public void addHighscoreToDatabase(HighscoreItem highscoreItem) {
        AddHighscoreItem item = new AddHighscoreItem(db, highscoreItem);
        Executors.newSingleThreadExecutor().submit(item);

    }

    /**
     * Diese Runnable kapselt den, nebenläufig durchzuführenden, Vorgang des Hinzufügens eines neuen
     * Datenbankeintrags. Das zuspeichernde Objekt wird dem Task im Konstruktor übergeben.
     */
    private class AddHighscoreItem implements Runnable {
        private HighscoreDatabase db;
        private HighscoreItem highscoreItem;

        public AddHighscoreItem(HighscoreDatabase db, HighscoreItem highscoreItem) {
            this.db = db;
            this.highscoreItem = highscoreItem;
        }

        @Override
        public void run() {
            db.highscoreItems().addHighscoreItem(highscoreItem);
        }
    }
}
