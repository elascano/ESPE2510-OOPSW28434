console.log(" UI loaded");

// DOM
const form = document.getElementById("toolForm"); 
const statusEl = document.getElementById("status");
const tbody = document.getElementById("toolsTbody");
const reloadBtn = document.getElementById("reloadBtn");
const searchBtn = document.getElementById("searchBtn");

const formTitle = document.getElementById("formTitle");
const submitBtn = document.getElementById("submitBtn");
const cancelEditBtn = document.getElementById("cancelEditBtn");
const editingIdEl = document.getElementById("editingId");

const tidEl = document.getElementById("tid"); 
const nameEl = document.getElementById("name");
const priceEl = document.getElementById("price");
const ivaRateEl = document.getElementById("ivaRate");
const stockEl = document.getElementById("stock");
const descEl = document.getElementById("description");

function setStatus(msg, isError = false) {
  statusEl.textContent = msg;
  statusEl.style.color = isError ? "crimson" : "green";
}

function parseLocaleNumber(v) {
  return Number(String(v).trim().replace(",", "."));
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
  formTitle.textContent = "Create tool"; 
  submitBtn.textContent = "Save";
  cancelEditBtn.classList.add("hidden");

  tidEl.disabled = false;
  form.reset();
  ivaRateEl.value = "0.12";
  setStatus("");
  tidEl.focus();
}

function setEditMode(tool) {
  editingIdEl.value = tool.id;
  formTitle.textContent = "Edit tool"; 
  submitBtn.textContent = "Update";
  cancelEditBtn.classList.remove("hidden");

  tidEl.value = tool.id;
  nameEl.value = tool.name;
  priceEl.value = tool.price;
  ivaRateEl.value = tool.ivaRate;
  stockEl.value = tool.stock;
  descEl.value = tool.description;

  tidEl.disabled = true;
}

async function loadTools() { 
  try {
    const res = await fetch("/api/tools"); 
    const tools = await res.json();

    if (!res.ok) {
      setStatus(tools?.error || "Error loading tools.", true);
      tbody.innerHTML = "";
      return;
    }

    if (!Array.isArray(tools) || tools.length === 0) {
      tbody.innerHTML = `<tr><td colspan="8">No tools found.</td></tr>`;
      return;
    }

    tbody.innerHTML = tools.map((t) => `
      <tr>
        <td>${escapeHtml(t.id)}</td>
        <td>${escapeHtml(t.name)}</td>
        <td>${Number(t.price).toFixed(2)}</td>
        <td>${(Number(t.ivaRate) * 100).toFixed(0)}%</td>
        <td>${Number(t.priceWithIva).toFixed(2)}</td>
        <td>${Number(t.stock)}</td>
        <td>${escapeHtml(t.description)}</td>
        <td>
          <button data-action="edit" data-id="${escapeHtml(t.id)}">Edit</button>
          <button data-action="delete" data-id="${escapeHtml(t.id)}">Delete</button>
        </td>
      </tr>
    `).join("");

  } catch (err) {
    console.error(err);
    setStatus("Network error loading tools.", true);
  }
}

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  setStatus("");

  const editingId = editingIdEl.value;

  const id = tidEl.value.trim();
  const name = nameEl.value.trim();
  const price = parseLocaleNumber(priceEl.value);
  const ivaRate = parseLocaleNumber(ivaRateEl.value);
  const stock = Number(stockEl.value);
  const description = descEl.value.trim();

  if (!id || !name || !description || Number.isNaN(price) || price < 0 || Number.isNaN(ivaRate) || ivaRate < 0 || Number.isNaN(stock) || stock < 0) {
    setStatus("Invalid data. Check fields.", true);
    return;
  }

  const payload = { id, name, price, ivaRate, stock, description };

  try {
    const url = editingId ? `/api/tools/${editingId}` : "/api/tools";
    const method = editingId ? "PUT" : "POST";

    const res = await fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    });

    const data = await res.json();

    if (!res.ok) {
      setStatus(data?.error || "Error saving tool.", true);
      return;
    }

    setStatus(editingId ? "Tool updated " : "Tool created ");
    setCreateMode();
    await loadTools();
  } catch (err) {
    console.error(err);
    setStatus("Network error.", true);
  }
});

cancelEditBtn.addEventListener("click", () => {
  setCreateMode();
});

tbody.addEventListener("click", async (e) => {
  const btn = e.target.closest("button");
  if (!btn) return;

  const action = btn.dataset.action;
  const id = btn.dataset.id;

  if (action === "edit") {
    try {
      const res = await fetch(`/api/tools/${id}`); 
      const tool = await res.json();

      if (!res.ok) {
        setStatus(tool?.error || "Error loading tool.", true);
        return;
      }

      setEditMode(tool);
      window.scrollTo({ top: 0, behavior: "smooth" });
    } catch (err) {
      console.error(err);
      setStatus("Network error.", true);
    }
  }

  if (action === "delete") {
    if (!confirm("Are you sure you want to delete this tool?")) return;

    try {
      const res = await fetch(`/api/tools/${id}`, { method: "DELETE" }); 
      const data = await res.json();

      if (!res.ok) {
        setStatus(data?.error || "Error deleting tool.", true);
        return;
      }

      setStatus("Tool deleted ");
      await loadTools();
    } catch (err) {
      console.error(err);
      setStatus("Network error.", true);
    }
  }
});

searchBtn.addEventListener("click", async () => {
  const id = tidEl.value.trim();
  if (!id) {
    setStatus("Enter a tool ID to search.", true);
    return;
  }

  try {
    const res = await fetch(`/api/tools/${id}`); 
    const tool = await res.json();

    if (!res.ok) {
      setStatus(tool?.error || "Tool not found.", true);
      return;
    }

    setEditMode(tool);
    setStatus("Tool loaded");
  } catch (err) {
    console.error(err);
    setStatus("Network error.", true);
  }
});

reloadBtn.addEventListener("click", loadTools);
setCreateMode();
loadTools();