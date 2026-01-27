package ec.edu.espe.ws33.model;

public class LinuxButton extends Button {
    public void paint() {
        System.out.println("I'm a LinuxButton: " + caption);
    }
}