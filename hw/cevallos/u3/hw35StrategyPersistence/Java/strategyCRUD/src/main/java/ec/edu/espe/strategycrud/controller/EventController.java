/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.strategycrud.controller;

import ec.edu.espe.strategycrud.model.Event;
import ec.edu.espe.strategycrud.model.StorageStrategy;

/**
 *
 * @author Mateo Cevallos
 */
public class EventController {

    private StorageStrategy storageStrategy;

    public EventController(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public void setStorageStrategy(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public boolean addEvent(Event event) {
        return storageStrategy.addEvent(event);
    }

    public boolean updateEvent(Event event) {
        return storageStrategy.updateEvent(event);
    }

    public boolean deleteEvent(String id) {
        return storageStrategy.deleteEvent(id);
    }

    public Event readEvent(String id) {
        return storageStrategy.readEvent(id);
    }
}
