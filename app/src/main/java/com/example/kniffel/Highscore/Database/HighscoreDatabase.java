package com.example.kniffel.Highscore.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.kniffel.Highscore.HighscoreItem;


@Database(entities = {HighscoreItem.class}, version = 1)
public abstract class HighscoreDatabase extends RoomDatabase {
    public abstract HighscoreItemDao highscoreItems();
}
