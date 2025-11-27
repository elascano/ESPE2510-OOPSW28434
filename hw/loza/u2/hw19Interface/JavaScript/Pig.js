import { FarmAnimal } from "./FarmAnimal.js";

export class Pig extends FarmAnimal {
    toString() {
        return `{
  "farmAnimal": ${super.toString()}
}`;
    }
}
