/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

import java.util.PriorityQueue;

/**
 *
 * @author Mateo Cevallos
 */
public class Scheduler {
    private PriorityQueue<Request> pendingRequests;

    public Scheduler(PriorityQueue<Request> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }
    
    

    public void addRequest(Request request) {
        System.out.println("Request added...");
    }

    public Request getNextRequest() {
        System.out.println("Getting next request...");
        return null;
    }

    public void rescheduleRequest() {
        System.out.println("Rescheduling...");
    }

    public PriorityQueue<Request> getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(PriorityQueue<Request> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }
    
    
}
