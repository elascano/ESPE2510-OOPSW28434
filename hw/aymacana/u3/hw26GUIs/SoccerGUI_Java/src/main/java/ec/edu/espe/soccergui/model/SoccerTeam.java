package ec.edu.espe.soccergui.model;

public class SoccerTeam {

    private int id;  // Ahora es int para autoincremental
    private String teamName;
    private String coachName;
    private String neighborhoodCity;
    private int numberOfPlayers;
    private double arbitration; // Calculado: 10 / numberOfPlayers

    // Constructores
    public SoccerTeam() {
        // Constructor vacío para MongoDB
    }

    public SoccerTeam(int id, String teamName, String coachName,
            String neighborhoodCity, int numberOfPlayers) {
        this.id = id;
        this.teamName = teamName;
        this.coachName = coachName;
        this.neighborhoodCity = neighborhoodCity;
        this.numberOfPlayers = numberOfPlayers;
        calculateArbitration(); // Calcular automáticamente
    }

    public SoccerTeam(String teamName, String coachName,
            String neighborhoodCity, int numberOfPlayers) {
        // ID será asignado por el repositorio
        this.teamName = teamName;
        this.coachName = coachName;
        this.neighborhoodCity = neighborhoodCity;
        this.numberOfPlayers = numberOfPlayers;
        calculateArbitration(); // Calcular automáticamente
    }

    // Método para calcular arbitraje
    private void calculateArbitration() {
        if (this.numberOfPlayers > 0) {
            // 10 dólares base dividido entre número de jugadores
            this.arbitration = 10.0 / this.numberOfPlayers;
        } else {
            this.arbitration = 0.0;
        }
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        calculateArbitration(); // Recalcular si cambia ID (por si acaso)
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getNeighborhoodCity() {
        return neighborhoodCity;
    }

    public void setNeighborhoodCity(String neighborhoodCity) {
        this.neighborhoodCity = neighborhoodCity;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        calculateArbitration(); // ¡IMPORTANTE! Recalcular cuando cambia
    }

    public double getArbitration() {
        return arbitration;
    }
    // No hay setter para arbitration porque se calcula automáticamente

    @Override
    public String toString() {
        return String.format(
                "SoccerTeam{id=%d, teamName='%s', coach='%s', city='%s', players=%d, arbitration=$%.2f}",
                id, teamName, coachName, neighborhoodCity, numberOfPlayers, arbitration
        );
    }

    public String toDisplayString() {
        return String.format(
                "ID: %d\n"
                + "Equipo: %s\n"
                + "Entrenador: %s\n"
                + "Ciudad/Barrio: %s\n"
                + "Número de Jugadores: %d\n"
                + "Arbitraje por jugador: $%.2f",
                id, teamName, coachName, neighborhoodCity, numberOfPlayers, arbitration
        );
    }

    // Formateado para tabla
    public Object[] toTableRow() {
        return new Object[]{
            id,
            teamName,
            coachName,
            neighborhoodCity,
            numberOfPlayers,
            String.format("$%.2f", arbitration)
        };
    }
}
