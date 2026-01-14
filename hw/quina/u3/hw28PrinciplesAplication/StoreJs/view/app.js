const loadTable = async () => {
    const res = await fetch('/api/products');
    const products = await res.json();
    const tbody = document.getElementById('tblBody');
    tbody.innerHTML = '';
    products.forEach(p => {
        tbody.innerHTML += `<tr><td>${p.id}</td><td>${p.name}</td><td>${p.basePrice}</td><td>${p.finalPrice}</td></tr>`;
    });
};

document.getElementById('btnCreate').onclick = async () => {
    const data = { id: txtId.value, name: txtName.value, price: txtPrice.value };
    await fetch('/api/products', { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(data) });
    loadTable();
};

document.getElementById('btnSearch').onclick = async () => {
    const res = await fetch(`/api/products/${txtId.value}`);
    const p = await res.json();
    if(p) { txtName.value = p.name; txtPrice.value = p.basePrice; }
};

document.getElementById('btnUpdate').onclick = async () => {
    const data = { id: txtId.value, name: txtName.value, price: txtPrice.value };
    await fetch('/api/products', { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(data) });
    loadTable();
};

document.getElementById('btnDelete').onclick = async () => {
    await fetch(`/api/products/${txtId.value}`, { method: 'DELETE' });
    loadTable();
};

loadTable();