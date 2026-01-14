import { TaxCalculator } from '../utils/TaxCalculator.js';

const calc = new TaxCalculator(0.15);
const API_URL = "http://localhost:3000/api/products";

// CARGAR TABLA
async function loadTable() {
    try {
        const response = await fetch(API_URL);
        const data = await response.json();
        const tableBody = document.getElementById('product-table-body');
        tableBody.innerHTML = '';
        
        let total = 0;
        data.forEach(p => {
            const base = calc.recalculateBase(p.tax_included_price);
            total += p.tax_included_price;

            tableBody.innerHTML += `
                <tr>
                    <td>${p._id}</td>
                    <td>${p.description}</td>
                    <td>$${base.toFixed(2)}</td>
                    <td>$${p.tax_included_price.toFixed(2)}</td>
                    <td>
                        <button onclick="deleteProduct('${p._id}')" style="color:red">Eliminar</button>
                        <button onclick="editProduct('${p._id}', '${p.description}')">Actualizar</button>
                    </td>
                </tr>`;
        });
        document.getElementById('grand-total').innerText = `$${total.toFixed(2)}`;
    } catch (e) { console.error("Error cargando tabla:", e); }
}

// GUARDAR
document.getElementById('product-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.getElementById('prod-id').value;
    const desc = document.getElementById('prod-desc').value;
    const price = parseFloat(document.getElementById('prod-price').value);

    const product = {
        _id: id,
        description: desc,
        tax_included_price: calc.applyTax(price)
    };

    const res = await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(product)
    });

    if (res.ok) {
        e.target.reset();
        loadTable();
    }
});

// FUNCIONES GLOBALES (Para que los botones las vean)
window.deleteProduct = async (id) => {
    if (confirm("¿Eliminar producto?")) {
        await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        loadTable();
    }
};

window.editProduct = async (id, oldDesc) => {
    const newDesc = prompt("Nueva descripción:", oldDesc);
    const newPrice = prompt("Nuevo precio sin IVA:");
    
    if (newDesc && newPrice) {
        const updated = {
            description: newDesc,
            tax_included_price: calc.applyTax(parseFloat(newPrice))
        };
        await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updated)
        });
        loadTable();
    }
};
    // En view/Main.js
window.editProduct = async (id, oldDesc) => {
    const newDesc = prompt("Nueva descripción:", oldDesc);
    const newPrice = prompt("Nuevo precio base (sin IVA):");

    if (newDesc && newPrice) {
        const price = parseFloat(newPrice);
        
        // CÁLCULO DEL 15% ANTES DE ENVIAR
        const updatedData = {
            description: newDesc,
            tax_included_price: calc.applyTax(price)
        };

        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updatedData)
            });

            if (response.ok) {
                alert("¡Actualizado con éxito!");
                loadTable(); // Recarga para ver el recálculo
            } else {
                const errorData = await response.json();
                alert("Error: " + errorData.message);
            }
        } catch (err) {
            alert("No se pudo conectar con el servidor.");
        }
    }
};
window.onload = loadTable;