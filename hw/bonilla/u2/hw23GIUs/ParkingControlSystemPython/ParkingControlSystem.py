from PyQt6.QtWidgets import QApplication, QWidget, QLabel, QPushButton, QVBoxLayout, QHBoxLayout, QFrame
from PyQt6.QtGui import QFont
from PyQt6.QtCore import Qt
import sys

class ParkingControlSystem(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Parking Control System")
        self.setFixedSize(480, 700)
        self.setup_ui()

    def setup_ui(self):
        title_panel = QFrame()
        title_panel.setStyleSheet("background-color: #999999; border: 2px solid #666666;")
        title_layout = QVBoxLayout()
        title_label = QLabel("PARKING CONTROL SYSTEM")
        title_label.setFont(QFont("Microsoft YaHei UI", 20, QFont.Weight.Bold))
        title_label.setAlignment(Qt.AlignmentFlag.AlignCenter)
        title_layout.addWidget(title_label)
        title_panel.setLayout(title_layout)

        button_panel = QFrame()
        button_panel.setStyleSheet("background-color: #CCCCCC;")
        buttons_layout = QVBoxLayout()

        def make_button(text):
            btn = QPushButton(text)
            btn.setFont(QFont("Yu Gothic UI Semibold", 12))
            btn.setStyleSheet("background-color: #999999; padding: 6px;")
            return btn

        buttons = [
            "View registered vehicles",
            "Register resident + vehicle",
            "Register visitor",
            "Register entry / exit",
            "Parking space status (occupation)",
            "Assign / manage parking spaces",
            "Verify authorization",
            "Search vehicle by license plate",
            "Update resident vehicles",
            "Manage rentals",
            "Generate reports"
        ]

        for b in buttons:
            buttons_layout.addWidget(make_button(b))

        button_panel.setLayout(buttons_layout)

        bottom_panel = QFrame()
        bottom_panel.setStyleSheet("background-color: #CCCCCC;")
        bottom_layout = QVBoxLayout()
        exit_button = QPushButton("Exit the program")
        exit_button.setFont(QFont("Yu Gothic UI Semibold", 12))
        exit_button.setStyleSheet("background-color: #999999; padding: 6px;")
        bottom_layout.addWidget(exit_button, alignment=Qt.AlignmentFlag.AlignCenter)
        bottom_panel.setLayout(bottom_layout)

        main_layout = QVBoxLayout()
        main_layout.addWidget(title_panel)
        main_layout.addWidget(button_panel)
        main_layout.addWidget(bottom_panel)
        self.setLayout(main_layout)


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = ParkingControlSystem()
    window.show()
    sys.exit(app.exec())
