package ec.edu.espe.elevator.model;

/**
 *
 * @author Paulo Ramos
 */
public abstract class Button {
    
    private String nameButton;

    public Button(String nameButton) {
        this.nameButton = nameButton;
    }

    
    /**
     * @return the nameButton
     */
    public String getNameButton() {
        return nameButton;
    }

    /**
     * @param nameButton the nameButton to set
     */
    public void setNameButton(String nameButton) {
        this.nameButton = nameButton;
    }
    
}

