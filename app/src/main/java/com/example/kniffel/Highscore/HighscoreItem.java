package com.example.kniffel.Highscore;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;

/**
 * Diese Klasse bildet einen einzelnen Eintrag in der Highscoreliste. Die
 * in den Instanzen gespeicherten Informationen sollen in der Datenbank persistiert werden.
 */

@Entity
public class HighscoreItem {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String playerName;
    private GregorianCalendar date;
    private int score;

    public HighscoreItem(String task, String date, int score) {
        this.playerName = task;
        this.date = getDateFromString(date);
        this.score = score;
    }

    private GregorianCalendar getDateFromString(String date) {
        GregorianCalendar cal = new GregorianCalendar();

        try {
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);
            cal.setTime(Objects.requireNonNull(df.parse(date)));
        } catch (ParseException e) {
            //Wenn parsing fehlschlägt benutzt der erstellte GregCalender automatisch das aktuelle Datum
            e.printStackTrace();
        }
        return cal;
    }


    public String getFormattedDate() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
                Locale.GERMANY);

        return df.format(date.getTime());
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
}
