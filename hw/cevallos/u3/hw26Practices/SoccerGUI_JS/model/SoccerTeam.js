// model/SoccerTeam.js
class SoccerTeam {
    constructor(id, teamName, coachName, neighborhoodCity, numberOfPlayers) {
        this.id = id;
        this.teamName = teamName;
        this.coachName = coachName;
        this.neighborhoodCity = neighborhoodCity;
        this.numberOfPlayers = numberOfPlayers;
        this.arbitration = this.calculateArbitration();
    }

    calculateArbitration() {
        if (this.numberOfPlayers > 0) {
            return 10 / this.numberOfPlayers;
        }
        return 0;
    }

    static fromDocument(doc) {
        const team = new SoccerTeam(
            doc.id,
            doc.teamName,
            doc.coachName,
            doc.neighborhoodCity,
            doc.numberOfPlayers
        );
        return team;
    }

    toDocument() {
        return {
            id: this.id,
            teamName: this.teamName,
            coachName: this.coachName,
            neighborhoodCity: this.neighborhoodCity,
            numberOfPlayers: this.numberOfPlayers,
            arbitration: this.arbitration
        };
    }

    toString() {
        return `ID: ${this.id} | ${this.teamName} | ${this.coachName} | ${this.neighborhoodCity} | ${this.numberOfPlayers} jugadores | $${this.arbitration.toFixed(2)} c/u`;
    }

    displayDetails() {
        return `
╔════════════════════════════════════════╗
║           EQUIPO DE FÚTBOL            ║
╠════════════════════════════════════════╣
║ ID: ${this.id.toString().padEnd(34)} ║
║ Nombre: ${this.teamName.padEnd(32)} ║
║ Entrenador: ${this.coachName.padEnd(29)} ║
║ Ciudad/Barrio: ${this.neighborhoodCity.padEnd(25)} ║
║ N° Jugadores: ${this.numberOfPlayers.toString().padEnd(26)} ║
║ Arbitraje por jugador: $${this.arbitration.toFixed(2).padEnd(19)} ║
║ Costo total arbitraje: $10.00${"".padEnd(18)} ║
╚════════════════════════════════════════╝`;
    }
}

export default SoccerTeam;