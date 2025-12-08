import sys
from typing import List

from PySide6.QtWidgets import (
    QApplication,
    QListWidget,
    QLineEdit,
    QComboBox,
    QRadioButton,
    QTextEdit,
    QPushButton,
    QMessageBox,
)
from PySide6.QtUiTools import QUiLoader
from PySide6.QtCore import QFile

from model.contact import Contact
from model.contact_repository import save_contact


class ContactsWindow:
    def __init__(self):
        loader = QUiLoader()
        ui_file = QFile("ui/frm_contacts.ui")
        if not ui_file.open(QFile.ReadOnly):
            raise RuntimeError("No se pudo abrir ui/frm_contacts.ui")
        self.window = loader.load(ui_file)
        ui_file.close()

        if self.window is None:
            raise RuntimeError("No se pudo cargar el formulario")

        self.txtFirstName: QLineEdit = self.window.findChild(QLineEdit, "txtFirstName")
        self.txtLastName: QLineEdit = self.window.findChild(QLineEdit, "txtLastName")
        self.txtBirthDate: QLineEdit = self.window.findChild(QLineEdit, "txtBirthDate")
        self.cmbType: QComboBox = self.window.findChild(QComboBox, "cmbType")
        self.rbMale: QRadioButton = self.window.findChild(QRadioButton, "rbMale")
        self.rbFemale: QRadioButton = self.window.findChild(QRadioButton, "rbFemale")
        self.lstHobbies: QListWidget = self.window.findChild(QListWidget, "lstHobbies")
        self.txtComments: QTextEdit = self.window.findChild(QTextEdit, "txtComments")
        self.btnSave: QPushButton = self.window.findChild(QPushButton, "btnSave")

        if self.btnSave is None:
            raise RuntimeError("No se encontró el botón btnSave en el .ui")

        self.btnSave.clicked.connect(self.on_save_clicked)

    def on_save_clicked(self):
        try:
            contact = self.read_values()
        except ValueError as exc:
            QMessageBox.warning(self.window, "Datos incompletos", str(exc))
            return

        try:
            save_contact(contact)
            QMessageBox.information(
                self.window,
                "Contacto guardado",
                f"Se guardó el contacto en MongoDB:\n{contact}",
            )
            self.empty_fields()
        except Exception as exc:
            QMessageBox.critical(
                self.window,
                "Error al guardar",
                f"Ocurrió un error guardando en MongoDB:\n{exc}",
            )

    def read_values(self) -> Contact:
        first_name = self.txtFirstName.text().strip()
        last_name = self.txtLastName.text().strip()

        if not first_name or not last_name:
            raise ValueError("First Name y Last Name son obligatorios")

        type_of_contact = self.cmbType.currentText()

        if self.rbMale.isChecked():
            sex = "Male"
        elif self.rbFemale.isChecked():
            sex = "Female"
        else:
            sex = "Unknown"

        hobbies: List[str] = [item.text() for item in self.lstHobbies.selectedItems()]
        comments = self.txtComments.toPlainText().strip()

        age = 19

        return Contact(
            first_name=first_name,
            last_name=last_name,
            age=age,
            type_of_contact=type_of_contact,
            sex=sex,
            hobbies=hobbies,
            comments=comments,
        )

    def empty_fields(self):
        self.txtFirstName.clear()
        self.txtLastName.clear()
        self.txtBirthDate.clear()
        self.cmbType.setCurrentIndex(0)
        self.rbMale.setChecked(False)
        self.rbFemale.setChecked(False)
        self.lstHobbies.clearSelection()
        self.txtComments.clear()
        self.txtFirstName.setFocus()


def run():
    app = QApplication(sys.argv)
    window = ContactsWindow()
    window.window.show()
    sys.exit(app.exec())
