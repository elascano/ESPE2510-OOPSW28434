from PyQt6.QtWidgets import (
    QApplication, QWidget, QLabel, QLineEdit, QComboBox, QRadioButton,
    QListWidget, QTextEdit, QPushButton, QVBoxLayout, QHBoxLayout,
    QGroupBox, QDateEdit
)
from PyQt6.QtCore import Qt, QDate
from PyQt6.QtGui import QFont
import sys


class FrmContacts(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Contacts")

        self.setFixedSize(950, 700)
        self.setStyleSheet("QWidget { color: black; font-size: 14px; }")

        self.init_ui()

    def init_ui(self):
        title = QLabel("CONTACTS")
        title.setFont(QFont("Lucida Console", 28, QFont.Weight.Bold))
        title.setAlignment(Qt.AlignmentFlag.AlignCenter)
        title.setStyleSheet(
            "background-color: rgb(255,204,204); padding: 18px; border: 2px solid #777; color: black;"
        )

        form_box = QGroupBox("Contact Information")
        form_box.setStyleSheet("""
            QGroupBox {
                background-color: rgb(204,204,255);
                color: black;
                font-weight: bold;
                border: 2px solid #555;
                margin-top: 10px;
            }
            QGroupBox::title {
                subcontrol-origin: margin;
                subcontrol-position: top center;
                padding: 5px;
                color: black;
            }
        """)

        field_style = """
            QLineEdit, QDateEdit, QTextEdit, QComboBox, QListWidget {
                background-color: white;
                color: black;
                border: 1px solid #555;
                border-radius: 5px;
                padding: 4px;
                font-size: 14px;
            }
        """
        self.setStyleSheet(self.styleSheet() + field_style)

        self.lbl_id = QLabel("ID:")
        self.lbl_id_value = QLabel("1")  
        self.lbl_id_value.setStyleSheet("background-color: white; border: 1px solid #555; padding: 4px;")

        self.txt_first = QLineEdit()

        self.txt_last = QLineEdit()

        self.birth_date = QDateEdit()
        self.birth_date.setCalendarPopup(True)
        self.birth_date.setDate(QDate.currentDate())

        self.txt_age = QLineEdit()

        self.cmb_type = QComboBox()
        self.cmb_type.addItems(["Family", "Friend", "Job", "Unknown"])

        self.rad_male = QRadioButton("Male")
        self.rad_female = QRadioButton("Female")

        self.lst_hobbies = QListWidget()
        self.lst_hobbies.addItems(
            ["Play Soccer", "Dijing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        )
        self.lst_hobbies.setFixedHeight(120)
        self.lst_hobbies.setSelectionMode(QListWidget.SelectionMode.MultiSelection)

        self.txt_comments = QTextEdit()

        for widget in [
            self.txt_first, self.txt_last, self.birth_date,
            self.txt_age, self.cmb_type
        ]:
            widget.setFixedHeight(35)
            widget.setMinimumWidth(350)

        left = QVBoxLayout()

        id_layout = QHBoxLayout()
        id_layout.addWidget(self.lbl_id)
        id_layout.addWidget(self.lbl_id_value)

        left.addLayout(id_layout)

        for text, widget in [
            ("First Name:", self.txt_first),
            ("Last Name:", self.txt_last),
            ("Birth Date:", self.birth_date),
            ("Age:", self.txt_age)
        ]:
            lbl = QLabel(text)
            lbl.setStyleSheet("color: black; font-weight: bold;")
            left.addWidget(lbl)
            left.addWidget(widget)

        right = QVBoxLayout()

        for text, widget in [
            ("Type:", self.cmb_type),
        ]:
            lbl = QLabel(text)
            lbl.setStyleSheet("color: black; font-weight: bold;")
            right.addWidget(lbl)
            right.addWidget(widget)

        lbl_sex = QLabel("Sex:")
        lbl_sex.setStyleSheet("color: black; font-weight: bold;")
        right.addWidget(lbl_sex)
        right.addWidget(self.rad_male)
        right.addWidget(self.rad_female)

        lbl_hobbies = QLabel("Hobbies:")
        lbl_hobbies.setStyleSheet("color: black; font-weight: bold;")
        right.addWidget(lbl_hobbies)
        right.addWidget(self.lst_hobbies)

        top_form = QHBoxLayout()
        top_form.addLayout(left)
        top_form.addSpacing(40)
        top_form.addLayout(right)

        form_layout = QVBoxLayout()
        form_layout.addLayout(top_form)

        lbl_comments = QLabel("Comments:")
        lbl_comments.setStyleSheet("color: black; font-weight: bold;")
        form_layout.addWidget(lbl_comments)
        form_layout.addWidget(self.txt_comments)

        form_box.setLayout(form_layout)

        save_box = QGroupBox()
        save_box.setStyleSheet("QGroupBox { background-color: rgb(204,255,255); padding: 15px; }")

        btn_save = QPushButton("SAVE")
        btn_save.setFont(QFont("Lucida Console", 18))
        btn_save.setFixedSize(150, 45)

        save_layout = QHBoxLayout()
        save_layout.addStretch()
        save_layout.addWidget(btn_save)
        save_layout.addStretch()
        save_box.setLayout(save_layout)

        main_layout = QVBoxLayout()
        main_layout.addWidget(title)
        main_layout.addWidget(form_box)
        main_layout.addWidget(save_box)
        main_layout.addStretch()

        self.setLayout(main_layout)


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = FrmContacts()
    window.show()
    sys.exit(app.exec())
