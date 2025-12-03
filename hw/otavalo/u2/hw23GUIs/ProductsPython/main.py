from PyQt5 import QtWidgets, uic
import sys

app = QtWidgets.QApplication(sys.argv)
window = uic.loadUi("productsUGI.ui")

window.setMinimumSize(800, 600)
window.show()

sys.exit(app.exec())
