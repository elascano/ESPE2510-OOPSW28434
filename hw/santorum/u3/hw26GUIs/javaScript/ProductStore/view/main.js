const form = document.getElementById("productForm");
const statusEl = document.getElementById("status");
const tbody = document.getElementById("productsTbody");
const reloadBtn = document.getElementById("reloadBtn");

const formTitle = document.getElementById("formTitle");
const submitBtn = document.getElementById("submitBtn");
const cancelEditBtn = document.getElementById("cancelEditBtn");

const editingIdEl = document.getElementById("editingId");
const nameEl = document.getElementById("name");
const priceEl = document.getElementById("price");
const ivaRateEl = document.getElementById("ivaRate");


function setStatus(msg, isError = false) {
  statusEl.textContent = msg;
  statusEl.style.color = isError ? "crimson" : "green";
}

function parseLocaleNumber(value) {
  return Number(String(value).trim().replace(",", "."));
}

function calcPriceWithIva(price, ivaRate) {
  return Number((price * (1 + ivaRate)).toFixed(2));
}

function escapeHtml(str) {
  return String(str)
    .replaceAll("&", "&amp;")
    .replaceAll("<", "&lt;")
    .replaceAll(">", "&gt;")
    .replaceAll('"', "&quot;")
    .replaceAll("'", "&#039;");
}


function setCreateMode() {
  editingIdEl.value = "";
  formTitle.textContent = "Create product";
  submitBtn.textContent = "Save";
  cancelEditBtn.classList.add("hidden");
  form.reset();
  ivaRateEl.value = "0.12";
}

function setEditMode(product) {
  editingIdEl.value = product._id;
  formTitle.textContent = "Edit product";
  submitBtn.textContent = "Update";
  cancelEditBtn.classList.remove("hidden");

  nameEl.value = product.name;
  priceEl.value = product.price;
  ivaRateEl.value = product.ivaRate;
}


async function loadProducts() {
  tbody.innerHTML = `<tr><td colspan="6">Loading...</td></tr>`;

  try {
    const res = await fetch("/api/products");
    const products = await res.json();

    if (!Array.isArray(products) || products.length === 0) {
      tbody.innerHTML = `<tr><td colspan="6">Don't have products.</td></tr>`;
      return;
    }

    tbody.innerHTML = products.map(p => {
      const uiCalc = calcPriceWithIva(p.price, p.ivaRate);

      return `
        <tr>
          <td>${escapeHtml(p.name)}</td>
          <td>${p.price.toFixed(2)}</td>
          <td>${(p.ivaRate * 100).toFixed(0)}%</td>
          <td>${p.priceWithIva.toFixed(2)}</td>
          <td>${uiCalc.toFixed(2)}</td>
          <td>
            <button data-action="edit" data-id="${p._id}">Update</button>
            <button data-action="delete" data-id="${p._id}">Delete</button>
          </td>
        </tr>
      `;
    }).join("");

  } catch (err) {
    console.error(err);
    tbody.innerHTML = `<tr><td colspan="6">Error loading products</td></tr>`;
  }
}


form.addEventListener("submit", async (e) => {
  e.preventDefault();
  setStatus("");

  const name = nameEl.value.trim();
  const price = parseLocaleNumber(priceEl.value);
  const ivaRate = parseLocaleNumber(ivaRateEl.value);
  const editingId = editingIdEl.value;

  if (!name || Number.isNaN(price) || Number.isNaN(ivaRate)) {
    setStatus("Invalid data. Use 10.50 and IVA 0.12 (or 0,12).", true);
    return;
  }

  const payload = { name, price, ivaRate };
  const isEdit = Boolean(editingId);

  try {
    const res = await fetch(
      isEdit ? `/api/products/${editingId}` : "/api/products",
      {
        method: isEdit ? "PUT" : "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
      }
    );

    const data = await res.json();

    if (!res.ok) {
      setStatus(data.error || "Error saving.", true);
      return;
    }

    setStatus(isEdit ? "Product updated " : "Product created ");
    setCreateMode();
    loadProducts();

  } catch (err) {
    console.error(err);
    setStatus("Error of network.", true);
  }
});


tbody.addEventListener("click", async (e) => {
  const btn = e.target.closest("button");
  if (!btn) return;

  const action = btn.dataset.action;
  const id = btn.dataset.id;

  if (action === "edit") {
    const res = await fetch(`/api/products/${id}`);
    const product = await res.json();
    setEditMode(product);
    window.scrollTo({ top: 0, behavior: "smooth" });
  }

  if (action === "delete") {
    if (!confirm("¿Delete product?")) return;

    await fetch(`/api/products/${id}`, { method: "DELETE" });
    setStatus("Product deleted ");
    loadProducts();
  }
});

reloadBtn.addEventListener("click", loadProducts);


setCreateMode();
loadProducts();
