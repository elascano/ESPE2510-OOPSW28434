class Persistence {
  async create(instrument) { throw new Error("Not implemented"); }
  async read() { throw new Error("Not implemented"); }
  async update(id, instrument) { throw new Error("Not implemented"); }
  async delete(id) { throw new Error("Not implemented"); }
  async find(id) { throw new Error("Not implemented"); }
}

module.exports = Persistence;
