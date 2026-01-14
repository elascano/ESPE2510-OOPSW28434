# Exam Solution/main.py
import sys
import os
from PyQt6.QtWidgets import QApplication, QDialog, QMessageBox
from PyQt6 import uic

from Controller.Soccer_team_controller import TeamController
from Utils.validations import ValidationError


class TeamManagementDialog(QDialog):
    def __init__(self):
        super().__init__()
        
        # Configurar ventana
        self.setWindowTitle("Team Management System")
        
        # Controller
        self.controller = TeamController()
        
        # Cargar la interfaz
        self.load_ui()
        
        # Conectar botones
        self.connect_buttons()
    
    def get_ui_path(self):
        """Obtener la ruta correcta del archivo .ui (funciona con PyInstaller)"""
        # Si estamos en un ejecutable de PyInstaller
        if getattr(sys, 'frozen', False):
            # PyInstaller crea una carpeta temporal en _MEIPASS
            base_path = sys._MEIPASS
            # Buscar en varias ubicaciones posibles
            possible_paths = [
                os.path.join(base_path, "GUIS.ui"),  # En la raíz del paquete
                os.path.join(base_path, "View", "GUIS.ui"),  # En carpeta View
            ]
        else:
            # En modo desarrollo
            base_path = os.path.dirname(os.path.abspath(__file__))
            possible_paths = [
                os.path.join(base_path, "GUIS.ui"),  # En la raíz
                os.path.join(base_path, "View", "GUIS.ui"),  # En carpeta View
            ]
        
        # Probar cada ruta
        for path in possible_paths:
            if os.path.exists(path):
                print(f"✅ UI encontrada en: {path}")
                return path
        
        # Si no se encuentra en ninguna ruta
        print("❌ No se encontró GUIS.ui en ninguna ubicación")
        return None
    
    def load_ui(self):
        """Cargar la interfaz de usuario"""
        try:
            # Obtener la ruta correcta del archivo .ui
            ui_path = self.get_ui_path()
            
            if ui_path:
                # Cargar el archivo .ui
                uic.loadUi(ui_path, self)
                print("✅ UI cargada exitosamente")
                self.resize(700, 400)
            else:
                # Crear una UI básica si no se encuentra el archivo
                self.create_basic_ui()
                QMessageBox.warning(self, "Advertencia", 
                                   "No se encontró el archivo GUIS.ui. Usando interfaz básica.")
                
        except Exception as e:
            print(f"❌ Error cargando UI: {e}")
            self.create_basic_ui()
    
    def create_basic_ui(self):
        """Crear una interfaz básica si no se puede cargar el .ui"""
        from PyQt6.QtWidgets import (QTabWidget, QWidget, QVBoxLayout, 
                                    QLabel, QLineEdit, QPushButton, 
                                    QTextEdit, QGridLayout)
        
        # Crear tabs
        tabs = QTabWidget()
        
        # ========== TAB CREATE ==========
        create_widget = QWidget()
        create_layout = QVBoxLayout()
        
        # Team Name
        create_layout.addWidget(QLabel("Team Name:"))
        self.txtName = QLineEdit()
        create_layout.addWidget(self.txtName)
        
        # Coach
        create_layout.addWidget(QLabel("Coach:"))
        self.txtCoach = QLineEdit()
        create_layout.addWidget(self.txtCoach)
        
        # City
        create_layout.addWidget(QLabel("City:"))
        self.txtLocation = QLineEdit()
        create_layout.addWidget(self.txtLocation)
        
        # Players
        create_layout.addWidget(QLabel("Players:"))
        self.txtNumber = QLineEdit()
        create_layout.addWidget(self.txtNumber)
        
        # Save button
        self.btnSave = QPushButton("Save")
        create_layout.addWidget(self.btnSave)
        create_layout.addStretch()
        
        create_widget.setLayout(create_layout)
        tabs.addTab(create_widget, "Create")
        
        # ========== TAB READ ==========
        read_widget = QWidget()
        read_layout = QVBoxLayout()
        
        # Team ID
        read_layout.addWidget(QLabel("Team ID:"))
        self.txtSearchById = QLineEdit()
        read_layout.addWidget(self.txtSearchById)
        
        # Search button
        self.btnSearch = QPushButton("Search")
        read_layout.addWidget(self.btnSearch)
        
        # Results
        self.txaRead = QTextEdit()
        self.txaRead.setReadOnly(True)
        read_layout.addWidget(self.txaRead)
        
        read_widget.setLayout(read_layout)
        tabs.addTab(read_widget, "Read")
        
        # ========== TAB UPDATE ==========
        update_widget = QWidget()
        update_layout = QVBoxLayout()
        
        # Team ID
        update_layout.addWidget(QLabel("Team ID:"))
        self.txtSearchByIdUp = QLineEdit()
        update_layout.addWidget(self.txtSearchByIdUp)
        
        # Search button
        self.btnSearchByIdUp = QPushButton("Search")
        update_layout.addWidget(self.btnSearchByIdUp)
        
        # Team Name
        update_layout.addWidget(QLabel("Team Name:"))
        self.txtNameUp = QLineEdit()
        update_layout.addWidget(self.txtNameUp)
        
        # Coach
        update_layout.addWidget(QLabel("Coach:"))
        self.txtCoachNameUp = QLineEdit()
        update_layout.addWidget(self.txtCoachNameUp)
        
        # City
        update_layout.addWidget(QLabel("City:"))
        self.txtLocationUp = QLineEdit()
        update_layout.addWidget(self.txtLocationUp)
        
        # Players
        update_layout.addWidget(QLabel("Players:"))
        self.txtNumberUp = QLineEdit()
        update_layout.addWidget(self.txtNumberUp)
        
        # Update button
        self.btnUpdate = QPushButton("Update")
        update_layout.addWidget(self.btnUpdate)
        update_layout.addStretch()
        
        update_widget.setLayout(update_layout)
        tabs.addTab(update_widget, "Update")
        
        # ========== TAB DELETE ==========
        delete_widget = QWidget()
        delete_layout = QVBoxLayout()
        
        # Team ID
        delete_layout.addWidget(QLabel("Team ID:"))
        self.txtSearchByIdDel = QLineEdit()
        delete_layout.addWidget(self.txtSearchByIdDel)
        
        # Search button
        self.btnSearchDel = QPushButton("Search")
        delete_layout.addWidget(self.btnSearchDel)
        
        # Results
        self.txaDelete = QTextEdit()
        self.txaDelete.setReadOnly(True)
        delete_layout.addWidget(self.txaDelete)
        
        # Delete button
        self.btnDelete = QPushButton("Delete")
        delete_layout.addWidget(self.btnDelete)
        delete_layout.addStretch()
        
        delete_widget.setLayout(delete_layout)
        tabs.addTab(delete_widget, "Delete")
        
        # Layout principal
        main_layout = QVBoxLayout()
        main_layout.addWidget(tabs)
        self.setLayout(main_layout)
        self.resize(700, 500)
    
    def connect_buttons(self):
        """Conectar todos los botones"""
        self.btnSave.clicked.connect(self.create_team)
        self.btnSearch.clicked.connect(self.read_team)
        self.btnSearchByIdUp.clicked.connect(self.search_for_update)
        self.btnUpdate.clicked.connect(self.update_team)
        self.btnSearchDel.clicked.connect(self.search_for_delete)
        self.btnDelete.clicked.connect(self.delete_team)
    
    # ========== MÉTODOS DE NEGOCIO ==========
    
    def create_team(self):
        """CREATE tab"""
        try:
            players = int(self.txtNumber.text())
            
            team_id, referee_fee = self.controller.create_team(
                self.txtName.text(),
                self.txtCoach.text(),
                self.txtLocation.text(),
                players
            )
            
            QMessageBox.information(
                self,
                "Success",
                f"Team created!\nTeam ID: {team_id}\nReferee fee: ${referee_fee:.2f}"
            )
            
            # Limpiar campos
            self.txtName.clear()
            self.txtCoach.clear()
            self.txtLocation.clear()
            self.txtNumber.clear()
            
        except ValueError:
            QMessageBox.warning(self, "Error", "Players must be a number")
        except ValidationError as e:
            QMessageBox.warning(self, "Validation error", str(e))
        except Exception as e:
            QMessageBox.critical(self, "Error", f"Error: {str(e)}")
    
    def read_team(self):
        """READ tab"""
        team_id = self.txtSearchById.text()
        
        if not team_id:
            QMessageBox.warning(self, "Error", "Please enter a Team ID")
            return
        
        try:
            team_id = int(team_id)
        except:
            QMessageBox.warning(self, "Error", "Team ID must be a number")
            return
        
        team = self.controller.read_team(team_id)
        
        if team:
            info = f"""Team ID: {team.get('team_id', 'N/A')}
Name: {team.get('name', 'N/A')}
Coach: {team.get('coach', 'N/A')}
City: {team.get('city', 'N/A')}
Players: {team.get('players', 'N/A')}
Referee fee per player: ${team.get('referee_fee', 0):.2f}"""
            self.txaRead.setPlainText(info)
        else:
            self.txaRead.clear()
            QMessageBox.information(self, "Not found", "Team not found")
    
    def search_for_update(self):
        """Buscar equipo para actualizar"""
        team_id = self.txtSearchByIdUp.text()
        
        if not team_id:
            QMessageBox.warning(self, "Error", "Please enter a Team ID")
            return
        
        try:
            team_id = int(team_id)
        except:
            QMessageBox.warning(self, "Error", "Team ID must be a number")
            return
        
        team = self.controller.read_team(team_id)
        
        if team:
            self.txtNameUp.setText(team.get('name', ''))
            self.txtCoachNameUp.setText(team.get('coach', ''))
            self.txtLocationUp.setText(team.get('city', ''))
            self.txtNumberUp.setText(str(team.get('players', '')))
            QMessageBox.information(self, "Found", "Team found. You can now update the fields.")
        else:
            QMessageBox.warning(self, "Not found", "Team not found")
            self.txtNameUp.clear()
            self.txtCoachNameUp.clear()
            self.txtLocationUp.clear()
            self.txtNumberUp.clear()
    
    def update_team(self):
        """UPDATE tab"""
        team_id = self.txtSearchByIdUp.text()
        
        if not team_id:
            QMessageBox.warning(self, "Error", "Please enter a Team ID")
            return
        
        try:
            team_id = int(team_id)
        except:
            QMessageBox.warning(self, "Error", "Team ID must be a number")
            return
        
        try:
            players = int(self.txtNumberUp.text())
            
            data = {
                "name": self.txtNameUp.text(),
                "coach": self.txtCoachNameUp.text(),
                "city": self.txtLocationUp.text(),
                "players": players
            }
            
            success = self.controller.update_team(team_id, data)
            
            if success:
                QMessageBox.information(self, "Updated", "Team updated successfully")
                self.txtSearchByIdUp.clear()
                self.txtNameUp.clear()
                self.txtCoachNameUp.clear()
                self.txtLocationUp.clear()
                self.txtNumberUp.clear()
            else:
                QMessageBox.warning(self, "Error", "Failed to update team")
                
        except ValueError:
            QMessageBox.warning(self, "Error", "Players must be a number")
        except ValidationError as e:
            QMessageBox.warning(self, "Validation error", str(e))
        except Exception as e:
            QMessageBox.critical(self, "Error", f"Error: {str(e)}")
    
    def search_for_delete(self):
        """Buscar equipo para eliminar"""
        team_id = self.txtSearchByIdDel.text()
        
        if not team_id:
            QMessageBox.warning(self, "Error", "Please enter a Team ID")
            return
        
        try:
            team_id = int(team_id)
        except:
            QMessageBox.warning(self, "Error", "Team ID must be a number")
            return
        
        team = self.controller.read_team(team_id)
        
        if team:
            info = f"""Team found:
ID: {team.get('team_id', 'N/A')}
Name: {team.get('name', 'N/A')}
Coach: {team.get('coach', 'N/A')}
City: {team.get('city', 'N/A')}
Players: {team.get('players', 'N/A')}
            
Click Delete button to remove this team."""
            self.txaDelete.setPlainText(info)
        else:
            self.txaDelete.clear()
            QMessageBox.warning(self, "Not found", "Team not found")
    
    def delete_team(self):
        """DELETE tab"""
        team_id = self.txtSearchByIdDel.text()
        
        if not team_id:
            QMessageBox.warning(self, "Error", "Please enter a Team ID")
            return
        
        try:
            team_id = int(team_id)
        except:
            QMessageBox.warning(self, "Error", "Team ID must be a number")
            return
        
        reply = QMessageBox.question(
            self,
            "Confirm Delete",
            f"Are you sure you want to delete team with ID: {team_id}?",
            QMessageBox.StandardButton.Yes | QMessageBox.StandardButton.No
        )
        
        if reply == QMessageBox.StandardButton.Yes:
            success = self.controller.delete_team(team_id)
            
            if success:
                QMessageBox.information(self, "Deleted", "Team deleted successfully")
                self.txtSearchByIdDel.clear()
                self.txaDelete.clear()
            else:
                QMessageBox.warning(self, "Error", "Failed to delete team")


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = TeamManagementDialog()
    window.show()
    sys.exit(app.exec())