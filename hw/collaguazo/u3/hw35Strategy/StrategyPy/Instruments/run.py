from pathlib import Path
import sys

root_path = str(Path(__file__).resolve().parent / "src" / "main" / "python")
sys.path.insert(0, root_path)

from ec.edu.espe.instruments.view.FrmInstrument import FrmInstrument

if __name__ == "__main__":
    app = FrmInstrument()
    app.mainloop()