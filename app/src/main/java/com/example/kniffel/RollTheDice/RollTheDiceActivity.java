package com.example.kniffel.RollTheDice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.InsertName.InsertNameActivity;
import com.example.kniffel.InsertResults.TableActivityFourPlayers;
import com.example.kniffel.InsertResults.TableActivityThreePlayers;
import com.example.kniffel.InsertResults.TableActivityTwoPlayers;
import com.example.kniffel.R;
import com.example.kniffel.RollTheDice.ShakeSensor.ShakeSensor;
import com.example.kniffel.RollTheDice.ShakeSensor.ShakeSensorListener;

import java.util.Random;

public class RollTheDiceActivity extends AppCompatActivity implements ShakeSensorListener {

    public static final String EXTRA_KEY_PLAYER_NAMES_ARRAY = "PLAYERS_NAMES";
    public static final String EXTRA_KEY_ROLLED_DICE_EYE_NUMBERS = "EYE_NUMBERS";
    private static final int MAX_DICE_DIGIT = 6;
    /** ImageViews mit den Würfeln*/
    private ImageView diceOne;
    private ImageView diceTwo;
    private ImageView diceThree;
    private ImageView diceFour;
    private ImageView diceFive;
    /** ShakeSensor für die Bewegungserkennung*/
    private ShakeSensor shakeSensor;
    private TextView countdownDiceThrows, playerNameView;
    /** Array, dass die Speicheradressen der Würfle-Images enthält*/
    private int[] diceDrawablePath;
    /** Array, dass die Augenzahl der aktuell angezeigten Würfel speichert*/
    private int[] diceEyeNumber;
    /** Buttons*/
    private Button scoreboardButton, clearSelectedDicesButton;
    /** Array in dem die SpielerNamen stehen */
    private String[] playerNames;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtrasFromIntent();
        initUi();
        initSensor();
        setupDices();
    }

    private void getExtrasFromIntent() {
        Bundle extras = getIntent().getExtras();
        playerNames = extras.getStringArray(InsertNameActivity.EXTRA_KEY_PLAYER_NAMES_ARRAY);
    }


    private void setupDices() {
        /** Array mit Speicheradresse der Würfel*/
        diceDrawablePath = new int[]{R.drawable.dice_throw_1, R.drawable.dice_throw_2, R.drawable.dice_throw_3, R.drawable.dice_throw_4, R.drawable.dice_throw_5, R.drawable.dice_throw_6};
        /** Array mit den aktuellen Würfelzahlen*/
        diceEyeNumber = new int[]{1, 2, 3, 4, 5};
        lockDices();
    }

    /** Würfel werden umrandet und deaktiviert und pink umrandet, sodass sie sich beim nächsten Schütteln nicht mitverändern */
    private void lockDices() {

        diceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(diceOne.isClickable()) {
                    diceOne.setClickable(false);
                    diceOne.setBackgroundResource(R.drawable.custom_button3);
                    //damit kann der Würfel zurücklegen Button entfernt werden und ich finde es intuitiver
                    //funktioniert aber natürlich nicht weil er ja nicht mehr Clickable ist und so onClick nicht aufgerufen
                    //werden kann. Problem kann mit fünf boolean Variablen für die fünf Würfel gelöst werden
                    // z.B. boolean rollableDice1 = true
                } else {
                    diceOne.setClickable(true);
                    diceOne.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        diceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceTwo.setClickable(false);
                diceTwo.setBackgroundResource(R.drawable.custom_button3);
            }
        });
        diceThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceThree.setClickable(false);
                diceThree.setBackgroundResource(R.drawable.custom_button3);
            }
        });

        diceFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               diceFour.setClickable(false);
                diceFour.setBackgroundResource(R.drawable.custom_button3);
            }
        });

        diceFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               diceFive.setClickable(false);
               diceFive.setBackgroundResource(R.drawable.custom_button3);
            }
        });
    }

    private void initSensor() {
        shakeSensor = new ShakeSensor(this, this);
        shakeSensor.start();

    }

    private void initUi() {
        setContentView(R.layout.activity_dice_layout);
        diceOne = findViewById(R.id.dice_1);
        diceTwo = findViewById(R.id.dice_2);
        diceThree = findViewById(R.id.dice_3);
        diceFour = findViewById(R.id.dice_4);
        diceFive = findViewById(R.id.dice_5);
        countdownDiceThrows = findViewById(R.id.countdown_rolling_the_dice);

        // cooler Trick wenn es so funktioniert wie ich mir das vorstelle ohne viele Abfragen: das playerName Array wird
        // der tableActivity mit übergeben und sobald eine Zahl eingetragen ist, also quasi der nächste Spieler dran ist,
        // wird das playerNames Array durchrotiert, sodass der aktuelle Spieler immer an der ersten Stelle steht -Q
        playerNameView = findViewById(R.id.player_name_roll_the_dice);
        playerNameView.setText(playerNames[0]);

        scoreboardButton = findViewById(R.id.button_scoreboard);
        /** "Eintragen" ermöglicht den Wechsel zur TableActivity
         *   Der Switch Case Block erstellt den richtigen Intent abhänging der Spieleranzahl
         */
        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (playerNames.length) {
                    case 2:
                        Intent intent = new Intent(RollTheDiceActivity.this, TableActivityTwoPlayers.class);
                        addExtrasAndStartNextActivity(intent);
                        break;
                    case 3:
                        Intent intent2 = new Intent(RollTheDiceActivity.this, TableActivityThreePlayers.class);
                        addExtrasAndStartNextActivity(intent2);
                        break;
                    case 4:
                        Intent intent3 = new Intent(RollTheDiceActivity.this, TableActivityFourPlayers.class);
                        addExtrasAndStartNextActivity(intent3);
                        break;
                }
            }
        });
        clearSelectedDicesButton = findViewById(R.id.button_clear_selected_dices);
        /** Ausgewählte bzw. gesperrte Würfel werden wieder entsperrt, soddass sie beim nächsten Schütteln mit verändert werden*/
        clearSelectedDicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unlockDices();
            }
        });
    }

    /**
     * fügt die Extras an den Intent und startet diesen
     * @param intent
     */
    private void addExtrasAndStartNextActivity(Intent intent) {
        intent.putExtra(EXTRA_KEY_PLAYER_NAMES_ARRAY, playerNames);
        intent.putExtra(EXTRA_KEY_ROLLED_DICE_EYE_NUMBERS, diceEyeNumber);
        startActivity(intent);
    }

    /** zählt die Anzahl der noch übrigen Würfe herunter (bei counterThrows = 0 verändern sich die Würfel nicht mehr)
     * Würfel werden geworfen und den ImageViews werden neue Würfel zufällig zugewiesen
     * */
    private void throwDices() {
        int counterThrows = Integer.parseInt(countdownDiceThrows.getText().toString());
        if (counterThrows > 0) {
            counterThrows--;
            countdownDiceThrows.setText(Integer.toString(counterThrows));
            shakeDices();
        }
    }
    /** Wird das Handy geschüttelt, werden den ImageViews der Würfel neue WürfelBilder zugeordnet*/
    private void shakeDices() {
        if (diceOne.isClickable()) {
            /** randomIndex() berechnet einen Zufalleswert zwischen 0 und 5 der dann aus dem diceDrawablePath das entsprechende Bild hervorholt*/
            diceOne.setImageResource(diceDrawablePath[diceEyeNumber[0] = randomIndex()]);
            /** speichert die aktuelle Augenzahl der angezeigten Würfel ++ weil der 0te Würfel die Augenzahl 1 hat usw...*/
            diceEyeNumber[0]++;
        }
        if (diceTwo.isClickable()) {
            diceTwo.setImageResource(diceDrawablePath[diceEyeNumber[1] = randomIndex()]);
            diceEyeNumber[1]++;
        }
        if (diceThree.isClickable()) {
            diceThree.setImageResource(diceDrawablePath[diceEyeNumber[2] = randomIndex()]);
            diceEyeNumber[2]++;
        }
        if (diceFour.isClickable()) {
            diceFour.setImageResource(diceDrawablePath[diceEyeNumber[3] = randomIndex()]);
            diceEyeNumber[3]++;
        }
        if (diceFive.isClickable()) {
            diceFive.setImageResource(diceDrawablePath[diceEyeNumber[4] = randomIndex()]);
            diceEyeNumber[4]++;
        }
        /** Nach dem Schütteln werden die gesperrten Würfel automatisch wieder entsperrt*/
        unlockDices();

    }
    /** ImageViews werden wieder clickable gestetzt sowie der Hintergrund transparent*/
    private void unlockDices() {
        diceOne.setClickable(true);
        diceOne.setBackgroundColor(Color.TRANSPARENT);
        diceTwo.setClickable(true);
        diceTwo.setBackgroundColor(Color.TRANSPARENT);
        diceThree.setClickable(true);
        diceThree.setBackgroundColor(Color.TRANSPARENT);
        diceFour.setClickable(true);
        diceFour.setBackgroundColor(Color.TRANSPARENT);
        diceFive.setClickable(true);
        diceFive.setBackgroundColor(Color.TRANSPARENT);
    }

    @SuppressLint("MissingPermission")
    private void vibrate(){
        /** Instanz von Vibrator im aktuellen Kontext holen*/
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        /** Vibriert die Zahl in Klammern in ms*/
        v.vibrate(500);
    }

    /** berechnet eine Zufallszahl zwischen 0 und 5*/
    private int randomIndex() {
        Random random = new Random();
        return random.nextInt(MAX_DICE_DIGIT);
    }

    /** Wurde das Gerät ausreichend geschüttelt, wird die Methode throwDices() aktiviert*/
    @Override
    public void onShakingDetected() {
        throwDices();
        vibrate();
    }


}
