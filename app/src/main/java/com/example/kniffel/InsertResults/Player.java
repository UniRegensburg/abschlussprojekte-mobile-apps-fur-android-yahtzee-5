package com.example.kniffel.InsertResults;

public class Player {

    private String name;
    private String ones, twosome, threesome, foursome, fivesome, sixsome, subtotals,bonus, threeDoubles, fourDoubles,
    fullHouse, smallStreet, bigStreet, kniffel, chance, totalSum;

    public Player(String name){
        this.name = name;
        ones = "";
        twosome = "";
        threesome = "";
        foursome ="";
        fivesome ="";
        sixsome ="";
        subtotals ="";
        bonus ="";
        threeDoubles ="";
        fourDoubles ="";
        fullHouse ="";
        smallStreet ="";
        bigStreet ="";
        kniffel ="";
        chance ="";
        totalSum ="";
    }

    public String getName(){return name;}

    public void setName(String newName){ name = newName; }

    public String getOnes() {
        return ones;
    }

    public void setOnes(String ones) {
        this.ones = ones;
    }

    public String getTwosome() {
        return twosome;
    }

    public void setTwosome(String twosome) {
        this.twosome = twosome;
    }

    public String getThreesome() {
        return threesome;
    }

    public void setThreesome(String threesome) {
        this.threesome = threesome;
    }

    public String getFoursome() {
        return foursome;
    }

    public void setFoursome(String foursome) {
        this.foursome = foursome;
    }

    public String getFivesome() {
        return fivesome;
    }

    public void setFivesome(String fivesome) {
        this.fivesome = fivesome;
    }

    public String getSixsome() {
        return sixsome;
    }

    public void setSixsome(String sixsome) {
        this.sixsome = sixsome;
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
        return threeDoubles;
    }

    public void setThreeDoubles(String threeDoubles) {
        this.threeDoubles = threeDoubles;
    }

    public String getFourDoubles() {
        return fourDoubles;
    }

    public void setFourDoubles(String fourDoubles) {
        this.fourDoubles = fourDoubles;
    }

    public String getFullHouse() {
        return fullHouse;
    }

    public void setFullHouse(String fullHouse) {
        this.fullHouse = fullHouse;
    }

    public String getSmallStreet() {
        return smallStreet;
    }

    public void setSmallStreet(String smallStreet) {
        this.smallStreet = smallStreet;
    }

    public String getBigStreet() {
        return bigStreet;
    }

    public void setBigStreet(String bigStreet) {
        this.bigStreet = bigStreet;
    }

    public String getKniffel() {
        return kniffel;
    }

    public void setKniffel(String kniffel) {
        this.kniffel = kniffel;
    }

    public String getChance() {
        return chance;
    }

    public void setChance(String chance) {
        this.chance = chance;
    }

    public String getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(String totalSum) {
        this.totalSum = totalSum;
    }


}
