const config = require('../config.json');

class ComputersController {
    constructor(model) {
        this.model = model; // Inyección de dependencia (DIP)
        this.taxRate = config.tax_rate || 0.03; // 3% como en computadoras
    }

    async handleSave() {
        try {
            const brand = document.getElementById('txt_brand').value.trim();
            const dailyFee = parseFloat(document.getElementById('txt_price').value);
            const days = parseInt(document.getElementById('txt_days').value);

            if (!brand || isNaN(dailyFee) || isNaN(days)) {
                alert("Please fill all fields correctly.");
                return;
            }

            const subtotal = dailyFee * days;
            const tax = subtotal * this.taxRate;
            const total = subtotal + tax;

            const computerData = {
                brand,
                daily_fee: dailyFee,
                delay_days: days,
                tax: parseFloat(tax.toFixed(2)),
                total: parseFloat(total.toFixed(2)),
                date: new Date()
            };

            const btn = document.getElementById('btn_save');
            btn.disabled = true;

            await this.model.insert(computerData);

            this.clearInputs();
            await this.refreshTable();

            btn.disabled = false;
        } catch (error) {
            console.error(error);
            alert("Error saving computer data.");
            document.getElementById('btn_save').disabled = false;
        }
    }

    async refreshTable() {
        const tbody = document.getElementById('computer_table_body');
        if (!tbody) return;

        const computers = await this.model.getAll();
        tbody.innerHTML = '';

        computers.forEach(computer => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${computer.brand}</td>
                <td>$${computer.daily_fee}</td>
                <td>${computer.delay_days}</td>
                <td>$${computer.tax}</td>
                <td><strong>$${computer.total}</strong></td>
            `;
            tbody.appendChild(row);
        });
    }

    clearInputs() {
        document.getElementById('txt_brand').value = '';
        document.getElementById('txt_price').value = '';
        document.getElementById('txt_days').value = '';
    }
}

module.exports = ComputersController;
