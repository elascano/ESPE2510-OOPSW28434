class TaxCalculator:
    """
    Calculadora de impuestos sobre la renta.
    """

    # Tabla de impuestos progresiva (ejemplo, puedes actualizar)
    TAX_BRACKETS = [
        (0, 500, 0.0),
        (500.01, 1000, 0.1),
        (1000.01, 2000, 0.15),
        (2000.01, float("inf"), 0.2),
    ]

    def __init__(self, salary_monthly: float, expenses: float = 0.0):
        if salary_monthly < 0 or expenses < 0:
            raise ValueError("Salary and expenses must be non-negative numbers.")
        self.salary_monthly = salary_monthly
        self.expenses = expenses

    def taxable_income(self) -> float:
        """
        Calcula ingreso sujeto a impuestos.
        """
        return max(self.salary_monthly - self.expenses, 0)

    def tax_monthly(self) -> float:
        """
        Calcula impuesto mensual según tabla progresiva.
        """
        income = self.taxable_income()
        for lower, upper, rate in self.TAX_BRACKETS:
            if lower <= income <= upper:
                return income * rate
        return 0.0

    def tax_annual(self) -> float:
        """
        Calcula impuesto anual.
        """
        return self.tax_monthly() * 12

    def net_salary_monthly(self) -> float:
        """
        Calcula salario neto mensual después de impuestos.
        """
        return self.salary_monthly - self.tax_monthly()

    def net_salary_annual(self) -> float:
        """
        Calcula salario neto anual después de impuestos.
        """
        return self.net_salary_monthly() * 12