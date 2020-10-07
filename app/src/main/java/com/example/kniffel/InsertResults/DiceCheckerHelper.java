package com.example.kniffel.InsertResults;

import java.util.Arrays;

import static java.lang.String.valueOf;

public class DiceCheckerHelper {

    /**
     * Zählt alle Würfelaugen zusammen.
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return alle zusammengezöhlten Würfelaugen
     */
    public static int countDiceEyeNumberTogether (int[] diceEyeNumber) {
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
    public static int countSingleDiceEyeNumbers(int[] diceEyeNumber, int countParameter) {
        int counter = 0;
        for (int element : diceEyeNumber) {
            if (element == countParameter) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Checkt ob ein 3er Pasch vorliegt
     *
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return alle Augen zählen, wenn ein 3er Pasch vorliegt sonst ein Strich
     */
    public static String check3ErPasch(int[] diceEyeNumber) {
        if (diceEyeNumber[0] == diceEyeNumber[1] && diceEyeNumber[0] == diceEyeNumber[2]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[0] == diceEyeNumber[1] && diceEyeNumber[0] == diceEyeNumber[3]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[0] == diceEyeNumber[1] && diceEyeNumber[0] == diceEyeNumber[4]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[0] == diceEyeNumber[2] && diceEyeNumber[0] == diceEyeNumber[3]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[0] == diceEyeNumber[2] && diceEyeNumber[0] == diceEyeNumber[4]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[0] == diceEyeNumber[3] && diceEyeNumber[0] == diceEyeNumber[4]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[3]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[4]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[2] == diceEyeNumber[4]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[2] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        return "/";
    }
    /**
     * Checkt ob ein 4er Pasch vorliegt
     *
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return alle Augen zählen, wenn ein 4er Pasch vorliegt sonst ein Strich
     */
    public static String check4ErPasch(int[] diceEyeNumber) {
        if (diceEyeNumber[0] == diceEyeNumber[1] && diceEyeNumber[0] == diceEyeNumber[2] && diceEyeNumber[0] == diceEyeNumber[3]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[0] == diceEyeNumber[1] && diceEyeNumber[0] == diceEyeNumber[2] && diceEyeNumber[0] == diceEyeNumber[4]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[0] == diceEyeNumber[1] && diceEyeNumber[0] == diceEyeNumber[3] && diceEyeNumber[0] == diceEyeNumber[4]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[0] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4]) {
            return valueOf(countDiceEyeNumberTogether(diceEyeNumber));
        }
        return "/";
    }

    /**
     * Checkt ob ein FullHouse gewürfelt wurde
     *
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return ist 25, wenn ein FullHouse gewürfelt wurde sonst ein Strich
     */
    public static String checkFullHouse(int[] diceEyeNumber) {
        if (diceEyeNumber[0] == diceEyeNumber[1] && diceEyeNumber[0] == diceEyeNumber[2]) {
            if (diceEyeNumber[3] == diceEyeNumber[4]) {
                return "25";            }
        }
        if (diceEyeNumber[0] == diceEyeNumber[1] && diceEyeNumber[0] == diceEyeNumber[3]) {
            if (diceEyeNumber[2] == diceEyeNumber[4]) {
                return "25";            }
        }
        if (diceEyeNumber[0] == diceEyeNumber[1] && diceEyeNumber[0] == diceEyeNumber[4]) {
            if (diceEyeNumber[2] == diceEyeNumber[3]) {
                return "25";            }
        }
        if (diceEyeNumber[0] == diceEyeNumber[2] && diceEyeNumber[0] == diceEyeNumber[3]) {
            if (diceEyeNumber[1] == diceEyeNumber[4]) {
                return "25";            }
        }
        if (diceEyeNumber[0] == diceEyeNumber[2] && diceEyeNumber[0] == diceEyeNumber[4]) {
            if (diceEyeNumber[1] == diceEyeNumber[3]) {
                return "25";            }
        }
        if (diceEyeNumber[0] == diceEyeNumber[3] && diceEyeNumber[0] == diceEyeNumber[4]) {
            if (diceEyeNumber[1] == diceEyeNumber[2]) {
                return "25";            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[3]) {
            if (diceEyeNumber[0] == diceEyeNumber[4]) {
                return "25";            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[2] && diceEyeNumber[1] == diceEyeNumber[4]) {
            if (diceEyeNumber[0] == diceEyeNumber[3]) {
                return "25";            }
        }
        if (diceEyeNumber[1] == diceEyeNumber[3] && diceEyeNumber[1] == diceEyeNumber[4]) {
            if (diceEyeNumber[0] == diceEyeNumber[2]) {
                return "25";
            }
        }
        if (diceEyeNumber[2] == diceEyeNumber[3] && diceEyeNumber[2] == diceEyeNumber[4]) {
            if (diceEyeNumber[0] == diceEyeNumber[1]) {
                return "25";
            }
        }
        return "/";
    }

    /**
     * Checkt ob eine kleine Straße gewürfelt wurde. Das übergebene Array wird am Anfang sortiert. Es kann nur eine kleine
     * Straße sein, wenn eine 1 oder eine 2 an der ersten Stelle des Arrays steht. Falls eine 1 dort steht, muss wegen
     * (1,1,2,3,4) an Stelle zwei oder drei eine 2 stehen usw. Falls eine 2 an der ersten Stelle steht, muss an Stelle zwei
     * oder drei eine 3 stehen usw. (1,2,3,4,5) ist die einzige Möglichkeit für eine 2,3,4,5 Kombination mit einer 1 vorne
     * und diese muss nicht von der zweiten Abfrage erkannt werden, da diese in der ersten schon als kleine Straße erkannt wird.
     *
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return ist 30, wenn eine kleine Straße gewürfelt wurde sonst ein Strich
     */
    public static String checkSmallStreet(int[] diceEyeNumber) {
        Arrays.sort(diceEyeNumber);
        if (diceEyeNumber[0] == 1) {
            if (diceEyeNumber[1] == 2 || diceEyeNumber[2] == 2) {
                if (diceEyeNumber[2] == 3 || diceEyeNumber[3] == 3) {
                    if (diceEyeNumber[3] == 4 || diceEyeNumber[4] == 4) {
                        return "30";
                    }
                }
            }
        }
        if (diceEyeNumber[0] == 2) {
            if (diceEyeNumber[1] == 3 || diceEyeNumber[2] == 3) {
                if (diceEyeNumber[2] == 4 || diceEyeNumber[3] == 4) {
                    if (diceEyeNumber[3] == 5 || diceEyeNumber[4] == 5) {
                        return "30";
                    }
                }
            }
        }
        return "/";
    }

    /**
     * Checkt ob eine große Straße gewürfelt wurde.
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return ist 40, wenn eine große Straße gewürfelt wurde sonst ein Strich
     */
    public static String checkGreatStreet(int[] diceEyeNumber) {
        Arrays.sort(diceEyeNumber);
        if (diceEyeNumber[0] == 1 && diceEyeNumber[1] == 2 && diceEyeNumber[2] == 3 && diceEyeNumber[3] == 4 && diceEyeNumber[4] == 5){
            return "40";
        } else if (diceEyeNumber[0] == 2 && diceEyeNumber[1] == 3 && diceEyeNumber[2] == 4 && diceEyeNumber[3] == 5 && diceEyeNumber[4] == 6) {
            return "40";
        }
        return "/";
    }

    /**
     * Checkt ob ein Kniffel gewürfelt wurde.
     * @param diceEyeNumber ist das Integer Array in dem die fünf aktuellen Würfelaugen stehen
     * @return  ist 50, wenn ein Kniffel gewürfelt wurde sonst ein Strich
     */
    public static String checkKniffel (int[] diceEyeNumber) {
        if (diceEyeNumber[0] == diceEyeNumber[1] && diceEyeNumber[0] == diceEyeNumber[2] && diceEyeNumber[0] == diceEyeNumber[3] &&diceEyeNumber[0] == diceEyeNumber[4]) {
            return "50";
        }
        return "/";
    }
}
