class ComputerController:
    def __init__(self, model):
        self.model = model
        self.fine_rate = 0.03 

    def save_registry(self, brand, rental_fee, days):
        try:
            daily_fee = float(rental_fee)
            delay_days = int(days)

            base_cost = daily_fee * delay_days
            fine_amount = round(base_cost * self.fine_rate, 2)
            total_pay = round(base_cost + fine_amount, 2)

            data = {
                "brand": brand,
                "daily_fee": daily_fee,
                "delay_days": delay_days,
                "tax": fine_amount,
                "total": total_pay
            }

            return self.model.insert(data)

        except Exception as e:
            print(f"Controller Error: {e}")
            return None

    def list_computers(self):
        return self.model.get_all()
