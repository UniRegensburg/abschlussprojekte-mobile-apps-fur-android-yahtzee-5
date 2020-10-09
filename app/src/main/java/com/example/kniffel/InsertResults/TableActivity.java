package com.example.kniffel.InsertResults;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kniffel.GameOver.GameFinishedActivity;
import com.example.kniffel.InsertName.InsertNameActivity;
import com.example.kniffel.R;
import com.example.kniffel.RollTheDice.RollTheDiceActivity;

import java.util.ArrayList;

/**
 * Activity verwaltet die Spieler und ihre Würfe in einem RecyclerView:
 * - Tabelle verwaltet die Spieler und speichert ihre Ergebnisse
 * - neue Wurfergebnisse können ergänzt werden
 * -
 */
public class TableActivity extends AppCompatActivity {

    private String[] playerNames;
    private int[] diceEyeNumber = new int[]{1, 2, 3, 4, 5};
    private ArrayList<Player> players;
    private TableEntryAdapter entryAdapter;
    private RecyclerView tablePlayerList;
    private RecyclerView.LayoutManager layoutManager;
    /**
     * laufIndex damit der derzeitige Spieler in der Arraylist immer aufgerufen und manipuliert werden kann
     */
    private int currentPlayer = 0;
    /**
     * die Anzahl an würfen die ein Spieler in dieser Runde noch hat
     */
    private int rollsLeft = 3;
    private Button rollAgainButton, submitPointsButton;

    /**
     * roundCounter wird nach jedem Eintragen aufruf um 1 hochgezählt numbersOfRoundsThatHasToBePlayed = SpielerAnzahl*13 (felder)
     * wenn so oft eintragen gedrückt wurde dann ist das Spiel vorbei und die GameOverActivity wird aufgerufen.
     */
    private int roundCounter = 0;
    private int numbersOfRoundsThatHaveToBePlayed;

    /**
     * Key um den aktuellen SpielerNamen an die RollTheDiceActivity weiterzugeben sowie die verbleibenden Würfe und die letzten
     * WürfelAugen. Final Points_KEY um die Endergebnisse an die Game Over Activity weiter zu geben
     */
    public static final String EXTRA_KEY_CURRENT_PLAYER = "CURRENT_PLAYER";
    public static final String EXTRA_KEY_ROLLS_LEFT = "ROLLS_LEFT";
    public static final String EXTRA_KEY_DICE_EYE_NUMBER = "DICE_EYE_NUMBERS";

    /**
     * Final Points_KEY um die Endergebnisse an die Game Over Activity weiter zu geben
     */
    public static final String EXTRA_KEY_FINAL_POINTS = "FINAL_POINTS";
    public static final String EXTRA_KEY_PLAYER_NAMES = "PLAYER_NAMES_STRING_ARRAY";


