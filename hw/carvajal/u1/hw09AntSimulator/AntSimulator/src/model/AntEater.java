package model;

public class AntEater {
    private Cell position;
    private int antsEaten;
    private boolean sleeping;
    private int ticksSinceLastMeal;

    public AntEater(Cell start) {
        this.position = start;
        this.sleeping = false;
        this.antsEaten = 0;
    }

    public void run() {
        System.out.println("[AntEater] En el tick actual, el oso hormiguero en (" 
            + position + ") actua...");

        if (sleeping) {
            System.out.println("   - El oso hormiguero sigue durmiendo...");
            ticksSinceLastMeal++;
            if (ticksSinceLastMeal > 5) {
                sleeping = false;
                System.out.println("   - Se despierta con hambre.");
            }
        } else {
            System.out.println("   - El oso hormiguero busca hormigas.");
            antsEaten += 5;
            System.out.println("   - Se come algunas hormigas (total comidas: " + antsEaten + ").");

            if (antsEaten >= 50) {
                sleeping = true;
                ticksSinceLastMeal = 0;
                System.out.println("   - Ya comio demasiado, se va a dormir.");
            }
        }

        System.out.println("   - Termina su turno.\n");
    }
}