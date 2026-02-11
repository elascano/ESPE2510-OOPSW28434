from model.sorting_context import SortingContext

class SortingController:

    def __init__(self):
        self.context = SortingContext()

    def sort_numbers(self, numbers):
        return self.context.sort(numbers)
