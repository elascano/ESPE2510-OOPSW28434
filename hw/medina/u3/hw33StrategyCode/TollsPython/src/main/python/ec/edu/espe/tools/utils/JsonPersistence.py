import os
import json
from ec.edu.espe.tools.utils.Persistence import Persistence
from ec.edu.espe.tools.model.Tool import Tool

class JsonPersistence(Persistence):
    FILE_NAME = "tools.json"

    def create(self, tool):
        tools = self.read()
        if any(t.getId() == tool.getId() for t in tools):
            return False
        tools.append(tool)
        return self._save_all(tools)

    def read(self):
        if not os.path.exists(self.FILE_NAME):
            return []
        try:
            raw = open(self.FILE_NAME, "r", encoding="utf-8").read().strip()
            if not raw:
                return []
            arr = json.loads(raw)
            tools = []
            for o in (arr or []):
                tools.append(Tool(o["id"], o["name"], float(o["price"]), o.get("materials", []) or [], float(o["priceWithIva"])))
            return tools
        except:
            return []

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
            arr = [{
                "id": t.getId(),
                "name": t.getName(),
                "price": t.getPrice(),
                "materials": t.getMaterials() or [],
                "priceWithIva": t.getPriceWithIva()
            } for t in tools]
            with open(self.FILE_NAME, "w", encoding="utf-8") as f:
                json.dump(arr, f, indent=2)
            return True
        except:
            return False
