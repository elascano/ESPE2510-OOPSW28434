# Exam Solution/Controller/Soccer_team_controller.py
from Model.Soccer_team import Soccer_team
from Utils.CRUD_operations import Mongo_CRUD
from Utils.validations import TeamValidator, ValidationError

class TeamController:
    def __init__(self):
        self.crud = Mongo_CRUD()
    
    # 🧠 REGLA DE NEGOCIO - Calcular costo del árbitro
    def calculate_referee_fee(self, players):
        if players <= 0:
            raise ValueError("Número de jugadores inválido")
        # $10 dividido entre el número de jugadores
        return round(10 / players, 2)
    
    # CRUD
    def create_team(self, name, coach, city, players):
        # Validar datos
        TeamValidator.validate_team_data(name, coach, city, players)
        
        # Calcular costo del árbitro
        referee_fee = self.calculate_referee_fee(players)
        
        # Crear equipo con ID autoincremental y costo del árbitro
        team = Soccer_team(
            name=name,
            coach=coach,
            city=city,
            players=players,
            team_id=None,  # Se asignará automáticamente
            referee_fee=referee_fee
        )
        
        # Guardar en MongoDB
        team_id = self.crud.create(team)
        return team_id, referee_fee
    
    def read_team(self, team_id):
        return self.crud.read(team_id)
    
    def read_all_teams(self):
        return self.crud.read_all()
    
    def update_team(self, team_id, data):
        # Si se actualizan los jugadores, recalcular referee_fee
        if 'players' in data and data['players']:
            try:
                players = int(data['players'])
                data['referee_fee'] = self.calculate_referee_fee(players)
            except:
                pass
        
        return self.crud.update(team_id, data)
    
    def delete_team(self, team_id):
        return self.crud.delete(team_id)
    
    def get_last_team_id(self):
        return self.crud.get_last_team_id()