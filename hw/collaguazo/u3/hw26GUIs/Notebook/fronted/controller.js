document.addEventListener("DOMContentLoaded", () => {

    const name = document.getElementById("name");
    const quantity = document.getElementById("quantity");
    const price = document.getElementById("price");
    const subtotal = document.getElementById("subtotal");
    const iva = document.getElementById("iva");
    const total = document.getElementById("total");
    const btnGuardar = document.getElementById("btnGuardar");
    const btnLeer = document.getElementById("btnLeer");
    const notebookList = document.getElementById("notebookList");

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

    // 2️⃣ Guardar notebook
    btnGuardar.addEventListener("click", async () => {
        if (!name.value || !quantity.value || !price.value) {
            alert("Completa todos los campos");
            return;
        }

        const notebook = {
            name: name.value,
            quantity: Number(quantity.value),
            price: Number(price.value),
            subtotal: Number(subtotal.value),
            iva: Number(iva.value),
            total: Number(total.value)
        };

        try {
            const response = await fetch("http://localhost:3000/api/notebooks", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(notebook)
            });

            if (response.ok) {
                alert("Notebook guardado en MongoDB");
                name.value = "";
                quantity.value = "";
                price.value = "";
                subtotal.value = "";
                iva.value = "";
                total.value = "";

                leerNotebooks(); // recarga lista automáticamente
            } else {
                alert("Error al guardar");
            }

        } catch (error) {
            console.error(error);
            alert("No se pudo conectar al servidor");
        }
    });

    // 3️⃣ Leer notebooks
    async function leerNotebooks() {
        try {
            const response = await fetch("http://localhost:3000/api/notebooks");
            const notebooks = await response.json();

            notebookList.innerHTML = "";

            notebooks.forEach(nb => {
                const div = document.createElement("div");
                div.classList.add("notebook-item");
                div.innerHTML = `
                    <strong>${nb.name}</strong> - Cantidad: ${nb.quantity}, Precio: ${nb.price}, Total: ${nb.total}
                    <button class="btnEliminar" data-id="${nb._id}">Eliminar</button>
                `;
                notebookList.appendChild(div);
            });

            // Botones eliminar
            document.querySelectorAll(".btnEliminar").forEach(btn => {
                btn.addEventListener("click", async (e) => {
                    const id = e.target.getAttribute("data-id");
                    console.log("Eliminar notebook con id:", id);
                    await eliminarNotebook(id);
                });
            });

        } catch (error) {
            console.error(error);
            alert("No se pudo leer los notebooks");
        }
    }

    btnLeer.addEventListener("click", leerNotebooks);

    // 4️⃣ Eliminar notebook
    async function eliminarNotebook(id) {
        try {
            const response = await fetch(`http://localhost:3000/api/notebooks/${id}`, {
                method: "DELETE"
            });

            if (response.ok) {
                alert("Notebook eliminado");
                leerNotebooks(); // recarga lista
            } else {
                alert("Error al eliminar");
            }
        } catch (error) {
            console.error(error);
            alert("No se pudo conectar al servidor");
        }
    }

    // 5️⃣ Cargar lista al iniciar
    leerNotebooks();
});
