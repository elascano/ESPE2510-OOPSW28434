class ChickenFarmer:
    def __init__(self, name):
        self.__name = name
        self.__coops = []
        self.__coop_iterator_index = 0
        self.__iter_index = 0 

    def add(self, coop):
        if coop:
            self.__coops.append(coop)

    def remove(self, coop_id):
        initial_length = len(self.__coops)
        self.__coops = [coop for coop in self.__coops if coop.id != coop_id]
        return len(self.__coops) < initial_length

    def get_name(self):
        return self.__name
    
    def reset_iteration(self):
        self.__coop_iterator_index = 0

    def next(self):
        if self.__coop_iterator_index < len(self.__coops):
            next_coop = self.__coops[self.__coop_iterator_index]
            self.__coop_iterator_index += 1
            return next_coop
        return None

    def __iter__(self):
        self.__iter_index = 0 
        return self

    def __next__(self):
        if self.__iter_index < len(self.__coops):
            coop = self.__coops[self.__iter_index]
            self.__iter_index += 1
            return coop
        raise StopIteration()