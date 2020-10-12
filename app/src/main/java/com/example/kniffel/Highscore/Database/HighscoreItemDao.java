package com.example.kniffel.Highscore.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.kniffel.Highscore.HighscoreItem;

import java.util.List;

/**
 * Dieses Interface definiert, wie später aus dem Java-Code auf die Highscore-Objekte in der Datenbank
 * zugegriffen werden kann. Die Annotationen verknüpfen die Java-Methoden mit konkreten DB-Operationen.
 */

@Dao
public interface HighscoreItemDao {

    // Gibt alle Einträge der Datenbank zurück. Der Tabellenname in der Query ergibt sich aus dem
    // entsprechenden Klassennamen.
    @Query("SELECT * FROM HighscoreItem")
    List<HighscoreItem> getAll();

    @Insert
    void addHighscoreItem(HighscoreItem highscoreitem);

    @Delete
    void delete(HighscoreItem highscoreItem);
}
