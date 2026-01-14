const ToolRepository = require("../model/toolRepository");
const ToolService = require("../model/toolService");

class ToolController {
  constructor() {
    this.repo = new ToolRepository();
    this.service = new ToolService();

    this.create = this.create.bind(this);
    this.getAll = this.getAll.bind(this);
    this.getById = this.getById.bind(this);
    this.update = this.update.bind(this);
    this.remove = this.remove.bind(this);
  }
  async create(req, res) {
    try {
      const validationError = this.service.validatePayload(req.body);
      if (validationError) return res.status(400).json({ error: validationError });

      const toolToSave = this.service.buildPersistedTool(req.body);

      const exists = await this.repo.findByBusinessId(toolToSave.id);
      if (exists) return res.status(409).json({ error: "Tool ID already exists." });

      const saved = await this.repo.create(toolToSave);
      return res.status(201).json(saved);
    } catch (err) {
      console.error("CREATE ERROR:", err);
      return res.status(500).json({ error: "Internal server error." });
    }
  }

  async getAll(req, res) {
    try {
      const tools = await this.repo.findAll();
      return res.json(tools);
    } catch (err) {
      console.error("GET ALL ERROR:", err);
      return res.status(500).json({ error: "Internal server error." });
    }
  }

  async getById(req, res) {
    try {
      const id = String(req.params.id).trim();
      const tool = await this.repo.findByBusinessId(id);

      if (!tool) return res.status(404).json({ error: "Tool not found." });
      return res.json(tool);
    } catch (err) {
      console.error("GET BY ID ERROR:", err);
      return res.status(500).json({ error: "Internal server error." });
    }
  }

  async update(req, res) {
    try {
      const idFromUrl = String(req.params.id).trim();

      const validationError = this.service.validatePayload(req.body);
      if (validationError) return res.status(400).json({ error: validationError });

      const payload = { ...req.body, id: idFromUrl };
      const toolToSave = this.service.buildPersistedTool(payload);
      const updated = await this.repo.updateByBusinessId(idFromUrl, toolToSave);

      if (!updated) return res.status(404).json({ error: "Tool not found." });
      return res.json(updated);
    } catch (err) {
      console.error("UPDATE ERROR:", err);
      return res.status(500).json({ error: "Internal server error." });
    }
  }

  async remove(req, res) {
    try {
      const id = String(req.params.id).trim();
      const deleted = await this.repo.deleteByBusinessId(id);
      
      if (!deleted) return res.status(404).json({ error: "Tool not found." });
      return res.json({ ok: true, deletedId: id });
    } catch (err) {
      console.error("DELETE ERROR:", err);
      return res.status(500).json({ error: "Internal server error." });
    }
  }
}

module.exports = ToolController;