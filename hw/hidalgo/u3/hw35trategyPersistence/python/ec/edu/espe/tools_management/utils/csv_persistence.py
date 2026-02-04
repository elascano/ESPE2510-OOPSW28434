import csv
import os
from typing import List
from utils.persistence import Persistence
from model.tool import Tool

class CsvPersistence(Persistence):
    FILE_NAME = "tools.csv"

    def create(self, tool: Tool) -> bool:
        tools = self.read()
        if any(t.id == tool.id for t in tools):
            return False
        
        tools.append(tool)
        return self._save_all(tools)

    def read(self) -> List[Tool]:
        if not os.path.exists(self.FILE_NAME):
            return []
        
        tools = []
        try:
            with open(self.FILE_NAME, mode='r', newline='', encoding='utf-8') as f:
                reader = csv.reader(f)
                for row in reader:
                    if len(row) >= 5:
                        t = Tool(
                            tool_id=row[0],
                            description=row[1],
                            base_price=float(row[2]),
                            stock=int(row[3]),
                            total_with_tax=float(row[4])
                        )
                        tools.append(t)
        except Exception as e:
            print(f"Error reading CSV: {e}")
        return tools

    def update(self, tool: Tool) -> bool:
        tools = self.read()
        found = False
        for i, t in enumerate(tools):
            if t.id == tool.id:
                tools[i] = tool
                found = True
                break
        
        if found:
            return self._save_all(tools)
        return False

    def delete(self, tool_id: str) -> bool:
        tools = self.read()
        initial_count = len(tools)
        
        tools = [t for t in tools if t.id != tool_id]
        
        if len(tools) < initial_count:
            return self._save_all(tools)
        return False

    def find(self, tool_id: str) -> Tool:
        tools = self.read()
        for t in tools:
            if t.id == tool_id:
                return t
        return None

    def _save_all(self, tools: List[Tool]) -> bool:
        try:
            with open(self.FILE_NAME, mode='w', newline='', encoding='utf-8') as f:
                writer = csv.writer(f)
                for t in tools:
                    writer.writerow([
                        t.id, 
                        t.description, 
                        t.base_price, 
                        t.stock, 
                        t.total_with_tax
                    ])
            return True
        except Exception as e:
            print(f"Error writing CSV: {e}")
            return False