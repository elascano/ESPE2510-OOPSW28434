class Persistence:
    def create(self, tool): raise NotImplementedError
    def read(self): raise NotImplementedError
    def update(self, id, tool): raise NotImplementedError
    def delete(self, id): raise NotImplementedError
    def find(self, id): raise NotImplementedError
