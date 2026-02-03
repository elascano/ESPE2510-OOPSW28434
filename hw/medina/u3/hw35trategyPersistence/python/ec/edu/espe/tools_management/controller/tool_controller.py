from utils.persistence import Persistence
from model.tool import Tool

class ToolController:
    TAX_RATE = 0.15

    def __init__(self, strategy: Persistence):
        self.strategy = strategy

    def set_strategy(self, strategy: Persistence):
        self.strategy = strategy

    def _calculate_total(self, price: float) -> float:
        return round(price * (1 + self.TAX_RATE), 2)

    def create_tool(self, tool_id, description, price, stock):
        total = self._calculate_total(price)
        new_tool = Tool(tool_id, description, price, stock, total)
        return self.strategy.create(new_tool)

    def get_all_tools(self):
        return self.strategy.read()

    def find_tool(self, tool_id):
        return self.strategy.find(tool_id)

    def update_tool(self, tool_id, description, price, stock):
        total = self._calculate_total(price)
        updated_tool = Tool(tool_id, description, price, stock, total)
        return self.strategy.update(updated_tool)

    def delete_tool(self, tool_id):
        return self.strategy.delete(tool_id)
    
    def calculate_inventory_value(self):
        tools = self.get_all_tools()
        total_base = sum(t.base_price for t in tools)
        total_tax = sum(t.total_with_tax for t in tools)
        return len(tools), total_base, total_tax