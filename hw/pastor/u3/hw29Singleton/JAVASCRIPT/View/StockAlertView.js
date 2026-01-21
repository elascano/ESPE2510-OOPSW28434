const output = document.getElementById("output");

document.getElementById("checkBtn").addEventListener("click", async () => {
    const res = await fetch("/check-stock");
    const products = await res.json();

    output.textContent = "";

    if (products.length === 0) {
        output.textContent = " No products with low stock.";
        return;
    }

    for (const p of products) {
        alert(
            " LOW STOCK ALERT\n" +
            "ID: " + p.id + "\n" +
            "Name: " + p.name + "\n" +
            "Stock: " + p.stock
        );

        output.textContent +=
            "LOW STOCK ALERT\n" +
            "ID: " + p.id + "\n" +
            "Name: " + p.name + "\n" +
            "Stock: " + p.stock + "\n" +
            "--------------------------\n";
    }
});

document.getElementById("updateBtn").addEventListener("click", async () => {
    const value = parseInt(document.getElementById("minStockInput").value);

    if (isNaN(value)) {
        alert("Please enter a valid number");
        return;
    }

    await fetch("/update-minimum-stock", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ value })
    });

    alert("Minimum stock updated!");
});
