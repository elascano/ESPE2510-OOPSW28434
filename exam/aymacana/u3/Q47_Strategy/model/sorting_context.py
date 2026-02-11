from model.bubble_sort import BubbleSort
from model.insertion_sort import InsertionSort
from model.quick_sort import QuickSort

class SortingContext:

    def sort(self, data):
        size = len(data)
        strategy = self.set_strategy(size)
        return strategy.sort(data)

    def set_strategy(self, n):
        if n < 30:
            return BubbleSort()
        elif n < 100:
            return InsertionSort()
        else:
            return QuickSort()
