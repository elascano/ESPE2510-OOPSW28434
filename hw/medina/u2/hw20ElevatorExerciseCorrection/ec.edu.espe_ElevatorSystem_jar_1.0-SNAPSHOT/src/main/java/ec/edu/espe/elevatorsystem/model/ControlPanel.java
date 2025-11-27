/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class ControlPanel extends Panel {
    private UpButton upButton = new UpButton();
    private DownButton downButton = new DownButton();

    public ControlPanel(int id) {
        super(id);
        addButton(upButton);
        addButton(downButton);
    }

    public UpButton getUpButton() { return upButton; }
    public DownButton getDownButton() { return downButton; }

    public void pressUp() { upButton.press(); }
    public void pressDown() { downButton.press(); }
}

