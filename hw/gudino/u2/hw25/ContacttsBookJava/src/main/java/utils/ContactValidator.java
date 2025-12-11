package utils;

import java.util.List;
/**
 *
 * @author Bryan Gudino, @ESPE
 */
public class ContactValidator {
    
    public static boolean isNameValid(String name){
        return name!= null && !name.trim().isEmpty() && name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+");
    }
    
    public static boolean isLasteNameValid(String lastName){
        return lastName!= null && !lastName.trim().isEmpty() && lastName.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+");
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
    
    public static boolean areHobbiesValid (List<String> hobbies){
        return hobbies != null &&hobbies.isEmpty();
    }
}