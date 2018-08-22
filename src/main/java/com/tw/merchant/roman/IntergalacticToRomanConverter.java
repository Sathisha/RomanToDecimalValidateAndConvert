package com.tw.merchant.roman;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntergalacticToRomanConverter {

    private List<String> romanDenomination;
    private List<String> intergalacticUnits;
    private int decimalValue;
    private String commodityName;
    private int commodityCost;
    private int commodityCostperUnit;



    @Override
    public String toString() {
        return "IntergalacticToRomanConverter{" +
                "romanDenomination=" + romanDenomination +
                ", intergalacticUnits=" + intergalacticUnits +
                ", decimalValue=" + getDecimalValue() +
                ", commodityName='" + commodityName + '\'' +
                ", commodityCost=" + getCommodityCost() +
                ", commodityCostperUnit=" + getCommodityCostperUnit()+
                '}';
    }

    public IntergalacticToRomanConverter() {
        this.romanDenomination = new ArrayList<String>();
        this.intergalacticUnits = new ArrayList<String>();;
    }

    public List<String> getRomanDenomination() {
        return romanDenomination;
    }

    public String printRomanDenomination(){
        String result = "";
        for(String romanDen: romanDenomination){
            result += romanDen + " ";
        }
        return result;
    }

    public String printIntergalacticDenomination(){
        String result = "";
        for(String den: intergalacticUnits){
            result += den + " ";
        }
        return result;
    }

    public void setRomanDenomination(List<String> romanDenomination) {
        this.romanDenomination = romanDenomination;
    }

    public List<String> getIntergalacticUnits() {
        return intergalacticUnits;
    }

    public void setIntergalacticUnits(List<String> intergalacticUnits) {
        this.intergalacticUnits = intergalacticUnits;
    }

    public int getDecimalValue() {
        this.decimalValue = calculateDecimalValue();
        return decimalValue;
    }

    private int calculateDecimalValue() {

        int result = 0;
        RomanNumberFormat rnf = new RomanNumberFormat();
        for (int i=0; i<romanDenomination.size();i++){
            int decimalValue = rnf.getDecimalDenominations()[rnf.getRomanDenominations().indexOf(romanDenomination.get(i))] ;

            if(i+1< romanDenomination.size()){
                int nextDecimalValue = rnf.getDecimalDenominations()[rnf.getRomanDenominations().indexOf(romanDenomination.get(i+1))] ;

                if(decimalValue>=nextDecimalValue){
                    result = result + decimalValue;
                }else{
                    result = result + nextDecimalValue - decimalValue;
                    i++;
                }
            }else{
                result = result + decimalValue;
                i++;
            }
        }

        return result;
    }

    public void setDecimalValue(int decimalValue) {
        this.decimalValue = decimalValue;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getCommodityCost() {
        return commodityCost;
    }

    public void setCommodityCost(int commodityCost) {
        this.commodityCost = commodityCost;
    }

    public int getCommodityCostperUnit() {
        this.commodityCostperUnit = getCommodityCost()/getDecimalValue();
        return commodityCostperUnit;
    }

    public void setCommodityCostperUnit(int commodityCostperUnit) {
        this.commodityCostperUnit = commodityCostperUnit;
    }
}
