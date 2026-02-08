package ec.edu.espe.ws33.controller;

import ec.edu.espe.ws33.model.*;

public class WinFactory extends GUIFactory {
    public Button createButton() {
        return new WinButton();
    }

    public Menu createMenu() {
        return new WinMenu();
    }
}