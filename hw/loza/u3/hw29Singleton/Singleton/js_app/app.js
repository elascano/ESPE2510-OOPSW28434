const express = require("express");
const path = require("path");
const app = express();

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

  getAllShoes() {
    return Array.from(this._data.values());
  }
}

const repository = MongoInventoryRepository.getInstance();

app.use(express.json());
app.use(express.static("public"));

app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "public", "index.html"));
});

app.post("/api/shoes", (req, res) => {
  const { id, name, stock } = req.body;
  
  if (!id || !id.trim()) {
    return res.json({ success: false, message: "Id invalido." });
  }
  if (!name || !name.trim()) {
    return res.json({ success: false, message: "Nombre invalido." });
  }
  if (!Number.isInteger(stock) || stock < 0) {
    return res.json({ success: false, message: "Stock invalido." });
  }
  
  repository.addShoe(id.trim(), name.trim(), stock);
  res.json({ success: true, message: "Zapato guardado." });
});

app.get("/api/shoes", (req, res) => {
  const shoes = repository.getAllShoes();
  res.json(shoes);
});

app.get("/api/shoes/:id", (req, res) => {
  const shoe = repository.findById(req.params.id);
  res.json(shoe);
});

app.post("/api/shoes/:id/buy", (req, res) => {
  const { quantity } = req.body;
  const { id } = req.params;
  
  if (!id || !id.trim()) {
    return res.json({ success: false, message: "Id invalido." });
  }
  
  const shoe = repository.findById(id.trim());
  if (!shoe) {
    return res.json({ success: false, message: "No existe un zapato con ese id." });
  }
  
  if (!Number.isInteger(quantity) || quantity <= 0) {
    return res.json({ success: false, message: "Cantidad invalida." });
  }
  
  const remaining = repository.buy(id.trim(), quantity);
  if (remaining === -2) {
    return res.json({ success: false, message: "Stock insuficiente." });
  }
  if (remaining === -1) {
    return res.json({ success: false, message: "No existe un zapato con ese id." });
  }
  
  let warning = "";
  if (remaining < 5) {
    warning = ` ALERTA: Stock bajo: quedan ${remaining}`;
  }
  
  res.json({ 
    success: true, 
    message: `Compra realizada. Stock restante: ${remaining}${warning}`,
    remaining
  });
});

const PORT = 3000;
app.listen(PORT, () => {
  console.log(`Servidor ejecutandose en http://localhost:${PORT}`);
  console.log("Presione Ctrl+C para salir");
});
