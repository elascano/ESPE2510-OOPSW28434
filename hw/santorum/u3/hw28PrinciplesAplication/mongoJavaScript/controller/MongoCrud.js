const Toy = require("../model/Toy");

class MongoCrud {

  async create(toy) {
    return await Toy.create(toy);
  }

  async readAll() {
    return await Toy.find();
  }

  async readById(id) {
    return await Toy.findOne({ id });
  }

  async update(toy) {
    await Toy.updateOne(
      { id: toy.id },
      {
        $set: {
          name: toy.name,
          price: toy.price,
          priceIva: toy.priceIva  
        }
      }
    );
    return true;
  }

  async delete(id) {
    await Toy.deleteOne({ id });
    return true;
  }
}

module.exports = MongoCrud;
