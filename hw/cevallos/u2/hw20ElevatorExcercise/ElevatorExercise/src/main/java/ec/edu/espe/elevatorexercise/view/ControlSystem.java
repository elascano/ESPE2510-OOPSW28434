/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.view;

import ec.edu.espe.elevatorexercise.model.Brake;
import ec.edu.espe.elevatorexercise.model.Button;
import ec.edu.espe.elevatorexercise.model.Cage;
import ec.edu.espe.elevatorexercise.model.ControlPanel;
import ec.edu.espe.elevatorexercise.model.Elevator;
import ec.edu.espe.elevatorexercise.model.ElevatorUsage;
import ec.edu.espe.elevatorexercise.model.Floor;
import ec.edu.espe.elevatorexercise.model.MotorSystem;
import ec.edu.espe.elevatorexercise.model.Movement;
import ec.edu.espe.elevatorexercise.model.Person;
import ec.edu.espe.elevatorexercise.model.Request;
import ec.edu.espe.elevatorexercise.model.Scheduler;
import ec.edu.espe.elevatorexercise.model.Sensor;
import ec.edu.espe.elevatorexercise.model.SensorData;
import ec.edu.espe.elevatorexercise.model.StatisticsCollector;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Mateo Cevallos
 */
public class ControlSystem {
     public static void main(String[] args) {

        System.out.println("=== CONTROL SYSTEM STARTED ===");

        // ================= BOTONES =================
        Button openButton = null;
        Button closeButton = null;
        Button alarmButton = null;
        Button stopButton = null;

        List<Button> buttons = new ArrayList<>();

        ControlPanel controlPanel = new ControlPanel(
                buttons,
                openButton,
                closeButton,
                alarmButton,
                stopButton
        );

        System.out.println("ControlPanel created");

        // ================= PERSONAS =================
        List<Person> persons = new ArrayList<>();

        Person person1 = new Person(
                70.5f,
                1,
                new Date(),
                0,
                5
        ) {};

        Person person2 = new Person(
                80.0f,
                2,
                new Date(),
                1,
                8
        ) {};

        persons.add(person1);
        persons.add(person2);

        System.out.println("Persons created: " + persons.size());

        // ================= CAGE =================
        Cage cage = new Cage(controlPanel, persons);
        System.out.println("Cage created");

        // ================= MOTOR SYSTEM =================
        MotorSystem motorSystem = new MotorSystem(
                4,
                6.5
        );

        System.out.println("MotorSystem created");

        // ================= SENSORS =================
        List<Sensor> sensors = new ArrayList<>();

        Sensor sensor1 = new Sensor(1, 0, 22.5);
        Sensor sensor2 = new Sensor(2, 1, 45.8);

        sensors.add(sensor1);
        sensors.add(sensor2);

        System.out.println("Sensors created: " + sensors.size());

        // ================= BRAKES =================
        List<Brake> brakes = new ArrayList<>();

        Brake brake1 = new Brake(0, true);
        Brake brake2 = new Brake(1, false);

        brakes.add(brake1);
        brakes.add(brake2);

        System.out.println("Brakes created: " + brakes.size());

        // ================= ELEVATOR =================
        Elevator elevator = new Elevator(
                0,           // position
                true,        // shaft
                1,           // direction
                101,         // id
                10,          // maxCapacity
                900.0,       // maxWeight
                150.0,       // currentWeight
                motorSystem,
                cage,
                sensors,
                brakes
        );

        System.out.println("Elevator created with ID: 101");

        // ================= FLOOR =================
        Floor floor = new Floor(3);
        System.out.println("Floor created: " + 3);

        // ================= SCHEDULER =================
        PriorityQueue<Request> requestQueue = new PriorityQueue<>();

        Scheduler scheduler = new Scheduler(requestQueue);
        System.out.println("Scheduler created");

        // ================= SENSOR DATA =================
        SensorData sensorData = new SensorData(1, 0, 33.7);
        System.out.println("SensorData created");

        // ================= ELEVATOR USAGE =================
        ElevatorUsage usage = new ElevatorUsage(
                "USR-001",
                1,
                6,
                new Date(),
                new Date(),
                101
        );

        System.out.println("ElevatorUsage record created");

        System.out.println("=== SYSTEM INITIALIZATION COMPLETE ===");
    }
}
