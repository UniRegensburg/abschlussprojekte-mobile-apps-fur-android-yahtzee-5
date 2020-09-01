package com.example.kniffel.RollTheDice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.MainActivity;
import com.example.kniffel.R;
import com.example.kniffel.RollTheDice.ShakeSensor.ShakeSensor;
import com.example.kniffel.RollTheDice.ShakeSensor.ShakeSensorListener;

public class RollTheDiceActivity extends AppCompatActivity implements ShakeSensorListener {

    private ImageView diceOne;
    private ImageView diceTwo;
    private ImageView diceThree;
    private ImageView diceFour;
    private ImageView diceFive;
    private ShakeSensor shakeSensor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
        initSensor();
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
    }

    private void throwDices() {

    }

    @Override
    public void onShakingDetected() {
        throwDices();
    }


}
