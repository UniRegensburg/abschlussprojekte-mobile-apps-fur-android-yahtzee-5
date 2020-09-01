package com.example.kniffel.RollTheDice;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.InsertResults.TableActivity;
import com.example.kniffel.R;
import com.example.kniffel.RollTheDice.ShakeSensor.ShakeSensor;
import com.example.kniffel.RollTheDice.ShakeSensor.ShakeSensorListener;

import java.util.Random;

public class RollTheDiceActivity extends AppCompatActivity implements ShakeSensorListener {

    private static final int MAX_DICE_DIGIT = 6;

    private ImageView diceOne;
    private ImageView diceTwo;
    private ImageView diceThree;
    private ImageView diceFour;
    private ImageView diceFive;
    private ShakeSensor shakeSensor;
    private TextView countdownDiceThrows;
    private int[] diceDrawablePath;
    private Button scoreboardButton;
    private int[] diceEyeNumber;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
        initSensor();
        setupDices();
    }


    private void setupDices() {
        diceDrawablePath = new int[]{R.drawable.dice_throw_1, R.drawable.dice_throw_2, R.drawable.dice_throw_3, R.drawable.dice_throw_4, R.drawable.dice_throw_5, R.drawable.dice_throw_6};
        diceEyeNumber = new int[]{1, 2, 3, 4, 5};
        lockDices();
    }

    // locks the dices --> numbers aren´t changing, when dice is locked
    private void lockDices() {

        diceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceOne.setClickable(false);
                diceOne.setBackgroundResource(R.drawable.custom_button3);
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
        scoreboardButton = findViewById(R.id.button_scoreboard);
        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RollTheDiceActivity.this, TableActivity.class);
                startActivity(intent);
            }
        });
    }


    private void throwDices() {
        int counterThrows = Integer.parseInt(countdownDiceThrows.getText().toString());
        if (counterThrows > 0) {
            counterThrows--;
            countdownDiceThrows.setText(Integer.toString(counterThrows));
            shakeDices();
        }
        Log.d("DiceEyeNumber", Integer.toString(diceEyeNumber[0]));
    }

    private void shakeDices() {
        if (diceOne.isClickable()) {
            diceOne.setImageResource(diceDrawablePath[diceEyeNumber[0] = randomIndex()]);
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
        unlockDices();

    }

    private void unlockDices() {
        diceOne.setClickable(true);
        diceOne.setBackgroundColor(Color.TRANSPARENT);
        diceTwo.setClickable(true);
        diceOne.setBackgroundColor(Color.TRANSPARENT);
        diceThree.setClickable(true);
        diceOne.setBackgroundColor(Color.TRANSPARENT);
        diceFour.setClickable(true);
        diceOne.setBackgroundColor(Color.TRANSPARENT);
        diceFive.setClickable(true);
    }


    private int randomIndex() {
        Random random = new Random();
        return random.nextInt(MAX_DICE_DIGIT);
    }

    @Override
    public void onShakingDetected() {
        throwDices();
    }


}
