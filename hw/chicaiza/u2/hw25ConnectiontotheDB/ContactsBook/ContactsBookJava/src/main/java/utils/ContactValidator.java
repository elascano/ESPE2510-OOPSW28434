package utils;

import java.util.List;

/**
 *
 * @author Daniel
 */
public class ContactValidator {
    
    public static boolean isNameValid(String name){
        return name!= null&&!name.trim().isEmpty() && name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }
    
    public static boolean isLasteNameValid(String lastName){
        return lastName!= null && !lastName.trim().isEmpty() && lastName.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }
    
    public static boolean isAgeValid(String ageText){
        try{
            int age = Integer.parseInt(ageText);
            return age > 0 && age <120;
            
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public static boolean isBirthDateSelected (java.util.Date date){
        return date!=null;
    }
    
    public static boolean isSexValid (String sex){
        return sex != null && (sex.equals("Male")|| sex.equals("Female"));
    }
    
    public static boolean areHobbiesVlid (List<String> hobbies){
        return hobbies != null &&hobbies.isEmpty();
    }
    
    
}
