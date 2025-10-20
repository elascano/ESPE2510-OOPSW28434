/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmsimulador.model;

/**
 *
 * @author Steven Loza
 * @version 0.1
 */
public class Chicken {
    private int Id;
    private String name;
    private String color;
    private int age;
    private boolean isMolting;
    
//    
    //setters
    public void setId(int id){
        this.Id =id;
    }
//    
//    //gettters
    public int getId(){
        return Id;
    }

    public Chicken(int Id, String name, String color, int age, boolean isMolting) {
        this.Id = Id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    @Override
    public String toString() {
        return "\nChicken{" + "\nId -> \t" + Id + ", \nname-> \t" + name + ", \ncolor-> \t" + color + ", \nage-> \t" + age + ", \nisMolting-> \t" + isMolting + "\n}\t";
    }

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the isMolting
     */
    public boolean isIsMolting() {
        return isMolting;
    }

    /**
     * @param isMolting the isMolting to set
     */
    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }
}
