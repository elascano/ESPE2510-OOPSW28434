const config = require('../config.json');

class ProductController {
    constructor(model) {
        this.model = model; // Inyección de dependencia (DIP)
        this.taxRate = config.tax_rate || 0.15;
    }

    async handleSave() {
        try {
            const name = document.getElementById('txt_name').value.trim();
            const price = parseFloat(document.getElementById('txt_price').value);
            const qty = parseInt(document.getElementById('txt_qty').value);

            if (!name || isNaN(price) || isNaN(qty)) {
                alert("Please fill all fields correctly.");
                return;
            }

            const subtotal = price * qty;
            const tax = subtotal * this.taxRate;
            const total = subtotal + tax;

            const productData = {
                name,
                price,
                quantity: qty,
                tax: parseFloat(tax.toFixed(2)),
                total: parseFloat(total.toFixed(2)),
                date: new Date()
            };

            const btn = document.getElementById('btn_save');
            btn.disabled = true;

            await this.model.insert(productData);
            
            this.clearInputs();
            await this.refreshTable();
            
            btn.disabled = false;
        } catch (error) {
            alert("Error saving to database.");
            document.getElementById('btn_save').disabled = false;
        }
    }

    async refreshTable() {
        const tbody = document.getElementById('product_table_body');
        if (!tbody) return;

        const products = await this.model.getAll();
        tbody.innerHTML = '';

        products.forEach(product => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${product.name}</td>
                <td>$${product.price}</td>
                <td>${product.quantity}</td>
                <td>$${product.tax}</td>
                <td><strong>$${product.total}</strong></td>
            `;
            tbody.appendChild(row);
        });
    }

    clearInputs() {
        document.getElementById('txt_name').value = '';
        document.getElementById('txt_price').value = '';
        document.getElementById('txt_qty').value = '';
    }
}

module.exports = ProductController;