// Actualizamos la URL de la API
const API_URL = 'http://localhost:3000/api/sculptures';

// ========================================================
//  [REUSABLE] HELPER: Convertir Texto a Lista (Paso a Paso)
//  Entrada: "  Bronce , Mármol "
//  Salida:  ["Bronce", "Mármol"]
// ========================================================
function convertTextToList(textWithCommas) {
    // 1. Validación básica
    if (!textWithCommas) return [];

    // 2. Separar por comas
    let rawItems = textWithCommas.split(',');
    
    // 3. Crear lista limpia
    let cleanList = [];

    // Bucle clásico "for...of" (es el foreach de JS moderno pero legible)
    for (let item of rawItems) {
        // Quitar espacios
        let cleanItem = item.trim();
        
        // Si no está vacío, lo agregamos
        if (cleanItem !== "") {
            cleanList.push(cleanItem);
        }
    }

    return cleanList;
}

// --- Cargar Tabla ---
async function loadTable(data = null) {
    const tbody = document.getElementById('tableBody');
    tbody.innerHTML = ''; 

    let sculptures = data;
    
    if (!sculptures) {
        try {
            const res = await fetch(API_URL);
            sculptures = await res.json();
        } catch (err) {
            console.error("Error:", err);
            return;
        }
    }

    if (!Array.isArray(sculptures)) sculptures = [sculptures];

    sculptures.forEach(s => {
        const row = tbody.insertRow();
        
        // Manejo visual de nulos por seguridad
        let displayIva = s.priceWithIva !== undefined ? s.priceWithIva : "Calc...";

        row.innerHTML = `
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.materials.join(', ')}</td> <td>${s.price}</td>
            <td>${displayIva}</td>
        `;
        row.onclick = () => selectRow(row, s);
    });
}

// --- Seleccionar Fila ---
function selectRow(row, data) {
    document.querySelectorAll('tr').forEach(r => r.classList.remove('selected'));
    row.classList.add('selected');

    document.getElementById('txtId').value = data.id;
    document.getElementById('txtName').value = data.name;
    document.getElementById('txtPrice').value = data.price;
    // Unimos la lista con comas para mostrarla en el input
    document.getElementById('txtMaterials').value = data.materials.join(', ');
}

// --- Helper para obtener datos del formulario ---
function getFormData() {
    const id = document.getElementById('txtId').value;
    const name = document.getElementById('txtName').value;
    const price = document.getElementById('txtPrice').value;
    const materialsText = document.getElementById('txtMaterials').value;

    if (!id || !name || !price) {
        alert("Fill all fields");
        return null;
    }

    // [USO]: Aquí usamos nuestra función legible
    const materialsList = convertTextToList(materialsText);

    return {
        id, 
        name, 
        price: Number(price), 
        materials: materialsList
    };
}

// --- CREATE ---
async function createSculpture() {
    const data = getFormData();
    if (!data) return;

    try {
        const res = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        const result = await res.json();
        alert(result.message);
        if (result.success) {
            clearForm();
            loadTable();
        }
    } catch (err) {
        alert("Connection Error");
    }
}

// --- UPDATE ---
async function updateSculpture() {
    const data = getFormData();
    if (!data) return;
    const id = document.getElementById('txtId').value;
    
    if(!id) { alert("Select first"); return; }

    try {
        const res = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        const result = await res.json();
        alert(result.message);
        if (result.success) {
            loadTable();
            clearForm();
        }
    } catch (err) {
        alert("Update Error");
    }
}

// --- DELETE ---
async function deleteSculpture() {
    const id = document.getElementById('txtId').value;
    if (!id) { alert("Select to delete"); return; }

    if (!confirm(`Delete ID: ${id}?`)) return;

    try {
        const res = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        const result = await res.json();
        alert(result.message);
        if (result.success) {
            loadTable();
            clearForm();
        }
    } catch (err) {
        alert("Delete Error");
    }
}

// --- FIND ---
async function findSculpture() {
    const id = document.getElementById('txtId').value;
    if (!id) { loadTable(); return; }

    try {
        const res = await fetch(`${API_URL}/${id}`);
        if (res.ok) {
            const data = await res.json();
            loadTable(data);
        } else {
            alert("Not found");
            loadTable();
        }
    } catch (err) {
        alert("Search Error");
    }
}

function clearForm() {
    document.getElementById('txtId').value = '';
    document.getElementById('txtName').value = '';
    document.getElementById('txtPrice').value = '';
    document.getElementById('txtMaterials').value = '';
    document.querySelectorAll('tr').forEach(r => r.classList.remove('selected'));
}

// Iniciar
loadTable();