class Shoe {
  constructor(id, name, stock) {
    this.id = id;
    this.name = name;
    this.stock = stock;
  }
}

class MongoInventoryRepository {
  static _instance = null;

  constructor() {
    if (MongoInventoryRepository._instance) {
      return MongoInventoryRepository._instance;
    }
    this._data = new Map();
    MongoInventoryRepository._instance = this;
  }

  static getInstance() {
    return new MongoInventoryRepository();
  }

  addShoe(id, name, stock) {
    this._data.set(id, new Shoe(id, name, stock));
  }

  findById(id) {
    return this._data.get(id) || null;
  }

  buy(id, quantity) {
    if (quantity <= 0) {
      throw new Error("Cantidad invalida.");
    }
    const shoe = this._data.get(id);
    if (!shoe) {
      return -1;
    }
    if (shoe.stock < quantity) {
      return -2;
    }
    shoe.stock -= quantity;
    return shoe.stock;
  }
}

console.log("=== PRUEBA DEL SINGLETON EN JAVASCRIPT ===");
const repo = MongoInventoryRepository.getInstance();
const repo2 = MongoInventoryRepository.getInstance();
console.log("¿Son la misma instancia?", repo === repo2);
console.log();

console.log("=== AGREGANDO ZAPATOS ===");
repo.addShoe("S001", "Nike Air Max", 10);
repo.addShoe("S002", "Adidas Ultraboost", 5);
repo.addShoe("S003", "Puma RS-X", 3);
console.log("Zapatos agregados correctamente");
console.log();

console.log("=== MOSTRANDO ZAPATOS ===");
["S001", "S002", "S003"].forEach((shoeId) => {
  const shoe = repo.findById(shoeId);
  if (shoe) {
    console.log(shoeId + ": " + shoe.name + " - Stock: " + shoe.stock);
  }
});
console.log();

console.log("=== REALIZANDO COMPRAS ===");
let remaining = repo.buy("S001", 3);
console.log("Compra de 3 unidades de S001. Stock restante: " + remaining);
remaining = repo.buy("S003", 1);
console.log("Compra de 1 unidad de S003. Stock restante: " + remaining);
console.log();

console.log("=== ESTADO FINAL ===");
["S001", "S002", "S003"].forEach((shoeId) => {
  const shoe = repo.findById(shoeId);
  if (shoe) {
    console.log(shoeId + ": " + shoe.name + " - Stock: " + shoe.stock);
  }
});
