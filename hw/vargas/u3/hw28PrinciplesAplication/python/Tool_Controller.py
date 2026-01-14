from Tool_Model import Tool
from ITool_View import ITool_View
from ITool_Repository import ITool_Repository
from ITax_Calculator import ITax_Calculator

class Tool_Controller:
    def __init__(self, view: ITool_View, repo: ITool_Repository, calc: ITax_Calculator):
        self.view = view
        self.repo = repo
        self.calc = calc
        
        self.view.set_controller(self)
        self.handle_refresh() 

    def handle_save(self):
        try:
            data = self.view.get_form_data()
            
            if not data['id'] or not data['description']:
                self.view.show_message("Validation", "Missing ID or Description", True)
                return

            base_price = float(data['price_text'])
            stock = int(data['stock_text']) if data['stock_text'] else 0

            total_with_tax = self.calc.calculate_total(base_price)

            new_tool = Tool(
                tool_id=data['id'],
                description=data['description'],
                base_price=base_price,
                stock=stock,
                total_with_tax=total_with_tax
            )

            if self.repo.save(new_tool):
                self.view.show_message("Success", f"Saved! Total: ${total_with_tax:.2f}")
                self.view.clear_form()
                self.handle_refresh()
            else:
                self.view.show_message("Error", "ID already exists or DB error", True)

        except ValueError:
            self.view.show_message("Error", "Invalid numbers", True)

    def handle_refresh(self):
        tools = self.repo.get_all()
        self.view.update_list(tools)