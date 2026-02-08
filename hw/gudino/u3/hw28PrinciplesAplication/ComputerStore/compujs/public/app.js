const API = "http://localhost:3000/products";

document.getElementById("add").onclick = async () => {
    const name = document.getElementById("name").value;
    const make = document.getElementById("make").value;
    const price = document.getElementById("price").value;

    await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            name,
            make,
            basePrice: Number(price)
        })
    });

    loadProducts();
};

document.getElementById("refresh").onclick = loadProducts;

async function loadProducts() {
    const res = await fetch(API);
    const products = await res.json();

    const totalRes = await fetch(`${API}/total`);
    const { total } = await totalRes.json();

    const tbody = document.getElementById("products");
    tbody.innerHTML = "";

    products.forEach(p => {
        tbody.innerHTML += `
            <tr>
                <td>${p.name}</td>
                <td>${p.make}</td>
                <td>$${p.basePrice.toFixed(2)}</td>
                <td>$${p.finalPrice.toFixed(2)}</td>
            </tr>
        `;
    });

    document.getElementById("total").innerText =
        `$${total.toFixed(2)}`;
}

loadProducts();
