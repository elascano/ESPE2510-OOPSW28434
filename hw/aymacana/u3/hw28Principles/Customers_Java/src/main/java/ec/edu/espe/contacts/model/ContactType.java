package ec.edu.espe.contacts.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public enum ContactType {
    NORMAL("Normal"),
    FRECUENT("Frquent");
    
    private final String displayName;
    
    ContactType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static ContactType fromString(String text) {
        for (ContactType type : ContactType.values()) {
            if (type.displayName.equalsIgnoreCase(text)) {
                return type;
            }
        }
        return NORMAL;
    }
}
