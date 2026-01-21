import tkinter as tk
from controllers.sales_controller import SalesController
from views.sales_view import SalesView


class Application:
    """Main application class coordinating MVC components."""
    
    def __init__(self) -> None:
        self.root = tk.Tk()
        self.controller = SalesController()
        self.view = SalesView(self.root)
        
        self._setup_callbacks()
        self._update_initial_display()
    
    def _setup_callbacks(self) -> None:
        """Setup callbacks between view and controller."""
        self.view.set_on_apply_discount_callback(self._handle_discount_update)
        self.view.set_on_create_sale_callback(self._handle_create_sale)
    
    def _update_initial_display(self) -> None:
        """Update initial display with current discount."""
        current_discount = self.controller.get_current_discount()
        self.view.update_current_discount(current_discount)
        self.view.discount_var.set(str(current_discount))
    
    def _handle_discount_update(self, new_discount: float) -> bool:
        """Handle discount update request."""
        try:
            success = self.controller.update_discount_percentage(new_discount)
            if success:
                # Update sales table to reflect new discount
                sales_summary = self.controller.get_sales_summary()
                self.view.update_sales_table(sales_summary)
            return success
        except ValueError as e:
            self.view.show_error(str(e))
            return False
    
    def _handle_create_sale(self, item_name: str, price: float) -> None:
        """Handle create sale request."""
        try:
            self.controller.create_sale(item_name, price)
            sales_summary = self.controller.get_sales_summary()
            self.view.update_sales_table(sales_summary)
        except ValueError as e:
            self.view.show_error(str(e))
    
    def run(self) -> None:
        """Run the application."""
        self.root.mainloop()


if __name__ == "__main__":
    app = Application()
    app.run()