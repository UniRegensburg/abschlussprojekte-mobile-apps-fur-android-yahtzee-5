package com.example.kniffel.InsertResults;

import java.util.Arrays;

public class Player {

    /**
     * das Integer Array mit den gewürfelten Augen bekommt dieses von der TableActivity übergeben
     */
    private int[] diceEyeNumber;
    /**
     * Name des Spielers
     */
    private String name;
    /**
     * die 13 anklickbaren Felder
     */
    private String[] clickableValues = new String[13];
    private String subtotals, bonus, totalSum;
    /**
     * boolean flag ob dieser Player im Moment angeklickt werden darf
     */
    private boolean clickable = false;
    /**
     * boolean flag ob dieser Spieler in dieser Runde etwas eingetragen hat
     */
    private boolean hasInsertedAValue = false;
    /**
     * laufindex aus dem clickableValues String Array das am Anfang immer -1 ist weil ja immer nur das letzte Item gelöscht werden
     * soll und beim ersten aufrufen gibt es kein "letztes" Item. Der wert wird immer gesetzt sobald ein Wert eingetragen wurde
     * und von der TableActivity wieder auf -1 gesetzt wenn eine Zahl bestätigt wurde
     */
    private int runningIndexOfLastItemChanged = -1;


    public Player(String name){
        this.name = name;

        Arrays.fill(clickableValues, "");

        subtotals ="";
        bonus ="";
        totalSum ="";
    }

    /**
     * wird vor jedem neuen Eintrag aufgerufen, wenn ein Item davor angeklickt wurde (ist der Fall wenn runningIndex... != -1 ist
     * wird das Entsprechende Feld im String Array auf "" gesetzt.
     */
    public void clearLastItem () {
        if (runningIndexOfLastItemChanged != -1)
            clickableValues[runningIndexOfLastItemChanged] = "";
    }

    public boolean getHasInsertedAValue() {
        return hasInsertedAValue;
    }

    /**
     * resetet die Flag, die das letzte Item speichert, dass geändert wurde
     */
    public void resetLastItemFlag () {
        runningIndexOfLastItemChanged = -1;
    }

    public void setClickable (boolean clickable) {
        this.clickable = clickable;
    }

    public boolean getClickable () {
        return clickable;
    }

    public void setDiceEyeNumber(int[] diceEyeNumber) {
        this.diceEyeNumber = diceEyeNumber;
    }

    public String getName(){return name;}

    public String getOnes() {
        return clickableValues[0];
    }

