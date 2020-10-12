package com.example.kniffel.Highscore;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;

/**
 * Diese Klasse bildet einen einzelnen Eintrag in der Highscoreliste. Die
 * in den Instanzen gespeicherten Informationen sollen in der Datenbank persistiert werden.
 */

@Entity
public class HighscoreItem implements Comparable<HighscoreItem>{
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "playerName")
    private String playerName;
    @ColumnInfo(name = "date")
    public  Date date;
    @ColumnInfo(name = "score")
    private int score;



    public HighscoreItem(String playerName, int score) {
        this.playerName = playerName;
        this.date = Calendar.getInstance().getTime();
        this.score = score;
    }

    public String getFormattedDate() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
                Locale.GERMANY);

        return df.format(date.getTime());
    }

    public int getUid() { return uid; }

    public void setUid(int uid) {this.uid = uid;}

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        this.score = score;
    }


    /**
     * Vorgefertigter Comparator, mit dessen Hilfe HighscoreItem-Objekte anhand des erspielten Scores
     * mit anderen HighscoreItem-Objekten verglichen werden können. Höhre Scores werden vor niedrigere
     * einsortiert.
     */
    public static final Comparator<HighscoreItem> SCORE_COMPARATOR = new Comparator<HighscoreItem>() {
        @Override
        public int compare(HighscoreItem o1, HighscoreItem o2) {
            float scoreDelta = o1.score - o2.score;
            return scoreDelta > 0 ? -1 : 1;
        }
    };

    @Override
    public int compareTo(HighscoreItem highscoreItem) {
        return this.getPlayerName().compareTo(highscoreItem.getPlayerName());
    }
}

