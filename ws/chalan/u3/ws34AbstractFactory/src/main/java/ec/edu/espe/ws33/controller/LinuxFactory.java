package ec.edu.espe.ws33.controller;

import ec.edu.espe.ws33.model.*;

public class LinuxFactory extends GUIFactory {
    public Button createButton() {
        return new LinuxButton();
    }

    public Menu createMenu() {
        return new LinuxMenu();
    }
}