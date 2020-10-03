package com.example.kniffel.InsertResults;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kniffel.InsertName.InsertNameActivity;
import com.example.kniffel.R;
import com.example.kniffel.RollTheDice.RollTheDiceActivity;

import java.util.ArrayList;

/**
 * Activity verwaltet die Spieler und ihre Würfe in einem RecyclerView:
 * - Tabelle verwaltet die Spieler und speichert ihre Ergebnisse
 * - neue Wurfergebnisse können ergänzt werden
 * - */
public class TableActivity extends AppCompatActivity {

    private String[] playerNames;
    private int[] diceEyeNumber;
    private ArrayList<Player> players;
    private TableEntryAdapter entryAdapter;
    private RecyclerView tablePlayerList;


    /**
     * Key um die Spieler Namen an die RollTheDiceActivity weiterzugeben
     */
    public static final String EXTRA_KEY_PLAYER_NAMES_ARRAY = "PLAYERS_NAMES";

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
    }

    /**
     * Erstellt und führt den Intent mit ActivityForResult aus, damit die TableActivity auf dem ActivityStack unten liegt
     */
    private void createActivityStack() {
        Intent intentToGetRollTheDiceActivityOnStack = new Intent(this, RollTheDiceActivity.class);
        intentToGetRollTheDiceActivityOnStack.putExtra(EXTRA_KEY_PLAYER_NAMES_ARRAY, playerNames);

        startActivityForResult(intentToGetRollTheDiceActivityOnStack, REQUEST_CODE_FOR_ACTIVITY_FOR_RESULT);
    }

    /**
     * bekommt von der ActvityForResult Methode aus der RollTheDiceActivity das Integer Array mit den 5 gewürfelten Würfeln
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_FOR_ACTIVITY_FOR_RESULT) {
            if (resultCode == RESULT_OK) {
                diceEyeNumber = data.getIntArrayExtra(RollTheDiceActivity.EXTRA_KEY_ROLLED_DICE_EYE_NUMBERS);
            }
        }
    }

    /**
     * Layout und RecylerView initialisieren
     */
    private void initUI() {
        setContentView(R.layout.activity_table);
        tablePlayerList = findViewById(R.id.table_player_list);
    }

    /** -ArrayList wird initialisiert
     * - Aus dem playerNames Array werden die namen ausgelesen, die Player erstellt und der ArrayList hinzugefügt*/
    private void initPlayers(){
        players = new ArrayList<>();
        entryAdapter = new TableEntryAdapter(players, this);
        tablePlayerList.setAdapter(entryAdapter);
    }

    /**
     * Löscht das letzte angeklickte Feld damit immer nur eines beim derzeitigen Wurf angezeigt wird. Macht nichts, wenn
     * nach der RollTheDiceActivity noch kein anderes Feld zum ersten mal angeklickt wurde
     */
    private void clearLastClickedTextView() {

    }

    private void onTextViewClicked(View v) {
        switch (v.getId()) {
            case R.id.player_1_toss_one:
            case R.id.player_2_toss_one:
                insertPointsTossSingleDices(v, 1);
                break;
            case  R.id.player_1_toss_two:
            case  R.id.player_2_toss_two:
                insertPointsTossSingleDices(v, 2);
                break;
            case  R.id.player_1_toss_three:
            case  R.id.player_2_toss_three:
                insertPointsTossSingleDices(v, 3);
                break;
            case  R.id.player_1_toss_four:
            case  R.id.player_2_toss_four:
                insertPointsTossSingleDices(v, 4);
                break;
            case  R.id.player_1_toss_five:
            case  R.id.player_2_toss_five:
                insertPointsTossSingleDices(v, 5);
                break;
            case  R.id.player_1_toss_six:
            case  R.id.player_2_toss_six:
                insertPointsTossSingleDices(v, 6);
                break;
            case  R.id.player_1_toss_three_doubles:
            case  R.id.player_2_toss_three_doubles:
                insertPointsTossThreeDoubles(v);
                break;
            case  R.id.player_1_toss_four_doubles:
            case  R.id.player_2_toss_four_doubles:
                insertPointsTossFourDoubles(v);
                break;
            case  R.id.player_1_toss_full_house:
            case  R.id.player_2_toss_full_house:
                insertPointsTossFullHouse(v);
                break;
            case  R.id.player_1_toss_little_street:
            case  R.id.player_2_toss_little_street:
                insertPointsTossLittleStreet(v);
                break;
            case  R.id.player_1_toss_large_street:
            case  R.id.player_2_toss_large_street:
                insertPointsTossLargeStreet(v);
                break;
            case  R.id.player_1_toss_kniffel:
            case  R.id.player_2_toss_kniffel:
                insertPointsTossKniffel(v);
                break;
            case  R.id.player_1_toss_chance:
            case  R.id.player_2_toss_chance:
                insertPointsTossChance(v);
                break;
        }
    }

    /**
     * @param v ist das angeklickte TextView
     * @param i ist das Würfelergebnis, das gezählt werden soll wie oft es in dem
     *        diceEyeNumberArray vorkommt.
     *        Das TextView wird danach auf den Wert i mal Anzahl der Würfel mit Augenzahl i gesetzt.
     */
    private void insertPointsTossSingleDices(View v, int i) {
        //((TextView)v).setText(DiceCheckerHelper.countDiceEyeNumberTogether(diceEyeNumber));
        ((TextView) v).setText("Test123");
    }

    /**
     * die folgenden 7 Methoden lassen immer nur von der Helper Klasse Checken ob die Anforderungen für das angeklickte Feld
     * erfüllt sind und dann wird das TextView auf den Rückgabewert dieser Checker Methoden gesetzt.
     * @param v ist das angeklickte TextView
     */
    private void insertPointsTossChance(View v) {
        ((TextView)v).setText(DiceCheckerHelper.countDiceEyeNumberTogether(diceEyeNumber));
    }

    private void insertPointsTossKniffel(View v) {
        ((TextView)v).setText(DiceCheckerHelper.checkKniffel(diceEyeNumber));
    }

    private void insertPointsTossLargeStreet(View v) {
        ((TextView)v).setText(DiceCheckerHelper.checkGreatStreet(diceEyeNumber));

    }

    private void insertPointsTossLittleStreet(View v) {
        ((TextView)v).setText(DiceCheckerHelper.checkSmallStreet(diceEyeNumber));
    }

    private void insertPointsTossFullHouse(View v) {
        ((TextView)v).setText(DiceCheckerHelper.checkFullHouse(diceEyeNumber));
    }

    private void insertPointsTossFourDoubles(View v) {
        ((TextView)v).setText(DiceCheckerHelper.check4ErPasch(diceEyeNumber));
    }

    private void insertPointsTossThreeDoubles(View v) {
        ((TextView)v).setText(DiceCheckerHelper.check3ErPasch(diceEyeNumber));
    }
}
