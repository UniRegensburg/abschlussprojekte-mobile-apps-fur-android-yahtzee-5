package com.example.kniffel.Highscore.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.kniffel.Highscore.HighscoreItem;


@Database(entities = {HighscoreItem.class}, version = 1)
@TypeConverters(HighscoreItemConverters.class)
public abstract class HighscoreDatabase extends RoomDatabase {
    /**
     * Methode für Zugriff auf die "Tabelle" mit den LogEntry-Einrägen
     */
    public abstract HighscoreItemDao highscoreItems();
}
