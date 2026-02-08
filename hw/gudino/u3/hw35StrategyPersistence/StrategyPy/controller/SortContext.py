from controller.BubbleSort import BubbleSort
from controller.InsertionSort import InsertionSort
from controller.QuickSort import QuickSort

class SortContext:
    """
    AQUÍ SE IMPLEMENTA EL PATRÓN STRATEGY
    El Context decide qué algoritmo (Strategy) usar
    """

    def __init__(self, data, repository):
        self.data = data
        self.repository = repository
        self.strategy = self._choose_strategy()

    def _choose_strategy(self):
        size = len(self.data)
        if 2 <= size <= 5:
            return BubbleSort()
        elif 6 <= size <= 10:
            return InsertionSort()
        elif size >= 11:
            return QuickSort()
        else:
            raise ValueError("Array must have more than 1 element.")

    def sort(self):
        original = self.data.copy()   # ← COPIA del input original (NO se modifica)
        result = self.strategy.sort(self.data)

        self.repository.save(
            original,
            result,
            self.strategy.__class__.__name__
        )
        return result
