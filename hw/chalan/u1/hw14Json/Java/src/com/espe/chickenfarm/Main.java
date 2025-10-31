package com.espe.chickenfarm;

import com.espe.chickenfarm.view.ChickenFarmSimulator;

public class Main {
    public static void main(String[] args) {
        System.out.println("- - - Kevin Chalan's Chicken Farm Simulator - - -");
        ChickenFarmSimulator farmSimulator = new ChickenFarmSimulator();
        farmSimulator.mainMenu();
    }
}