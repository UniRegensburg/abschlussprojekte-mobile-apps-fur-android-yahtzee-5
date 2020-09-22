package com.example.kniffel.InsertResults;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.R;

public class TableActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvitiy_table_three_player);
    }

    /**
     *
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @param countParameter ist der Parameter für das z.B. die Einser gezählt werden müssen
     *                       also muss für dieses Beispiel hier die 1 übergeben werden.
     * @return gibt die Anzahl der entsprechend gewürfelten (für das letzte Bsp.) Einser zurück
     */
    private int countSingleDiceEyeNumbers (int[] diceEyeNumber, int countParameter) {
        int counter = 0;
        for (int element : diceEyeNumber){
            if(element == countParameter){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Das geht sicher schöner. Checkt ob ein 3er Pasch vorliegt
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen steheni
     * @return ist true, wenn ein 3er Pasch vorliegt
     */
    private boolean check3ErPasch (int[] diceEyeNumber) {
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[3]){
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[4]){
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[5]){
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4]){
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[5]){
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[4] && diceEyeNumber[1] == diceEyeNumber[5]){
            return true;
        }
        if (diceEyeNumber[2] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4]){
            return true;
        }
        if (diceEyeNumber[2] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[5]){
            return true;
        }
        if (diceEyeNumber[2] == diceEyeNumber[4] && diceEyeNumber[1] == diceEyeNumber[5]){
            return true;
        }
        if (diceEyeNumber[3] == diceEyeNumber[4] && diceEyeNumber[1] == diceEyeNumber[5]){
            return true;
        }
        return false;
    }

    /**
     * Das geht sicher schöner. Checkt ob ein 4er Pasch vorliegt
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return ist true, wenn ein 3er Pasch vorliegt
     */
    private boolean check4ErPasch (int[] diceEyeNumber) {
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4]) {
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[5]) {
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[4] && diceEyeNumber[1] == diceEyeNumber[5]) {
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4] && diceEyeNumber[1] == diceEyeNumber[5]) {
            return true;
        }
        if (diceEyeNumber[2] == diceEyeNumber[3] && diceEyeNumber[2] == diceEyeNumber[4] && diceEyeNumber[2] == diceEyeNumber[5]) {
            return true;
        }
        return false;
    }

    /**
     * Checkt ob ein FullHouse gewürfelt wurde
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return ist true, wenn ein FullHouse gewürfelt wurde
     */
    private boolean checkFullHouse (int[] diceEyeNumber) {
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[3]){
            if(diceEyeNumber[4] == diceEyeNumber[5]){
                return true;
            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[4]){
            if(diceEyeNumber[3] == diceEyeNumber[5]){
                return true;
            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[5]){
            if(diceEyeNumber[3] == diceEyeNumber[4]){
                return true;
            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4]){
            if(diceEyeNumber[2] == diceEyeNumber[5]){
                return true;
            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[5]){
            if(diceEyeNumber[2] == diceEyeNumber[4]){
                return true;
            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[4] && diceEyeNumber[1] == diceEyeNumber[5]){
            if(diceEyeNumber[2] == diceEyeNumber[3]){
                return true;
            }
        }
        if (diceEyeNumber[2] == diceEyeNumber[3] && diceEyeNumber[2] == diceEyeNumber[4]){
            if(diceEyeNumber[1] == diceEyeNumber[5]){
                return true;
            }
        }
        if (diceEyeNumber[2] == diceEyeNumber[3] && diceEyeNumber[2] == diceEyeNumber[5]){
            if(diceEyeNumber[1] == diceEyeNumber[4]){
                return true;
            }
        }
        if (diceEyeNumber[2] == diceEyeNumber[4] && diceEyeNumber[2] == diceEyeNumber[5]){
            if(diceEyeNumber[1] == diceEyeNumber[3]){
                return true;
            }
        }
        if (diceEyeNumber[3] == diceEyeNumber[4] && diceEyeNumber[3] == diceEyeNumber[5]){
            if(diceEyeNumber[1] == diceEyeNumber[2]){
                return true;
            }
        }
        return false;
    }

    //private boolean checkSmallStreet (int[] diceEyeNumber) {
    //    if ()
    //}
}
