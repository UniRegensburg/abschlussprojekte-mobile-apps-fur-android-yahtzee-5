package com.example.kniffel.Highscore.Database;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.kniffel.Highscore.HighscoreItem;

/**
 * Dieses Interface definiert, wie später aus dem Java-Code auf die Highscore-Objekte in der Datenbank
 * zugegriffen werden kann. Die Annotationen verknüpfen die Java-Methoden mit konkreten DB-Operationen.
 */

@Dao
public interface HighscoreItemDao {
    @Insert
    void addHighscoreItem(HighscoreItem highscoreitems);
}
