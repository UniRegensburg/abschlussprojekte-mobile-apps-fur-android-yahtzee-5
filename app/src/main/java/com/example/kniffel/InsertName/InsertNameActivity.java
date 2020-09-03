package com.example.kniffel.InsertName;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.MainActivity;
import com.example.kniffel.R;
import com.example.kniffel.RollTheDice.RollTheDiceActivity;

public class InsertNameActivity extends AppCompatActivity {

    /**
     * Key um die Spieler Anzahl an die InsertNameActivity weiterzugeben
     */
    public static final String EXTRA_KEY_PLAYER_NAMES_ARRAY = "PLAYERS_NAMES";

    private EditText editTextForPlayerNames;
    private Button buttonToConfirmPlayerNames;
    private String playerNames[];
    private int counterHowMuchNamesAlreadyEntered = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_name);
        getExtrasFromIntent();
        initViews();
        initClickListener();
    }

    /**
     * speichter die Anzahl der Spieler die man über den Intent ausließt in numberOfPlayers
     */
    private void getExtrasFromIntent() {
        Bundle extras = getIntent().getExtras();
        int numberOfPlayers = extras.getInt(MainActivity.EXTRA_KEY_NUMBER_OF_PLAYERS);
        playerNames = new String[numberOfPlayers];
    }

    /**
     * Initialisiert den EditText für die Namen der Spieler und den Button zum Bestätigen
     */
    private void initViews() {
        editTextForPlayerNames = findViewById(R.id.editTextForPlayersNames);
        buttonToConfirmPlayerNames = findViewById(R.id.buttonAcceptPlayerName);
    }

    /**
     * Initialisiert den Listener für den Button, der die initPlayerNames Methode aufruft
     */
    private void initClickListener() {
        buttonToConfirmPlayerNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPlayerNames();
            }
        });
    }

    /**
     * hier wird ein Array mit der Länge der SpielerAnzahl erstellt, die nach dem letzten Namen dem Intent
     * zum starten der RollTheDiceActivity übergeben wird
     */
    private void initPlayerNames() {
        String currentName = editTextForPlayerNames.getText().toString();
        playerNames[counterHowMuchNamesAlreadyEntered] = currentName;
        counterHowMuchNamesAlreadyEntered++;

        /**
         * wenn diese Abfrage wahr ist, dann wird der Intent um die RollTheDiceActivity aufzurufen erstellt und ausgeführt
         */
        if(counterHowMuchNamesAlreadyEntered == playerNames.length) {
            createIntentToStartRollTheDiceActivity();
        }

    }

    /**
     * Intent um die RollTheDiceActivity aufzurufen wird erstellt
     */
    private void createIntentToStartRollTheDiceActivity() {
        Intent intentToStartRollTheDiceActivity = new Intent(this, RollTheDiceActivity.class);

        // falls man als Extra kein Array übergeben kann, muss hier noch eine Schleife einzelne String Extras erstellen
        // die Frage ist, ob ein Array dann überhaupt Sinn macht
        intentToStartRollTheDiceActivity.putExtra(EXTRA_KEY_PLAYER_NAMES_ARRAY, playerNames);
        startActivity(intentToStartRollTheDiceActivity);
    }
}
