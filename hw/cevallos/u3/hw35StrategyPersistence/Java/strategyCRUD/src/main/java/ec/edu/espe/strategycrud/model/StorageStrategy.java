/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.espe.strategycrud.model;

/**
 *
 * @author Mateo Cevallos
 */
public interface StorageStrategy {

    boolean addEvent(Event event);

    boolean updateEvent(Event event);

    boolean deleteEvent(String id);

    Event readEvent(String id);
}
