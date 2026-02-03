/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.strategycrud.model;

/**
 *
 * @author Mateo Cevallos
 */
public class Event {

    private String id;
    private String name;
    private String date;

    public Event() {
    }

    public Event(String id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.matches("[a-zA-Z\\s]+")) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios");
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{id='" + id + "', name='" + name + "', date='" + date + "'}";
    }
}
