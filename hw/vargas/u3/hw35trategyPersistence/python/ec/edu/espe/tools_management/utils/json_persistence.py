import json
import os
from utils.persistence import Persistence
from model.tool import Tool

class JsonPersistence(Persistence):
    FILE_NAME = "tools.json"

    def create(self, tool: Tool) -> bool:
        tools = self.read()
        if any(t.id == tool.id for t in tools):
            return False
        tools.append(tool)
        return self._save_all(tools)

    def read(self) -> list:
        if not os.path.exists(self.FILE_NAME):
            return []
        try:
            with open(self.FILE_NAME, 'r') as f:
                data = json.load(f)
                return [Tool.from_dict(item) for item in data]
        except (json.JSONDecodeError, IOError):
            return []

    def update(self, tool: Tool) -> bool:
        tools = self.read()
        for i, t in enumerate(tools):
            if t.id == tool.id:
                tools[i] = tool
                return self._save_all(tools)
        return False

    def delete(self, tool_id: str) -> bool:
        tools = self.read()
        initial_len = len(tools)
        tools = [t for t in tools if t.id != tool_id]
        if len(tools) < initial_len:
            return self._save_all(tools)
        return False

    def find(self, tool_id: str) -> Tool:
        tools = self.read()
        for t in tools:
            if t.id == tool_id:
                return t
        return None

    def _save_all(self, tools: list) -> bool:
        try:
            with open(self.FILE_NAME, 'w') as f:
                json.dump([t.to_dict() for t in tools], f, indent=4)
            return True
        except IOError:
            return False