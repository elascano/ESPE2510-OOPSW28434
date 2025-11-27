/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
import java.util.ArrayList;
import java.util.List;

public abstract class Panel {
    protected int panelId;
    protected List<Button> buttons = new ArrayList<>();

    public Panel(int panelId) {
        this.panelId = panelId;
    }

    public List<Button> getButtons() { return buttons; }
    public void addButton(Button b) { buttons.add(b); }
}

