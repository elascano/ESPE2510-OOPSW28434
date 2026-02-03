class Persistence:
    def create(self, instrument): raise NotImplementedError
    def read(self): raise NotImplementedError
    def update(self, id, instrument): raise NotImplementedError
    def delete(self, id): raise NotImplementedError
    def find(self, id): raise NotImplementedError
