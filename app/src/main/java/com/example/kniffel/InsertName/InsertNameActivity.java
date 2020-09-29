package com.example.kniffel.InsertName;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.InsertNumberOfPlayers.InsertNumberOfPlayers;
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
    private String[] playerNames;
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
     * speichter die Anzahl der Spieler die man über den Intent ausließt in numberOfPlayers und erstellt das playerNames Array
     */
    private void getExtrasFromIntent() {
        Bundle extras = getIntent().getExtras();
        int numberOfPlayers = extras.getInt(InsertNumberOfPlayers.EXTRA_KEY_NUMBER_OF_PLAYERS);
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
     * aus dem Edittext wird der derzeitig eingegebene Name ausgelesen und in das playerNames Array
     * an der richtigen Stelle zugewiesen der Edittext wird danach wieder leer gemacht
     * der Zähler counterHowMuchNamesAlreadyEntered wird eins hochgezählt damit in der if Abfrage danach der Intent zur
     * RollTheDice Activity aufgerufen werden kann sobald alle Namen eingetragen sind playerNames.length ist dabei die Spieleranzahl
     */
    private void initPlayerNames() {
        String currentName = editTextForPlayerNames.getText().toString();
        playerNames[counterHowMuchNamesAlreadyEntered] = currentName;
        editTextForPlayerNames.setText("");
        // warum kann ich hier keinen String aus der xml Datei benutzen? -Q
        editTextForPlayerNames.setHint("nextPlayer");
        counterHowMuchNamesAlreadyEntered++;

        if(counterHowMuchNamesAlreadyEntered == playerNames.length) {
            createIntentToStartRollTheDiceActivity();
        }

    }

    /**
     * Intent um die RollTheDiceActivity aufzurufen wird erstellt
     */
    private void createIntentToStartRollTheDiceActivity() {
        Intent intentToStartRollTheDiceActivity = new Intent(this, RollTheDiceActivity.class);

        intentToStartRollTheDiceActivity.putExtra(EXTRA_KEY_PLAYER_NAMES_ARRAY, playerNames);
        startActivity(intentToStartRollTheDiceActivity);
    }
}
