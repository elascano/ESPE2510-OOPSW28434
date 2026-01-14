// controller/SoccerTeamController.js
import SimpleCrud from './SimpleCrud.js';
import SoccerTeam from '../model/SoccerTeam.js';
import IdGenerator from '../utils/IdGenerator.js';

class SoccerTeamController {
    constructor() {
        this.crud = new SimpleCrud('SoccerTeam');
    }

    // CREATE
    async addTeam(teamName, coachName, neighborhoodCity, numberOfPlayers) {
        try {
            // Generar ID autoincremental
            const nextId = await IdGenerator.getNextId('SoccerTeam');
            
            // Crear equipo
            const team = new SoccerTeam(
                nextId,
                teamName,
                coachName,
                neighborhoodCity,
                numberOfPlayers
            );
            
            // Guardar en MongoDB
            const doc = team.toDocument();
            const newId = await this.crud.create(doc);
            
            return {
                success: true,
                message: `✅ Equipo agregado con ID: ${newId}`,
                data: team
            };
            
        } catch (error) {
            return {
                success: false,
                message: `❌ Error al agregar equipo: ${error.message}`,
                data: null
            };
        }
    }

    // READ
    async getTeam(id) {
        try {
            const doc = await this.crud.read(id);
            
            if (doc) {
                const team = SoccerTeam.fromDocument(doc);
                return {
                    success: true,
                    message: '✅ Equipo encontrado',
                    data: team
                };
            } else {
                return {
                    success: false,
                    message: `❌ No se encontró equipo con ID: ${id}`,
                    data: null
                };
            }
            
        } catch (error) {
            return {
                success: false,
                message: `❌ Error al buscar equipo: ${error.message}`,
                data: null
            };
        }
    }

    // READ ALL
    async getAllTeams() {
        try {
            const docs = await this.crud.readAll();
            const teams = docs.map(doc => SoccerTeam.fromDocument(doc));
            
            return {
                success: true,
                message: `✅ Encontrados ${teams.length} equipos`,
                data: teams
            };
            
        } catch (error) {
            return {
                success: false,
                message: `❌ Error al obtener equipos: ${error.message}`,
                data: []
            };
        }
    }

    // UPDATE
    async updateTeam(id, teamName, coachName, neighborhoodCity, numberOfPlayers) {
        try {
            // Verificar que el equipo existe
            const existingDoc = await this.crud.read(id);
            if (!existingDoc) {
                return {
                    success: false,
                    message: `❌ No se encontró equipo con ID: ${id}`,
                    data: null
                };
            }

            // Crear equipo actualizado
            const team = new SoccerTeam(
                id,
                teamName || existingDoc.teamName,
                coachName || existingDoc.coachName,
                neighborhoodCity || existingDoc.neighborhoodCity,
                numberOfPlayers || existingDoc.numberOfPlayers
            );

            // Actualizar en MongoDB
            const doc = team.toDocument();
            const success = await this.crud.update(id, doc);

            if (success) {
                return {
                    success: true,
                    message: `✅ Equipo actualizado con ID: ${id}`,
                    data: team
                };
            } else {
                return {
                    success: false,
                    message: `❌ Error al actualizar equipo`,
                    data: null
                };
            }
            
        } catch (error) {
            return {
                success: false,
                message: `❌ Error al actualizar equipo: ${error.message}`,
                data: null
            };
        }
    }

    // DELETE
    async deleteTeam(id) {
        try {
            const success = await this.crud.delete(id);
            
            if (success) {
                return {
                    success: true,
                    message: `✅ Equipo eliminado con ID: ${id}`,
                    data: null
                };
            } else {
                return {
                    success: false,
                    message: `❌ No se encontró equipo con ID: ${id}`,
                    data: null
                };
            }
            
        } catch (error) {
            return {
                success: false,
                message: `❌ Error al eliminar equipo: ${error.message}`,
                data: null
            };
        }
    }

    // STATISTICS
    async getStatistics() {
        try {
            const result = await this.getAllTeams();
            
            if (!result.success || result.data.length === 0) {
                return {
                    success: true,
                    message: '📊 No hay equipos registrados',
                    data: null
                };
            }

            const teams = result.data;
            const totalTeams = teams.length;
            const totalPlayers = teams.reduce((sum, team) => sum + team.numberOfPlayers, 0);
            const avgPlayers = totalPlayers / totalTeams;
            const totalArbitrationCost = totalTeams * 10; // $10 por equipo

            const stats = {
                totalTeams,
                totalPlayers,
                avgPlayers: avgPlayers.toFixed(2),
                totalArbitrationCost
            };

            const message = `
📊 ESTADÍSTICAS DEL SISTEMA

Total equipos: ${totalTeams}
Total jugadores: ${totalPlayers}
Promedio por equipo: ${avgPlayers.toFixed(2)} jugadores
Costo total de arbitraje: $${totalArbitrationCost.toFixed(2)}`;

            return {
                success: true,
                message: message,
                data: stats
            };
            
        } catch (error) {
            return {
                success: false,
                message: `❌ Error al obtener estadísticas: ${error.message}`,
                data: null
            };
        }
    }
}

export default SoccerTeamController;