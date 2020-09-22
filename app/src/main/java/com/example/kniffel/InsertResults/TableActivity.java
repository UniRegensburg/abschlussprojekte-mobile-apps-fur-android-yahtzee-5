package com.example.kniffel.InsertResults;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.R;

import java.util.Arrays;

public class TableActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvitiy_table_three_player);
    }

    /**
     * Zählt alle Würfelaugen zusammen.
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return alle zusammengezöhlten Würfelaugen
     */
    private int countDiceEyeNumberTogether (int[] diceEyeNumber) {
        int counter = 0;
        for (int element : diceEyeNumber) {
            counter = counter + element;
            }
        return counter;
    }

    /**
     * @param diceEyeNumber  ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @param countParameter ist der Parameter für das z.B. die Einser gezählt werden müssen
     *                       also muss für dieses Beispiel hier die 1 übergeben werden.
     * @return gibt die Anzahl der entsprechend gewürfelten (für das letzte Bsp.) Einser zurück
     */
    private int countSingleDiceEyeNumbers(int[] diceEyeNumber, int countParameter) {
        int counter = 0;
        for (int element : diceEyeNumber) {
            if (element == countParameter) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Das geht sicher schöner. Checkt ob ein 3er Pasch vorliegt
     *
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen steheni
     * @return ist true, wenn ein 3er Pasch vorliegt
     */
    private boolean check3ErPasch(int[] diceEyeNumber) {
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[3]) {
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[4]) {
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[5]) {
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4]) {
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[5]) {
            return true;
        }
        if (diceEyeNumber[1] == diceEyeNumber[4] && diceEyeNumber[1] == diceEyeNumber[5]) {
            return true;
        }
        if (diceEyeNumber[2] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4]) {
            return true;
        }
        if (diceEyeNumber[2] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[5]) {
            return true;
        }
        if (diceEyeNumber[2] == diceEyeNumber[4] && diceEyeNumber[1] == diceEyeNumber[5]) {
            return true;
        }
        if (diceEyeNumber[3] == diceEyeNumber[4] && diceEyeNumber[1] == diceEyeNumber[5]) {
            return true;
        }
        return false;
    }

    /**
     * Das geht sicher schöner. Checkt ob ein 4er Pasch vorliegt
     *
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return ist true, wenn ein 3er Pasch vorliegt
     */
    private boolean check4ErPasch(int[] diceEyeNumber) {
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
     *
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return ist true, wenn ein FullHouse gewürfelt wurde
     */
    private boolean checkFullHouse(int[] diceEyeNumber) {
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[3]) {
            if (diceEyeNumber[4] == diceEyeNumber[5]) {
                return true;
            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[4]) {
            if (diceEyeNumber[3] == diceEyeNumber[5]) {
                return true;
            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[5]) {
            if (diceEyeNumber[3] == diceEyeNumber[4]) {
                return true;
            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4]) {
            if (diceEyeNumber[2] == diceEyeNumber[5]) {
                return true;
            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[5]) {
            if (diceEyeNumber[2] == diceEyeNumber[4]) {
                return true;
            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[4] && diceEyeNumber[1] == diceEyeNumber[5]) {
            if (diceEyeNumber[2] == diceEyeNumber[3]) {
                return true;
            }
        }
        if (diceEyeNumber[2] == diceEyeNumber[3] && diceEyeNumber[2] == diceEyeNumber[4]) {
            if (diceEyeNumber[1] == diceEyeNumber[5]) {
                return true;
            }
        }
        if (diceEyeNumber[2] == diceEyeNumber[3] && diceEyeNumber[2] == diceEyeNumber[5]) {
            if (diceEyeNumber[1] == diceEyeNumber[4]) {
                return true;
            }
        }
        if (diceEyeNumber[2] == diceEyeNumber[4] && diceEyeNumber[2] == diceEyeNumber[5]) {
            if (diceEyeNumber[1] == diceEyeNumber[3]) {
                return true;
            }
        }
        if (diceEyeNumber[3] == diceEyeNumber[4] && diceEyeNumber[3] == diceEyeNumber[5]) {
            if (diceEyeNumber[1] == diceEyeNumber[2]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checkt ob eine kleine Straße gewürfelt wurde. Das übergebene Array wird am Anfang sortiert. Es kann nur eine kleine
     * Straße sein, wenn eine 1 oder eine 2 an der ersten Stelle des Arrays steht. Falls eine 1 dort steht, muss wegen
     * (1,1,2,3,4) an Stelle zwei oder drei eine 2 stehen usw. Falls eine 2 an der ersten Stelle steht, muss an Stelle zwei
     * oder drei eine 3 stehen usw. (1,2,3,4,5) ist die einzige Möglichkeit für eine 2,3,4,5 Kombination mit einer 1 vorne
     * und diese muss nicht von der zweiten Abfrage erkannt werden, da diese in der ersten schon als kleine Straße erkannt wird.
     *
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return ist true, wenn eine kleine Straße gewürfelt wurde
     */
    private boolean checkSmallStreet(int[] diceEyeNumber) {
        Arrays.sort(diceEyeNumber);
        if (diceEyeNumber[1] == 1) {
            if (diceEyeNumber[2] == 2 || diceEyeNumber[3] == 2) {
                if (diceEyeNumber[3] == 3 || diceEyeNumber[4] == 3) {
                    if (diceEyeNumber[4] == 4 || diceEyeNumber[5] == 4) {
                        return true;
                    }
                }
            }
        }
        if (diceEyeNumber[1] == 2) {
            if (diceEyeNumber[2] == 3 || diceEyeNumber[3] == 3) {
                if (diceEyeNumber[3] == 4 || diceEyeNumber[4] == 4) {
                    if (diceEyeNumber[4] == 5 || diceEyeNumber[5] == 5) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checkt ob eine große Straße gewürfelt wurde.
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return ist true, wenn eine große Straße gewürfelt wurde.
     */
    private boolean checkGreatStreet(int[] diceEyeNumber) {
        Arrays.sort(diceEyeNumber);
        if (diceEyeNumber[1] == 1 && diceEyeNumber[2] == 2 && diceEyeNumber[3] == 3 && diceEyeNumber[4] == 4 && diceEyeNumber[5] == 5){
            return true;
        }
        return false;
    }

    /**
     * Checkt ob ein Kniffel gewürfelt wurde.
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return  ist true, wenn ein Kniffel gewürfelt wurde.
     */
    private boolean checkKniffel (int[] diceEyeNumber) {
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4] &&diceEyeNumber[1] == diceEyeNumber[5]) {
            return true;
        }
        return false;
    }
}