    /**
     *   die folgenden Methoden löschen zuerst das letzte Feld setzen dann das angeklickte Feld auf den richtigen Wert mithilfe
     *   des DiceCheckerHelper setzen dann den runningIndexOfLastItemChanged auf Ihre Stelle im clickableValues String Array
     *   und setzten dann die hasInsertedAValue flag auf true sodass die Table Activity einen Fehler ausgeben kann falls in dieser
     *   Runde kein Wert eingetragen wurde
     */
    public void setOnes() {
        clearLastItem();
        this.clickableValues[0] = String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,1));
        runningIndexOfLastItemChanged = 0;
        hasInsertedAValue = true;
    }

    public String getTwosome() {
        return clickableValues[1];
    }

    public void setTwosome() {
        clearLastItem();
        this.clickableValues[1] = String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,2));
        runningIndexOfLastItemChanged = 1;
        hasInsertedAValue = true;
    }

    public String getThreesome() {
        return clickableValues[2];
    }

    public void setThreesome() {
        clearLastItem();
        this.clickableValues[2] = String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,3));
        runningIndexOfLastItemChanged = 2;
        hasInsertedAValue = true;
    }

    public String getFoursome() {
        return clickableValues[3];
    }

    public void setFoursome() {
        clearLastItem();
        this.clickableValues[3] = String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,4));
        runningIndexOfLastItemChanged = 3;
        hasInsertedAValue = true;
    }

    public String getFivesome() {
        return clickableValues[4];
    }

    public void setFivesome() {
        clearLastItem();
        this.clickableValues[4]= String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,5));
        runningIndexOfLastItemChanged = 4;
        hasInsertedAValue = true;
    }

    public String getSixsome() {
        return clickableValues[5];
    }

    public void setSixsome() {
        clearLastItem();
        this.clickableValues[5] = String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,6));
        runningIndexOfLastItemChanged = 5;
        hasInsertedAValue = true;
    }

    public String getThreeDoubles() {
        return clickableValues[6];
    }

    public void setThreeDoubles() {
        clearLastItem();
        this.clickableValues[6] = DiceCheckerHelper.check3ErPasch(diceEyeNumber);
        runningIndexOfLastItemChanged = 6;
        hasInsertedAValue = true;
    }

    public String getFourDoubles() {
        return clickableValues[7];
    }

    public void setFourDoubles() {
        clearLastItem();
        this.clickableValues[7] = DiceCheckerHelper.check4ErPasch(diceEyeNumber);
        runningIndexOfLastItemChanged = 7;
        hasInsertedAValue = true;
    }

    public String getFullHouse() {
        return clickableValues[8];
    }

    public void setFullHouse() {
        clearLastItem();
        this.clickableValues[8] = DiceCheckerHelper.checkFullHouse(diceEyeNumber);
        runningIndexOfLastItemChanged = 8;
        hasInsertedAValue = true;
    }

    public String getSmallStreet() {
        return clickableValues[9];
    }

    public void setSmallStreet() {
        clearLastItem();
        this.clickableValues[9] = DiceCheckerHelper.checkSmallStreet(diceEyeNumber);
        runningIndexOfLastItemChanged = 9;
        hasInsertedAValue = true;
    }

    public String getBigStreet() {
        return clickableValues[10];
    }

    public void setBigStreet() {
        clearLastItem();
        this.clickableValues[10] = DiceCheckerHelper.checkGreatStreet(diceEyeNumber);
        runningIndexOfLastItemChanged = 10;
        hasInsertedAValue = true;
    }

    public String getKniffel() {
        return clickableValues[11];
    }

    public void setKniffel() {
        clearLastItem();
        this.clickableValues[11] = DiceCheckerHelper.checkKniffel(diceEyeNumber);
        runningIndexOfLastItemChanged = 11;
        hasInsertedAValue = true;
    }

    public String getChance() {
        return clickableValues[12];
    }

    public void setChance() {
        clearLastItem();
        this.clickableValues[12] = String.valueOf(DiceCheckerHelper.countDiceEyeNumberTogether(diceEyeNumber));
        runningIndexOfLastItemChanged = 12;
        hasInsertedAValue = true;
    }

    public String getSubtotals() {
        return subtotals;
    }

    /**
     * counter ist der zähler der die einzelnen Werte zusammenzählt, oneBoxStillEmpty ist erst false. Sobald eine Stelle ohne einen
     * Wert gefunden wird, wird oneBoxStillEmpty auf true gesetzt und somit auch das subtotals und der Bonus nicht gesetzt
     */
    public void setSubtotals() {
        int counter = 0;
        boolean oneBoxStillEmty = false;
        for (int i = 0; i < 5 ; i++){
            if (clickableValues[i].equals("")){
                oneBoxStillEmty = true;
            } else {
                counter = counter + Integer.parseInt(clickableValues[i]);
            }
        }
        if (!oneBoxStillEmty){
            this.subtotals = String.valueOf(counter);
            if (counter >= 63){
                setBonus(true);
            } else {
                setBonus(false);
            }
        }
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(boolean flagIfReachedBonus) {
        if (flagIfReachedBonus) {
            this.bonus = "35";
        } else {
            this.bonus = "0";
        }
    }

    public String getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(String totalSum) {
        this.totalSum = totalSum;
    }


}
