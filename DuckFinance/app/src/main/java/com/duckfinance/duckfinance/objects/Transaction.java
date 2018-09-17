package com.duckfinance.duckfinance.objects;

import com.duckfinance.duckfinance.R;

import android.content.Context;
import android.content.res.Resources;
import java.util.Date;

public class Transaction {

    private String date;
    private String category;
    private double amount;

    public Transaction(String category,String date, double amount){
        //TODO: get context for use string resources
        this.category = category;
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public int getCategoryAsNumber(){

        if(category.equals("Alimentacion")){
            return 0;
        }else if(category.equals("Cuentas y Pagos")){
            return 1;
        }else if(category.equals("Casa")){
            return 2;
        }else if(category.equals("Ropa")){
            return 3;
        }else if(category.equals("Diversion")){
            return 4;
        }else if(category.equals("Transporte")){
            return 5;
        }else if(category.equals("Salud e Higiene")){
            return 6;
        }else if(category.equals("Otros Gastos")){
            return 7;
        }
        return -1;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getImageIdColor(){
        int color;
        if(category.equals("Alimentacion")){
            return R.drawable.rom_5;
        }else if(category.equals("Cuentas y Pagos")){
            return R.drawable.rom_2;
        }else if(category.equals("Casa")){
            return R.drawable.rom_4;
        }else if(category.equals("Ropa")){
            return R.drawable.rom_7;
        }else if(category.equals("Diversion")){
            return R.drawable.rom_6;
        }else if(category.equals("Transporte")){
            return R.drawable.rom_3;
        }else if(category.equals("Salud e Higiene")){
            return R.drawable.rom_1;
        }else if(category.equals("Otros Gastos")){
            return R.drawable.rom_8;
        }
        return R.drawable.ic_launcher_background;
    }

    public Date getDateFormat(){
        return new Date(date);
    }

    public static String getCategoryNameFromNumber(int i){
        switch (i){
            case 0:
                return "Alimentacion";
            case 1:
                return "Cuentas y Pagos";
            case 2:
                return "Casa";
            case 3:
                return "Ropa";
            case 4:
                return "Diversion";
            case 5:
                return "Transporte";
            case 6:
                return "Salud e Higiene";
            case 7:
                return "Otros Gastos";
            default:
                return "unknown";
        }
    }
}