    /**
     * Request Code für den Acitivity for Result Intent von der ROllTheDiceActivity
     */
    public static final int REQUEST_CODE_FOR_ACTIVITY_FOR_RESULT = 101;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtrasFromIntent();
        createActivityStack();
        initUI();
        initPlayers();
    }

    /**
     * speichert die Namen der Spieler in das playerNames String array aus dem Extra der InsertNameActivity
     */
    private void getExtrasFromIntent() {
        Bundle extras = getIntent().getExtras();
        playerNames = extras.getStringArray(InsertNameActivity.EXTRA_KEY_PLAYER_NAMES_ARRAY);
        numbersOfRoundsThatHaveToBePlayed = 13* playerNames.length;
    }

    /**
     * Erstellt und führt den Intent mit ActivityForResult aus, damit die TableActivity auf dem ActivityStack unten liegt
     */
    private void createActivityStack() {
        createIntentToCallRollTheDiceActivity();
    }

    /**
     * startet die Roll The Dice activity und gibt mit wieviele würfe der Spieler noch übrig hat und was sein Name ist
     */
    private void createIntentToCallRollTheDiceActivity() {
        Intent intentToGetRollTheDiceActivityOnStack = new Intent(this, RollTheDiceActivity.class);
        intentToGetRollTheDiceActivityOnStack.putExtra(EXTRA_KEY_CURRENT_PLAYER, playerNames[currentPlayer]);
        intentToGetRollTheDiceActivityOnStack.putExtra(EXTRA_KEY_ROLLS_LEFT, rollsLeft);
        intentToGetRollTheDiceActivityOnStack.putExtra(EXTRA_KEY_DICE_EYE_NUMBER, diceEyeNumber);
        startActivityForResult(intentToGetRollTheDiceActivityOnStack, REQUEST_CODE_FOR_ACTIVITY_FOR_RESULT);
    }

    /**
     * bekommt von der ActvityForResult Methode aus der RollTheDiceActivity das Integer Array mit den 5 gewürfelten Würfeln
     * und wieviele würfe der aktuelle Spieler noch hat zurück
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_ACTIVITY_FOR_RESULT) {
            if (resultCode == RESULT_OK) {
                diceEyeNumber = data.getIntArrayExtra(RollTheDiceActivity.EXTRA_KEY_ROLLED_DICE_EYE_NUMBERS);
                //default Value Zahl die angezeigt wird falls keine Zahl aus dem Extra ankommt so whatever
                rollsLeft = data.getIntExtra(RollTheDiceActivity.EXTRA_KEY_ROLLS_LEFT, 3);
                players.get(currentPlayer).setDiceEyeNumber(diceEyeNumber);
                players.get(currentPlayer).setClickable(true);
                entryAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * Layout und RecylerView initialisieren
     */
    private void initUI() {
        setContentView(R.layout.activity_table);
        tablePlayerList = findViewById(R.id.table_player_list);
        tablePlayerList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        tablePlayerList.setLayoutManager(layoutManager);

        rollAgainButton = findViewById(R.id.button_roll_again);
        rollAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createIntentToCallRollTheDiceActivity();
            }
        });

        /**
         * weißt den Eintragen Button zu hier wird dann gecheckt ob etwas diese Runde eingetraen wurde, wenn ja wird gecheckt ob
         * das Spiel vorbei ist und die Game Over Activity aufgerufen werden soll (roundCounter ist der Zähler dazu).
         * Wenn das Spiel nicht vorbei ist, wird alles für den nächsten Spieler vorbereitet
         */
        submitPointsButton = findViewById(R.id.submit_button_player_table);
        submitPointsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(players.get(currentPlayer).getHasInsertedAValue()){
                    // roundCounter++ damit das Spiel rechzeitig beendet werden kann hier kann dann die Game Over Activity aufgerufen
                    // werden weil wenn das Spiel vorbei ist, muss nichts für den nächsten Spieler vorbereitet werden
                    roundCounter++;
                    // Methode wird im Player aufrufen, die checkt ob man schon den oberen teil zusammenrechnen kann
                    players.get(currentPlayer).setSubtotals();
                    if(roundCounter == numbersOfRoundsThatHaveToBePlayed){
                        createIntentToCallGameOverActivity();
                    }
                    prepareTableActivityForNextPlayer();
                } else {
                    Toast.makeText(TableActivity.this, R.string.error_message_no_value_inserted, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * setzt den roundCounter 1 nach oben
     * setzt die übrigen Würfe auf 3 und setzt den currentPlayer auf +1 oder wenn der letzte dran war wieder auf 0 für den
     * ersten Spieler und startet dann mit diesem Spieler die RollTheDiceActivity
     */
    private void prepareTableActivityForNextPlayer() {
        //damit in der nächsten Runde nicht das letzte Item wieder gelöscht wird
        players.get(currentPlayer).resetLastItemFlag();
        //der derzeitige Spieler wird nonClickable gesetzt sodass ein Error mit einer ToastMessage im Adapter aufgerufen werden kann
        players.get(currentPlayer).setClickable(false);
        rollsLeft = 3;
        //setzt den currentPlayer auf den nächsten
        if (currentPlayer == playerNames.length-1) {
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
        // damit nicht das Array vom letzten Spieler mitgegeben wird
        diceEyeNumber = null;
        createIntentToCallRollTheDiceActivity();
    }

    /**
     * wird aufgerufen wenn das Spiel vorbei ist, in der playerFinalPoints werden die Endpunkte der Spieler gespeichert an
     * 0ter Stelle stehen die Punkte des Spielers, dessen Name an 0ter stelle des playerNames Array steht
     * danach wird ein Intent zur Game Over Activity erstellt, der das playerNames String Array und das Integer Array mit dem Endpunktestand übergeben wird
     */
    private void createIntentToCallGameOverActivity() {
        int[] playerFinalPoints = new int[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players.get(i).setTotalSum();
            playerFinalPoints[i] = Integer.parseInt(players.get(i).getTotalSum());
        }
        Intent intentToStartGameOverActivity = new Intent(TableActivity.this, GameFinishedActivity.class);
        intentToStartGameOverActivity.putExtra(EXTRA_KEY_FINAL_POINTS, playerFinalPoints);
        intentToStartGameOverActivity.putExtra(EXTRA_KEY_PLAYER_NAMES, playerNames);
        startActivity(intentToStartGameOverActivity);
    }

    /**
     * -ArrayList wird initialisiert
     * - Aus dem playerNames Array werden die namen ausgelesen, die Player erstellt und der ArrayList hinzugefügt
     * der Clicklistener wird auf die Items gesetzt
     */
    private void initPlayers() {
        players = new ArrayList<>();

        for (String name : playerNames) {
            players.add(new Player(name));
            Log.d("Spielername", name);
        }
        /**weiterer (überflüssiger) Spieler wird der Spielerliste hinzugefügt, damit alle Spieler tatsächlichen Spieler im RecyclerView angezeigt werden */
        players.add(new Player(""));
        entryAdapter = new TableEntryAdapter(this, players);
        tablePlayerList.setAdapter(entryAdapter);
        entryAdapter.setPlayerEntries(players);
        entryAdapter.notifyDataSetChanged();
    }
}
