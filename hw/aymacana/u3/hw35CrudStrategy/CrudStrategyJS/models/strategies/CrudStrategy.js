export class CrudStrategy {
    async add(customer) {
        throw new Error("Method 'add()' must be implemented");
    }

    async delete(customerId) {
        throw new Error("Method 'delete()' must be implemented");
    }

    async update(customerId, customer) {
        throw new Error("Method 'update()' must be implemented");
    }

    async readAll() {
        throw new Error("Method 'readAll()' must be implemented");
    }

    async readById(customerId) {
        throw new Error("Method 'readById()' must be implemented");
    }

    getFormatName() {
        throw new Error("Method 'getFormatName()' must be implemented");
    }
}