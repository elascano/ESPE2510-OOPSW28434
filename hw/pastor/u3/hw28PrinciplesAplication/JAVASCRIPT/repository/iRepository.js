class IGenericRepository {
    async create(entity) { throw new Error("Method not implemented"); }
    async readAll() { throw new Error("Method not implemented"); }
}
module.exports = IGenericRepository;