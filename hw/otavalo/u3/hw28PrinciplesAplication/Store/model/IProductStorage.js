class ILibrary {
    async insert(data) { throw new Error("Method 'insert' must be implemented"); }
    async getAll() { throw new Error("Method 'getAll' must be implemented"); }
}

module.exports = ILibrary;