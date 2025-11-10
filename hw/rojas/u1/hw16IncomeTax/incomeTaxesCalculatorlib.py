#author Josue Rojas
#library
from dataclasses import dataclass
from typing import List, Optional

CFB_JAN_2025 = 798.31 

@dataclass(frozen=True)
class Bracket:
    lower: float
    upper: float
    rate: float
    base_tax: float 

@dataclass
class Result:
    annual_gross: float
    taxable_income: float
    caused_tax: float
    gp_rebate: float
    annual_tax: float
    monthly_withholding: float

class IncomeTaxEC:
    """Calculadora IR Ecuador 2025."""

    def __init__(self, cfb_value: float = CFB_JAN_2025, brackets: Optional[List[Bracket]] = None):
        self.cfb_value = cfb_value
        self.brackets = brackets or [
            Bracket(0,       12081,   0.00,     0),
            Bracket(12081,   15387,   0.05,     0),
            Bracket(15387,   19978,   0.10,   165),
            Bracket(19978,   26422,   0.12,   624),
            Bracket(26422,   34770,   0.15,  1398),
            Bracket(34770,   46089,   0.20,  2650),
            Bracket(46089,   61359,   0.25,  4941),
            Bracket(61359,   81817,   0.30,  8731),
            Bracket(81817,  108810,   0.35, 14869),
            Bracket(108810, float("inf"), 0.37, 24316),
        ]

    @staticmethod
    def _canastas(dependents: int) -> int:
        if dependents <= 0: return 7
        if dependents == 1: return 9
        if dependents == 2: return 11
        if dependents == 3: return 14
        if dependents == 4: return 17
        return 20  # 5+

    def compute(self, monthly_salary: float, annual_spent: float, dependents: int = 0) -> Result:
        if monthly_salary < 0 or annual_spent < 0 or dependents < 0:
            raise ValueError("Non-negative inputs required")

        annual_gross = monthly_salary * 12.0
        taxable = annual_gross

        caused_tax = 0.0
        for b in self.brackets:
            if b.lower <= taxable < b.upper:
                caused_tax = b.base_tax + (taxable - b.lower) * b.rate
                break

        cap = self.cfb_value * self._canastas(dependents)
        gp_rebate = 0.18 * min(annual_spent, cap)

        annual_tax = max(0.0, caused_tax - gp_rebate)
        monthly_withholding = annual_tax / 12.0

        return Result(
            annual_gross,
            taxable,
            caused_tax,
            gp_rebate,
            annual_tax,
            monthly_withholding
        )
