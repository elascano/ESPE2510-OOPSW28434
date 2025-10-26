class Chicken:
    counter = 1

    def _init_(self, name, color, age, is_molting):
        self.identifier = Chicken.counter
        Chicken.counter += 1

        self.name = name
        self.plumage_color = color
        self.years_old = age
        self.molting = is_molting

    def _str_(self):
        status = 'True' if self.molting else 'False'
        return f"Chicken {{ id => {self.identifier}, name => {self.name}, color => {self.plumage_color}, age => {self.years_old}, isMolting => {status} }}"
