import os
from ec.edu.espe.tools.utils.Persistence import Persistence
from ec.edu.espe.tools.model.Tool import Tool

class CsvPersistence(Persistence):
    FILE_NAME = "tools.csv"

    def create(self, tool):
        tools = self.read()
        if any(t.getId() == tool.getId() for t in tools):
            return False
        tools.append(tool)
        return self._save_all(tools)

    def read(self):
        if not os.path.exists(self.FILE_NAME):
            return []
        tools = []
        with open(self.FILE_NAME, "r", encoding="utf-8") as f:
            for line in f:
                line = line.strip()
                if not line:
                    continue
                parts = line.split(",")
                if len(parts) < 5:
                    continue
                id = parts[0]
                name = parts[1]
                price = float(parts[2])
                priceWithIva = float(parts[3])
                materials_str = ",".join(parts[4:])
                materials = [m.strip() for m in materials_str.split(";") if m.strip()]
                tools.append(Tool(id, name, price, materials, priceWithIva))
        return tools

    def update(self, id, tool):
        tools = self.read()
        for i, t in enumerate(tools):
            if t.getId() == id:
                tools[i] = tool
                return self._save_all(tools)
        return False

    def delete(self, id):
        tools = self.read()
        new_tools = [t for t in tools if t.getId() != id]
        if len(new_tools) == len(tools):
            return False
        return self._save_all(new_tools)

    def find(self, id):
        for t in self.read():
            if t.getId() == id:
                return t
        return None

    def _save_all(self, tools):
        try:
            with open(self.FILE_NAME, "w", encoding="utf-8", newline="") as f:
                for t in tools:
                    materials = ";".join(t.getMaterials() or [])
                    f.write(f"{t.getId()},{t.getName()},{t.getPrice()},{t.getPriceWithIva()},{materials}\n")
            return True
        except:
            return False
