class FrmTool {
  constructor() {
    this.strategySelect = document.getElementById("strategy");
    this.idInput = document.getElementById("id");
    this.nameInput = document.getElementById("name");
    this.priceInput = document.getElementById("price");
    this.materialsInput = document.getElementById("materials");
    this.statusEl = document.getElementById("status");
    this.subtitleEl = document.getElementById("subtitle");
    this.tbody = document.getElementById("tbody");

    document.getElementById("btnCreate").addEventListener("click", () => this.create());
    document.getElementById("btnUpdate").addEventListener("click", () => this.update());
    document.getElementById("btnDelete").addEventListener("click", () => this.delete());
    document.getElementById("btnFind").addEventListener("click", () => this.find());
    document.getElementById("btnRefresh").addEventListener("click", () => this.refresh());
    document.getElementById("btnClear").addEventListener("click", () => this.clear());

    this.strategySelect.addEventListener("change", () => this.changeStrategy());

    this.init();
  }

  async init() {
    const { strategy } = await this.api("GET", "/api/strategy");
    this.strategySelect.value = strategy;
    await this.changeStrategy(true);
    await this.refresh();
  }

  async changeStrategy(skipMessage) {
    const strategy = this.strategySelect.value;
    await this.api("POST", "/api/strategy", { strategy });
    this.subtitleEl.textContent = `Strategy: ${strategy === "mongo" ? "MongoPersistence" : strategy === "csv" ? "CsvPersistence" : "JsonPersistence"}`;
    if (!skipMessage) this.status(`Strategy changed`);
    await this.refresh();
  }

  readForm() {
    const id = this.idInput.value.trim();
    const name = this.nameInput.value.trim();
    const price = Number(this.priceInput.value);
    const materials = this.materialsInput.value.trim()
      ? this.materialsInput.value.split(",").map(s => s.trim()).filter(Boolean)
      : [];
    return { id, name, price, materials };
  }

  status(msg) {
    this.statusEl.textContent = msg || "";
  }

  renderTools(tools) {
    this.tbody.innerHTML = "";
    for (const t of tools) {
      const tr = document.createElement("tr");
      tr.innerHTML = `
        <td>${this.esc(t.id)}</td>
        <td>${this.esc(t.name)}</td>
        <td>${Number(t.price).toFixed(2)}</td>
        <td>${Number(t.priceWithIva).toFixed(2)}</td>
        <td>${this.esc((t.materials || []).join(", "))}</td>
      `;
      tr.addEventListener("click", () => {
        this.idInput.value = t.id;
        this.nameInput.value = t.name;
        this.priceInput.value = t.price;
        this.materialsInput.value = (t.materials || []).join(", ");
      });
      this.tbody.appendChild(tr);
    }
  }

  esc(s) {
    return String(s ?? "").replace(/[&<>"']/g, c => ({ "&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;" }[c]));
  }

  async refresh() {
    const tools = await this.api("GET", "/api/tools");
    this.renderTools(tools);
    this.status(`Total: ${tools.length}`);
  }

  async create() {
    const { id, name, price, materials } = this.readForm();
    if (!id || !name || !Number.isFinite(price)) { this.status("Complete ID, Name and Price"); return; }
    const res = await this.api("POST", "/api/tools", { id, name, price, materials });
    this.status(res.ok ? "Created" : res.message || "Cant not create");
    await this.refresh();
  }

  async update() {
    const { id, name, price, materials } = this.readForm();
    if (!id || !name || !Number.isFinite(price)) { this.status("Complete ID, Name and Price"); return; }
    const res = await this.api("PUT", `/api/tools/${encodeURIComponent(id)}`, { name, price, materials });
    this.status(res.ok ? "Updated" : res.message || "Failed to update");
    await this.refresh();
  }

  async delete() {
    const id = this.idInput.value.trim();
    if (!id) { this.status("Input ID"); return; }
    const res = await this.api("DELETE", `/api/tools/${encodeURIComponent(id)}`);
    this.status(res.ok ? "Deleted" : res.message || "Cant not delete");
    await this.refresh();
  }

  async find() {
    const id = this.idInput.value.trim();
    if (!id) { this.status("Input ID"); return; }
    const res = await this.api("GET", `/api/tools/${encodeURIComponent(id)}`);
    if (!res.ok) { this.status(res.message || "Not found"); return; }
    const t = res.tool;
    this.nameInput.value = t.name;
    this.priceInput.value = t.price;
    this.materialsInput.value = (t.materials || []).join(", ");
    this.status("Found");
  }

  clear() {
    this.idInput.value = "";
    this.nameInput.value = "";
    this.priceInput.value = "";
    this.materialsInput.value = "";
    this.status("");
  }

  async api(method, url, body) {
    const opts = { method, headers: { "Content-Type": "application/json" } };
    if (body !== undefined) opts.body = JSON.stringify(body);
    const r = await fetch(url, opts);
    const t = await r.text();
    return t ? JSON.parse(t) : null;
  }
}

window.addEventListener("DOMContentLoaded", () => new FrmTool());
