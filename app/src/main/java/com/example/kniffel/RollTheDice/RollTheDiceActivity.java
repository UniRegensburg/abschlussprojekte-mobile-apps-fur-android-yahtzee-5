package com.example.kniffel.RollTheDice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.InsertResults.TableActivity;
import com.example.kniffel.MainActivity;
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
        setupDiceArrays();
    }


    private void setupDiceArrays() {
        diceDrawablePath = new int[]{R.drawable.dice_throw_1, R.drawable.dice_throw_2, R.drawable.dice_throw_3, R.drawable.dice_throw_4, R.drawable.dice_throw_5, R.drawable.dice_throw_6};
        diceEyeNumber = new int[]{1, 2, 3, 4, 5};
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
        diceOne.setImageResource(diceDrawablePath[diceEyeNumber[0] = randomIndex()]);
        diceEyeNumber[0]++;
        diceTwo.setImageResource(diceDrawablePath[diceEyeNumber[1] = randomIndex()]);
        diceEyeNumber[1]++;
        diceThree.setImageResource(diceDrawablePath[diceEyeNumber[2] = randomIndex()]);
        diceEyeNumber[2]++;
        diceFour.setImageResource(diceDrawablePath[diceEyeNumber[3] = randomIndex()]);
        diceEyeNumber[3]++;
        diceFive.setImageResource(diceDrawablePath[diceEyeNumber[4] = randomIndex()]);
        diceEyeNumber[4]++;
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
