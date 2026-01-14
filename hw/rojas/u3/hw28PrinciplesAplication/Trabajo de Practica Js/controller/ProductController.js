import { ConnectionDB } from './ConnectionDB.js';
import { TaxCalculator } from '../utils/TaxCalculator.js';
import { Product } from '../model/Product.js';

export class ProductController {
    constructor(view) {
        this.view = view;
        this.db = new ConnectionDB(); // La conexión vive aquí
        this.calculator = new TaxCalculator(0.15);
    }

    async startup() {
        await this.db.connect();
    }

    async registerProduct(id, desc, price) {
        const exists = await this.db.findById(id);
        if (exists) {
            this.view.renderMessage("Error: Este ID ya existe.");
            return;
        }

        const total = this.calculator.addTax(price);
        
        // Se instancia el modelo con sus atributos
        const newProduct = new Product(id, desc, price, total);

        // Se guarda usando el servicio de conexión
        await this.db.insert(newProduct.toData());
        this.view.renderMessage("Producto guardado exitosamente.");
    }

    async listAll() {
        const data = await this.db.getAll();
        
        const output = data.map(p => ({
            id: p._id,
            detalle: p.description,
            base_recalculada: this.calculator.removeTax(p.tax_included_price),
            precio_final: p.tax_included_price
        }));

        const totalGeneral = this.calculator.sumTotal(data);
        this.view.renderTable(output, totalGeneral);
    }

    async shutdown() {
        await this.db.close();
    }
}