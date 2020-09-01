package com.example.kniffel.Highscore;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.R;

import java.util.ArrayList;

/**
 * nicht fertig
 */

/**
 * In dieser Activity werden alle Highscore Einträge in einer Liste angezeigt.
 */

public class HighscoreActivity extends AppCompatActivity {

    private ListView highscoreListView;
    private HighscoreListAdapter adapter;
    private ArrayList<HighscoreItem> highscoreList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_list_layout);
        setupUI();
    }

    private void setupUI() {
        highscoreListView = findViewById(R.id.highscore_list);
        highscoreList = new ArrayList<>();
        adapter = new HighscoreListAdapter(this, highscoreList);
        highscoreListView.setAdapter(adapter);
    }
}

/**
 * Das muss am besten passieren, sobald das Spiel beendet wird. Das heißt wenn auf den "Game-Over-Button"
 * geklickt wird, muss überprüft werden ob einer der Spieler einen neuen Highscore erreicht hat, nur
 * dann wird ein Datenbankaufruf benötigt.
 *
 * GameFinishedButton.setOnClickListener(new View.OnClickListener(){
 *
 * @Override public void onClick(View v) {
 * String playerName = muss ausgelesen werden
 * int score = muss aus einem Feld in der TabellenActivity geholt werden
 * date = ?
 * if(!task.isEmpty() && !date.isEmpty() && !score.isEmpty(){
 * HighscoreItem highscoreItem = new HighscoreItem(task, date, score);
 * highscoreList.add(highscoreItem);
 * adapter.notifyDataSetChanged();
 *
 * }
 * dbHelper.addHighscoreToDatabase(highscoreItem);
 * }
 * });
 */

