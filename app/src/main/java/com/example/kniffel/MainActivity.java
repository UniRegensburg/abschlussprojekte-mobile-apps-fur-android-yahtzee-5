package com.example.kniffel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kniffel.InsertName.InsertNameActivity;

public class MainActivity extends AppCompatActivity {

    /**
     * Key um die Spieler Anzahl an die InsertNameActivity weiterzugeben
     */
    public static final String EXTRA_KEY_NUMBER_OF_PLAYERS = "PLAYERS_NUMBER";

    private Button buttonForTwoPlayers;
    private Button buttonForThreePlayers;
    private Button buttonForFourPlayers;
    private int numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initUi();
    }

    /**
     * Initialisiert die drei Buttons mit findViewById
      */
    private void initViews() {
        buttonForTwoPlayers = findViewById(R.id.buttonStartGameWith2Players);
        buttonForThreePlayers = findViewById(R.id.buttonStartGameWith3Players);
        buttonForFourPlayers = findViewById(R.id.buttonStartGameWith4Players);
    }

    /**
     * Weißt den drei Buttons setOnClicklistener zu sodass damit ein Intent erstellt werden kann
     */
    private void initUi() {
        buttonForTwoPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInsertNameActivity(2);
            }
        });
        buttonForThreePlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInsertNameActivity(3);
            }
        });
        buttonForFourPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInsertNameActivity(4);
            }
        });
    }

    /**
     * die OnClickListener starten diese Methode um einen Intent zur InsertNameActivity zu kommen
     *  @param i ist die Anzahl der Spieler
     */
    private void startInsertNameActivity(int i) {
        numberOfPlayers = i;

        Intent intentToStartInsertNameActivity = new Intent(this, InsertNameActivity.class);
        intentToStartInsertNameActivity.putExtra(EXTRA_KEY_NUMBER_OF_PLAYERS, numberOfPlayers);
        startActivity(intentToStartInsertNameActivity);
    }

}