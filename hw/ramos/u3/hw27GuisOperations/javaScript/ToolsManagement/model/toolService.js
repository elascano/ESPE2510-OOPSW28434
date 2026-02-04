class ToolService {
  isValidNumber(n) {
    return typeof n === "number" && Number.isFinite(n) && !Number.isNaN(n);
  }

  validatePayload(body) {
    const { id, name, price, ivaRate, stock, description } = body;

    if (!id || typeof id !== "string") return "id is required (string).";
    if (!name || typeof name !== "string") return "name is required (string).";

    if (!this.isValidNumber(price) || price < 0) return "price is required (number >= 0).";
    if (!this.isValidNumber(ivaRate) || ivaRate < 0) return "ivaRate is required (number >= 0). Example: 0.12";
    if (!this.isValidNumber(stock) || stock < 0) return "stock is required (number >= 0).";

    if (!description || typeof description !== "string") return "description is required (string).";

    return null;
  }

  calcPriceWithIva(price, ivaRate) {
    return Number((price * (1 + ivaRate)).toFixed(2));
  }

  normalizeStrings(payload) {
    return {
      id: String(payload.id ?? "").trim(),
      name: String(payload.name ?? "").trim(),
      description: String(payload.description ?? "").trim()
    };
  }

  buildPersistedTool(payload) {
    const { id, name, description } = this.normalizeStrings(payload);

    const price = payload.price;
    const ivaRate = payload.ivaRate;
    const stock = payload.stock;

    const priceWithIva = this.calcPriceWithIva(price, ivaRate);

    return { id, name, price, ivaRate, priceWithIva, stock, description };
  }
}

module.exports = ToolService;