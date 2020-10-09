package com.example.kniffel.RollTheDice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.InsertResults.TableActivity;
import com.example.kniffel.R;
import com.example.kniffel.RollTheDice.ShakeSensor.ShakeSensor;
import com.example.kniffel.RollTheDice.ShakeSensor.ShakeSensorListener;

import java.util.Arrays;
import java.util.Random;

public class RollTheDiceActivity extends AppCompatActivity implements ShakeSensorListener {

    /**
     * Extra Key um der Table Activity das gewürfelte Array als Intent für das Result mitzugeben
     */
    public static final String EXTRA_KEY_ROLLED_DICE_EYE_NUMBERS = "EYE_NUMBERS";
    public static final String EXTRA_KEY_ROLLS_LEFT = "ROLLS_LEFT";

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
    private Button scoreboardButton;
    /** aktueller SpielerName */
    private String currentPlayer;
    /** übrige Würfe die von der TableActivity als Extra übergeben werden */
    private int rollsLeft;
    /** boolean Array in dem gespeichter wird welche Würfel gewürfelt werden dürfen bei true: dieser Würfel wird nicht gewürfelt */
    private boolean[] diceLocked = new boolean[5];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtrasFromIntent();
        //diceEyeNumber = new int[]{1, 2, 3, 4, 5};
        initUi();
        initSensor();
        setupDices();
        Arrays.fill(diceLocked, false);
    }

    private void getExtrasFromIntent() {
        Bundle extras = getIntent().getExtras();
        currentPlayer = extras.getString(TableActivity.EXTRA_KEY_CURRENT_PLAYER);
        rollsLeft = extras.getInt(TableActivity.EXTRA_KEY_ROLLS_LEFT);

        if (extras.getIntArray(TableActivity.EXTRA_KEY_DICE_EYE_NUMBER) != null) {
            diceEyeNumber = extras.getIntArray(TableActivity.EXTRA_KEY_DICE_EYE_NUMBER);
        } else {
            diceEyeNumber = new int[]{1, 2, 3, 4, 5};
        }
    }


    private void setupDices() {
        /** Array mit Speicheradresse der Würfel*/
        diceDrawablePath = new int[]{R.drawable.dice_throw_1, R.drawable.dice_throw_2, R.drawable.dice_throw_3, R.drawable.dice_throw_4, R.drawable.dice_throw_5, R.drawable.dice_throw_6};
        diceOne.setImageResource(diceDrawablePath[diceEyeNumber[0]-1]);
        diceTwo.setImageResource(diceDrawablePath[diceEyeNumber[1]-1]);
        diceThree.setImageResource(diceDrawablePath[diceEyeNumber[2]-1]);
        diceFour.setImageResource(diceDrawablePath[diceEyeNumber[3]-1]);
        diceFive.setImageResource(diceDrawablePath[diceEyeNumber[4]-1]);
        setListenersToLockDices();
    }

    /** Würfel werden umrandet und deaktiviert und pink umrandet, sodass sie sich beim nächsten Schütteln nicht mitverändern */
    private void setListenersToLockDices() {

        diceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rollsLeft != 3) {
                    if (diceLocked[0]) {
                        diceOne.setBackgroundColor(Color.TRANSPARENT);
                        diceLocked[0] = false;
                    } else {
                        diceOne.setBackgroundResource(R.drawable.custom_button3);
                        diceLocked[0] = true;
                    }
                }
            }
        });

        diceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rollsLeft != 3) {
                    if (diceLocked[1]) {
                        diceTwo.setBackgroundColor(Color.TRANSPARENT);
                        diceLocked[1] = false;
                    } else {
                        diceTwo.setBackgroundResource(R.drawable.custom_button3);
                        diceLocked[1] = true;
                    }
                }
            }
        });

        diceThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rollsLeft != 3) {
                    if (diceLocked[2]) {
                        diceThree.setBackgroundColor(Color.TRANSPARENT);
                        diceLocked[2] = false;
                    } else {
                        diceThree.setBackgroundResource(R.drawable.custom_button3);
                        diceLocked[2] = true;
                    }
                }
            }
        });

        diceFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rollsLeft != 3) {
                    if (diceLocked[3]) {
                        diceFour.setBackgroundColor(Color.TRANSPARENT);
                        diceLocked[3] = false;
                    } else {
                        diceFour.setBackgroundResource(R.drawable.custom_button3);
                        diceLocked[3] = true;
                    }
                }
            }
        });

        diceFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rollsLeft != 3) {
                    if (diceLocked[4]) {
                        diceFive.setBackgroundColor(Color.TRANSPARENT);
                        diceLocked[4] = false;
                    } else {
                        diceFive.setBackgroundResource(R.drawable.custom_button3);
                        diceLocked[4] = true;
                    }
                }
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
        countdownDiceThrows.setText(String.valueOf(rollsLeft));


        playerNameView = findViewById(R.id.player_name_roll_the_dice);
        playerNameView.setText(currentPlayer);

        scoreboardButton = findViewById(R.id.button_scoreboard);

        /**
         * "Eintragen" ermöglicht den Wechsel zurück zur TableActivity bekommt das WürfelArray mitgegeben
         */
        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rollsLeft != 3) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(EXTRA_KEY_ROLLED_DICE_EYE_NUMBERS, diceEyeNumber);
                    resultIntent.putExtra(EXTRA_KEY_ROLLS_LEFT, rollsLeft);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(RollTheDiceActivity.this, R.string.error_message_not_rolled_the_dices, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /** zählt die Anzahl der noch übrigen Würfe herunter (bei counterThrows = 0 verändern sich die Würfel nicht mehr)
     * Würfel werden geworfen und den ImageViews werden neue Würfel zufällig zugewiesen
     * */
    private void throwDices() {
        if (rollsLeft > 0) {
            rollsLeft--;
            countdownDiceThrows.setText(String.valueOf(rollsLeft));
            shakeDices();
            vibrate();
        }
    }
    /** Wird das Handy geschüttelt, werden den ImageViews der Würfel neue WürfelBilder zugeordnet*/
    private void shakeDices() {
        if (!diceLocked[0]) {
            /** randomIndex() berechnet einen Zufalleswert zwischen 0 und 5 der dann aus dem diceDrawablePath das entsprechende Bild hervorholt*/

            diceOne.setImageResource(diceDrawablePath[diceEyeNumber[0] = randomIndex()]);
            /** speichert die aktuelle Augenzahl der angezeigten Würfel ++ weil der 0te Würfel die Augenzahl 1 hat usw...*/
            diceEyeNumber[0]++;
        }
        if (!diceLocked[1]) {
            diceTwo.setImageResource(diceDrawablePath[diceEyeNumber[1] = randomIndex()]);
            diceEyeNumber[1]++;
        }
        if (!diceLocked[2]) {
            diceThree.setImageResource(diceDrawablePath[diceEyeNumber[2] = randomIndex()]);
            diceEyeNumber[2]++;
        }
        if (!diceLocked[3]) {
            diceFour.setImageResource(diceDrawablePath[diceEyeNumber[3] = randomIndex()]);
            diceEyeNumber[3]++;
        }
        if (!diceLocked[4]) {
            diceFive.setImageResource(diceDrawablePath[diceEyeNumber[4] = randomIndex()]);
            diceEyeNumber[4]++;
        }
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
    }


}
