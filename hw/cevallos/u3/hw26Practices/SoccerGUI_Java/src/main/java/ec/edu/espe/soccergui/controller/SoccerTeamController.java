package ec.edu.espe.soccergui.controller;

import ec.edu.espe.soccergui.controller.SimpleCrud;
import ec.edu.espe.soccergui.model.SoccerTeam;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class SoccerTeamController {
    
    private SimpleCrud crud;
    
    public SoccerTeamController() {
        this.crud = new SimpleCrud("SoccerTeam");
    }
    
    // ========== CREATE ==========
    public int addTeam(String teamName, String coachName, 
                      String neighborhoodCity, int numberOfPlayers) {
        
        // Crear documento para MongoDB
        Document teamDoc = new Document();
        teamDoc.put("teamName", teamName);
        teamDoc.put("coachName", coachName);
        teamDoc.put("neighborhoodCity", neighborhoodCity);
        teamDoc.put("numberOfPlayers", numberOfPlayers);
        
        // Calcular arbitraje
        double arbitration = 10.0 / numberOfPlayers;
        teamDoc.put("arbitration", arbitration);
        
        // Guardar en MongoDB
        return crud.create(teamDoc);
    }
    
    // ========== READ ==========
    public SoccerTeam getTeam(int id) {
        Document doc = crud.read(id);
        if (doc != null) {
            return documentToTeam(doc);
        }
        return null;
    }
    
    public List<SoccerTeam> getAllTeams() {
        List<SoccerTeam> teams = new ArrayList<>();
        List<Document> docs = crud.readAllSortedById();
        
        for (Document doc : docs) {
            teams.add(documentToTeam(doc));
        }
        
        return teams;
    }
    
    // Buscar por nombre
    public List<SoccerTeam> searchTeamsByName(String name) {
        List<SoccerTeam> results = new ArrayList<>();
        List<Document> docs = crud.findByField("teamName", name);
        
        for (Document doc : docs) {
            results.add(documentToTeam(doc));
        }
        
        return results;
    }
    
    // ========== UPDATE ==========
    public boolean updateTeam(int id, String teamName, String coachName,
                             String neighborhoodCity, Integer numberOfPlayers) {
        
        Document currentDoc = crud.read(id);
        if (currentDoc == null) {
            return false;
        }
        
        // Crear nuevo documento con los datos actualizados
        Document newDoc = new Document();
        
        // Mantener valores existentes o usar nuevos
        newDoc.put("teamName", 
            (teamName != null && !teamName.isEmpty()) ? teamName : currentDoc.getString("teamName"));
        
        newDoc.put("coachName", 
            (coachName != null && !coachName.isEmpty()) ? coachName : currentDoc.getString("coachName"));
        
        newDoc.put("neighborhoodCity", 
            (neighborhoodCity != null && !neighborhoodCity.isEmpty()) ? 
            neighborhoodCity : currentDoc.getString("neighborhoodCity"));
        
        int finalPlayers = (numberOfPlayers != null && numberOfPlayers > 0) ? 
            numberOfPlayers : currentDoc.getInteger("numberOfPlayers");
        newDoc.put("numberOfPlayers", finalPlayers);
        
        // Recalcular arbitraje
        double arbitration = 10.0 / finalPlayers;
        newDoc.put("arbitration", arbitration);
        
        newDoc.put("id", id); // Mantener el mismo ID
        
        return crud.update(id, newDoc);
    }
    
    // ========== DELETE ==========
    public boolean deleteTeam(int id) {
        return crud.delete(id);
    }
    
    // ========== MÉTODOS DE CONVERSIÓN ==========
    private SoccerTeam documentToTeam(Document doc) {
        SoccerTeam team = new SoccerTeam();
        team.setId(doc.getInteger("id"));
        team.setTeamName(doc.getString("teamName"));
        team.setCoachName(doc.getString("coachName"));
        team.setNeighborhoodCity(doc.getString("neighborhoodCity"));
        team.setNumberOfPlayers(doc.getInteger("numberOfPlayers"));
        // arbitration ya está calculado en el documento
        return team;
    }
    
    // ========== MÉTODOS ADICIONALES ==========
    public boolean teamExists(int id) {
        return crud.exists(id);
    }
    
    public int getNextAvailableId() {
        // Esta es una forma simple de estimar el próximo ID
        // En producción usarías el método getNextId() del CRUD
        List<SoccerTeam> teams = getAllTeams();
        if (teams.isEmpty()) return 1;
        
        int maxId = 0;
        for (SoccerTeam team : teams) {
            if (team.getId() > maxId) {
                maxId = team.getId();
            }
        }
        return maxId + 1;
    }
    
    public Object[][] getTeamsForTable() {
        List<SoccerTeam> teams = getAllTeams();
        Object[][] data = new Object[teams.size()][6];
        
        for (int i = 0; i < teams.size(); i++) {
            SoccerTeam team = teams.get(i);
            data[i][0] = team.getId();
            data[i][1] = team.getTeamName();
            data[i][2] = team.getCoachName();
            data[i][3] = team.getNeighborhoodCity();
            data[i][4] = team.getNumberOfPlayers();
            data[i][5] = String.format("$%.2f", team.getArbitration());
        }
        
        return data;
    }
    
    public String[] getTableColumnNames() {
        return new String[]{
            "ID", 
            "Nombre del Equipo", 
            "Entrenador", 
            "Ciudad/Barrio", 
            "N° Jugadores", 
            "Arbitraje c/u"
        };
    }
}