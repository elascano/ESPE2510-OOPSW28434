document.addEventListener("DOMContentLoaded", () => {

    const name = document.getElementById("name");
    const quantity = document.getElementById("quantity");
    const price = document.getElementById("price");
    const subtotal = document.getElementById("subtotal");
    const iva = document.getElementById("iva");
    const total = document.getElementById("total");
    const btnGuardar = document.getElementById("btnGuardar");
    const btnLeer = document.getElementById("btnLeer");
    const productList = document.getElementById("productList");

    // 1️⃣ Calcular subtotal, IVA y total
    function calcular() {
        const qty = Number(quantity.value);
        const prc = Number(price.value);

        if (qty > 0 && prc > 0) {
            const sub = qty * prc;
            const ivaCalc = sub * 0.15;
            const tot = sub + ivaCalc;

            subtotal.value = sub.toFixed(2);
            iva.value = ivaCalc.toFixed(2);
            total.value = tot.toFixed(2);
        } else {
            subtotal.value = "";
            iva.value = "";
            total.value = "";
        }
    }

    quantity.addEventListener("input", calcular);
    price.addEventListener("input", calcular);

    // 2️⃣ Guardar product
    btnGuardar.addEventListener("click", async () => {
        if (!name.value || !quantity.value || !price.value) {
            alert("Completa todos los campos");
            return;
        }

        const product = {
            name: name.value,
            quantity: Number(quantity.value),
            price: Number(price.value),
            subtotal: Number(subtotal.value),
            iva: Number(iva.value),
            total: Number(total.value)
        };

        try {
            const response = await fetch("http://localhost:3000/api/products", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(product)
            });

            if (response.ok) {
                alert("Product guardado en MongoDB");
                name.value = "";
                quantity.value = "";
                price.value = "";
                subtotal.value = "";
                iva.value = "";
                total.value = "";

                leerProducts(); // recarga lista automáticamente
            } else {
                alert("Error al guardar");
            }

        } catch (error) {
            console.error(error);
            alert("No se pudo conectar al servidor");
        }
    });

    // 3️⃣ Leer products
    async function leerProducts() {
        try {
            const response = await fetch("http://localhost:3000/api/products");
            const products = await response.json();

            productList.innerHTML = "";

            products.forEach(p => {
                const div = document.createElement("div");
                div.classList.add("product-item");
                div.innerHTML = `
                    <strong>${p.name}</strong> - Cantidad: ${p.quantity}, Precio: ${p.price}, Total: ${p.total}
                    <button class="btnEliminar" data-id="${p._id}">Eliminar</button>
                `;
                productList.appendChild(div);
            });

            // Botones eliminar
            document.querySelectorAll(".btnEliminar").forEach(btn => {
                btn.addEventListener("click", async (e) => {
                    const id = e.target.getAttribute("data-id");
                    console.log("Eliminar product con id:", id);
                    await eliminarProduct(id);
                });
            });

        } catch (error) {
            console.error(error);
            alert("No se pudo leer los products");
        }
    }

    btnLeer.addEventListener("click", leerProducts);

    // 4️⃣ Eliminar product
    async function eliminarProduct(id) {
        try {
            const response = await fetch(`http://localhost:3000/api/products/${id}`, {
                method: "DELETE"
            });

            if (response.ok) {
                alert("Product eliminado");
                leerProducts(); // recarga lista
            } else {
                alert("Error al eliminar");
            }
        } catch (error) {
            console.error(error);
            alert("No se pudo conectar al servidor");
        }
    }

    // 5️⃣ Cargar lista al iniciar
    leerProducts();
});
