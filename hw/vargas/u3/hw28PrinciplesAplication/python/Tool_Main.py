import tkinter as tk
from Tool_Repository import Tool_Repository
from Tax_Service import Tax_Service
from Tool_View import Tool_View
from Tool_Controller import Tool_Controller

if __name__ == "__main__":
    repo = Tool_Repository()       
    calculator = Tax_Service()
    
    root = tk.Tk()
    view = Tool_View(root)
    
    controller = Tool_Controller(view, repo, calculator)
    
    root.mainloop()