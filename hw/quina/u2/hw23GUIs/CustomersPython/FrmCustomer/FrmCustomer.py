from PyQt5.QtWidgets import (
    QApplication, QWidget, QLabel, QLineEdit,
    QComboBox, QPushButton, QFrame
)
from PyQt5.QtGui import QFont
from PyQt5.QtCore import Qt


class FrmCustomer(QWidget):
    def __init__(self):
        super().__init__()
        self.init_ui()

    def init_ui(self):

        self.setWindowTitle("FINVORY - CLIENTE")
        self.setFixedSize(520, 650)
        self.setStyleSheet("background-color: #D9D9D9;") 

        self.header = QFrame(self)
        self.header.setGeometry(0, 0, 520, 120)
        self.header.setStyleSheet("background-color: #000533;")

        self.lbl_brand = QLabel("FINVORY", self.header)
        self.lbl_brand.setStyleSheet("color: white;")
        self.lbl_brand.setFont(QFont("Calibri", 14, QFont.StyleItalic))
        self.lbl_brand.move(15, 10)

        self.lbl_title = QLabel("CLIENTE", self.header)
        self.lbl_title.setFont(QFont("Calibri", 28, QFont.Bold))
        self.lbl_title.setStyleSheet("color: white;")
        self.lbl_title.adjustSize()
        self.lbl_title.move(190, 50)

        self.body = QFrame(self)
        self.body.setGeometry(0, 120, 520, 490)
        self.body.setStyleSheet("background-color: #EDE7EF;")

        y = 40 
        x_label = 50
        x_field = 50
        field_width = 420
        field_height = 28
        spacing = 70

        self.lbl_name = QLabel("Nombre:", self.body)
        self.lbl_name.move(x_label, y)
        self.txt_name = QLineEdit(self.body)
        self.txt_name.setGeometry(x_field, y + 20, field_width, field_height)

        y += spacing
        self.lbl_ci = QLabel("RUC/CI:", self.body)
        self.lbl_ci.move(x_label, y)
        self.txt_ci = QLineEdit(self.body)
        self.txt_ci.setGeometry(x_field, y + 20, field_width, field_height)

        y += spacing
        self.lbl_phone = QLabel("Celular:", self.body)
        self.lbl_phone.move(x_label, y)
        self.txt_phone = QLineEdit(self.body)
        self.txt_phone.setGeometry(x_field, y + 20, field_width, field_height)

        y += spacing
        self.lbl_email = QLabel("Email:", self.body)
        self.lbl_email.move(x_label, y)
        self.txt_email = QLineEdit(self.body)
        self.txt_email.setGeometry(x_field, y + 20, field_width, field_height)

        y += spacing
        self.lbl_type = QLabel("Tipo:", self.body)
        self.lbl_type.move(x_label, y)
        self.cmb_type = QComboBox(self.body)
        self.cmb_type.setGeometry(x_field, y + 20, field_width, field_height)
        self.cmb_type.addItems(["STANDARD", "PREMIUM", "VIP"])

        self.btn_register = QPushButton("Registrar", self.body)
        self.btn_register.setGeometry(190, 430, 140, 40)
        self.btn_register.setFont(QFont("Calibri", 12, QFont.Bold))
        self.btn_register.setStyleSheet("""
            QPushButton {
                background-color: #000533;
                color: white;
                border-radius: 6px;
            }
            QPushButton:hover {
                background-color: #001050;
            }
        """)
        
if __name__ == "__main__":
    import sys
    app = QApplication(sys.argv)
    win = FrmCustomer()
    win.show()
    sys.exit(app.exec_())
