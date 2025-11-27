/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

import java.util.Date;

/**
 *
 * @author Mateo Cevallos
 */
public class ElevatorUsage {
    private String personId;
    private int entryFloor;
    private int exitFloor;
    private Date entryTime;
    private Date exitTime;
    private int elevatorId; 

    public ElevatorUsage(String personId, int entryFloor, int exitFloor, Date entryTime, Date exitTime, int elevatorId) {
        this.personId = personId;
        this.entryFloor = entryFloor;
        this.exitFloor = exitFloor;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.elevatorId = elevatorId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public int getEntryFloor() {
        return entryFloor;
    }

    public void setEntryFloor(int entryFloor) {
        this.entryFloor = entryFloor;
    }

    public int getExitFloor() {
        return exitFloor;
    }

    public void setExitFloor(int exitFloor) {
        this.exitFloor = exitFloor;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(int elevatorId) {
        this.elevatorId = elevatorId;
    }
    
    
}
