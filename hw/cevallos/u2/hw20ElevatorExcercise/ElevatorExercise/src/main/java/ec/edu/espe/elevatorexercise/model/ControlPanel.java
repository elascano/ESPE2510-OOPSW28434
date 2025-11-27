/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

import java.util.List;

/**
 *
 * @author Mateo Cevallos
 */
public class ControlPanel {
    private List<Button> buttons;
    private Button openButton;
    private Button closeButton;
    private Button alarmButton;
    private Button stopButton;

    public ControlPanel(List<Button> buttons, Button openButton, Button closeButton, Button alarmButton, Button stopButton) {
        this.buttons = buttons;
        this.openButton = openButton;
        this.closeButton = closeButton;
        this.alarmButton = alarmButton;
        this.stopButton = stopButton;
    }
    
    
    public void buttonPressed(Button button) {
        System.out.println("Button action executed...");
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public Button getOpenButton() {
        return openButton;
    }

    public void setOpenButton(Button openButton) {
        this.openButton = openButton;
    }

    public Button getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(Button closeButton) {
        this.closeButton = closeButton;
    }

    public Button getAlarmButton() {
        return alarmButton;
    }

    public void setAlarmButton(Button alarmButton) {
        this.alarmButton = alarmButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public void setStopButton(Button stopButton) {
        this.stopButton = stopButton;
    }
    
    
}
