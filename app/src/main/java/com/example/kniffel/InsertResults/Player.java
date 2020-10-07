package com.example.kniffel.InsertResults;

import java.util.Arrays;

public class Player {

    private int[] diceEyeNumber;
    private String name;
    /**
     * die 13 anklickbaren Felder
     */
    private String[] clickableValues = new String[13];
    private String subtotals,bonus, totalSum;
    /**
     * boolean flag ob dieser Player im Moment angeklickt werden darf
     */
    private boolean clickable = false;
    /**
     * boolean flag ob dieser Spieler in dieser Runde etwas eingetragen hat
     */
    private boolean hasInsertetAValue = false;
    private int runningIndexOfLastItemChanged = -1;


    public Player(String name){
        this.name = name;

        Arrays.fill(clickableValues, "");

        subtotals ="";
        bonus ="";
        totalSum ="";
    }

    public void clearLastItem () {
        if (runningIndexOfLastItemChanged != -1)
            clickableValues[runningIndexOfLastItemChanged] = "";
    }

    public boolean getHasInsertetAValue () {
        return hasInsertetAValue;
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
     *   die ones werden auf die Anzahl der gezählten einser gesetzt
     */
    public void setOnes() {
        clearLastItem();
        this.clickableValues[0] = String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,1));
        runningIndexOfLastItemChanged = 0;
        hasInsertetAValue = true;
    }

    public String getTwosome() {
        return clickableValues[1];
    }

    public void setTwosome() {
        clearLastItem();
        this.clickableValues[1] = String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,2));
        runningIndexOfLastItemChanged = 1;
        hasInsertetAValue = true;
    }

    public String getThreesome() {
        return clickableValues[2];
    }

    public void setThreesome() {
        clearLastItem();
        this.clickableValues[2] = String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,3));
        runningIndexOfLastItemChanged = 2;
        hasInsertetAValue = true;
    }

    public String getFoursome() {
        return clickableValues[3];
    }

    public void setFoursome() {
        clearLastItem();
        this.clickableValues[3] = String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,4));
        runningIndexOfLastItemChanged = 3;
        hasInsertetAValue = true;
    }

    public String getFivesome() {
        return clickableValues[4];
    }

    public void setFivesome() {
        clearLastItem();
        this.clickableValues[4]= String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,5));
        runningIndexOfLastItemChanged = 4;
        hasInsertetAValue = true;
    }

    public String getSixsome() {
        return clickableValues[5];
    }

    public void setSixsome() {
        clearLastItem();
        this.clickableValues[5] = String.valueOf(DiceCheckerHelper.countSingleDiceEyeNumbers(diceEyeNumber,6));
        runningIndexOfLastItemChanged = 5;
        hasInsertetAValue = true;
    }

    public String getSubtotals() {
        return subtotals;
    }

    public void setSubtotals(String subtotals) {
        this.subtotals = subtotals;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getThreeDoubles() {
        return clickableValues[6];
    }

    public void setThreeDoubles() {
        clearLastItem();
        this.clickableValues[6] = DiceCheckerHelper.check3ErPasch(diceEyeNumber);
        runningIndexOfLastItemChanged = 6;
        hasInsertetAValue = true;
    }

    public String getFourDoubles() {
        return clickableValues[7];
    }

    public void setFourDoubles() {
        clearLastItem();
        this.clickableValues[7] = DiceCheckerHelper.check4ErPasch(diceEyeNumber);
        runningIndexOfLastItemChanged = 7;
        hasInsertetAValue = true;
    }

    public String getFullHouse() {
        return clickableValues[8];
    }

    public void setFullHouse() {
        clearLastItem();
        this.clickableValues[8] = DiceCheckerHelper.checkFullHouse(diceEyeNumber);
        runningIndexOfLastItemChanged = 8;
        hasInsertetAValue = true;
    }

    public String getSmallStreet() {
        return clickableValues[9];
    }

    public void setSmallStreet() {
        clearLastItem();
        this.clickableValues[9] = DiceCheckerHelper.checkSmallStreet(diceEyeNumber);
        runningIndexOfLastItemChanged = 9;
        hasInsertetAValue = true;
    }

    public String getBigStreet() {
        return clickableValues[10];
    }

    public void setBigStreet() {
        clearLastItem();
        this.clickableValues[10] = DiceCheckerHelper.checkGreatStreet(diceEyeNumber);
        runningIndexOfLastItemChanged = 10;
        hasInsertetAValue = true;
    }

    public String getKniffel() {
        return clickableValues[11];
    }

    public void setKniffel() {
        clearLastItem();
        this.clickableValues[11] = DiceCheckerHelper.checkKniffel(diceEyeNumber);
        runningIndexOfLastItemChanged = 11;
        hasInsertetAValue = true;
    }

    public String getChance() {
        return clickableValues[12];
    }

    public void setChance() {
        clearLastItem();
        this.clickableValues[12] = String.valueOf(DiceCheckerHelper.countDiceEyeNumberTogether(diceEyeNumber));
        runningIndexOfLastItemChanged = 12;
        hasInsertetAValue = true;
    }

    public String getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(String totalSum) {
        this.totalSum = totalSum;
    }


}
