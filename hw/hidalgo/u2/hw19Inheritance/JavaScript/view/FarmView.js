export class FarmView {
    showAnimalAdded(msg) {
        console.log(msg);
    }

    showAnimalsInCage(list) {
        console.log("Animals in cage:");
        console.log(list);
    }

    showCagesList(list) {
        console.log("Cages in farm:");
        console.log(list);
    }

    showAnimalSound(animal) {
        console.log(animal.makeSound());
    }
}
