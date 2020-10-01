package com.example.kniffel.InsertResults;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.R;
import com.example.kniffel.RollTheDice.RollTheDiceActivity;

public class TableActivityTwoPlayers extends AppCompatActivity {

    private String[] playerNames;
    private int[] diceEyeNumber;

    /**
     * alle Id's der TexViews für die Punkte
     */
    private int[] allTextViews = {R.id.player_1_toss_one, R.id.player_1_toss_two, R.id.player_1_toss_three,
            R.id.player_1_toss_four, R.id.player_1_toss_five, R.id.player_1_toss_six, R.id.player_1_toss_three_doubles,
            R.id.player_1_toss_four_doubles, R.id.player_1_toss_full_house, R.id.player_1_toss_little_street,
            R.id.player_1_toss_large_street, R.id.player_1_toss_kniffel, R.id.player_1_toss_chance, R.id.player_2_toss_one,
            R.id.player_2_toss_two, R.id.player_2_toss_three, R.id.player_2_toss_four, R.id.player_2_toss_five,
            R.id.player_2_toss_six, R.id.player_2_toss_three_doubles, R.id.player_2_toss_four_doubles,
            R.id.player_2_toss_full_house, R.id.player_2_toss_little_street, R.id.player_2_toss_large_street,
            R.id.player_2_toss_kniffel, R.id.player_2_toss_chance};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtrasFromIntent();
        initViews();
    }

    private void getExtrasFromIntent() {
        Bundle extras = getIntent().getExtras();
        playerNames = extras.getStringArray(RollTheDiceActivity.EXTRA_KEY_PLAYER_NAMES_ARRAY);
        diceEyeNumber = extras.getIntArray(RollTheDiceActivity.EXTRA_KEY_ROLLED_DICE_EYE_NUMBERS);
    }

    /**
     * zuweisung der Namen in die entsprechenden Felder und dann OnClickListeners für die Clickbaren Felder für die Punkte
     */
    private void initViews() {
        setContentView(R.layout.activity_table_two_player);
        TextView p1Name = findViewById(R.id.player_1_column);
        p1Name.setText(playerNames[0]);
        TextView p2Name = findViewById(R.id.player_2_column);
        p2Name.setText(playerNames[1]);

        for (int pointsTextViewId : allTextViews) {
            findViewById(pointsTextViewId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearLastClickedTextView();
                    onTextViewClicked(v);
                }
            });
        }
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
