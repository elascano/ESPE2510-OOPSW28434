from pathlib import Path
import sys

sys.path.insert(0, str(Path(__file__).resolve().parent / "src" / "main" / "python"))

from ec.edu.espe.tools.Main import Main

Main.main()
