package com.tw.merchant.roman;

import java.util.ArrayList;
import java.util.List;

public class RomanNumberFormat {

    //public static enum RomanDenominators  {I, V, X, L, C, D, M};

    private List romanDenominations;
    private int[] decimalDenominations;

    public RomanNumberFormat(){

        System.out.println("Initializing Denominations...");

        this.decimalDenominations = new int[7];
        this.romanDenominations = new ArrayList<String>();
        this.romanDenominations.add("I");
        this.romanDenominations.add("V");
        this.romanDenominations.add("X");
        this.romanDenominations.add("L");
        this.romanDenominations.add("C");
        this.romanDenominations.add("D");
        this.romanDenominations.add("M");

        this.decimalDenominations[0] = 1;
        this.decimalDenominations[1] = 5;
        this.decimalDenominations[2] = 10;
        this.decimalDenominations[3] = 50;
        this.decimalDenominations[4] = 100;
        this.decimalDenominations[5] = 500;
        this.decimalDenominations[6] = 1000;

    }

    public List<String> getRomanDenominations() {
        return romanDenominations;
    }

    public void setRomanDenominations(List romanDenominations) {
        this.romanDenominations = romanDenominations;
    }

    public int[] getDecimalDenominations() {
        return decimalDenominations;
    }

    public void setDecimalDenominations(int[] decimalDenominations) {
        this.decimalDenominations = decimalDenominations;
    }
}
