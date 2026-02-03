import tkinter as tk
from controller.tool_controller import ToolController
from view.frm_tool import FrmTool

from utils.csv_persistence import CsvPersistence 
from utils.mongo_persistence import MongoPersistence
from utils.json_persistence import JsonPersistence

if __name__ == "__main__":
    strategy = JsonPersistence()

    controller = ToolController(strategy)
    root = tk.Tk()
    app = FrmTool(root, controller)
    root.mainloop()