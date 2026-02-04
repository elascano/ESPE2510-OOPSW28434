const express = require("express");
const path = require("path");

const ToolController = require("./controller/ToolController");
const CsvPersistence = require("./utils/CsvPersistence");
const JsonPersistence = require("./utils/JsonPersistence");
const MongoPersistence = require("./utils/MongoPersistence");
const MongoConnection = require("./utils/MongoConnection");
const ViewServer = require("./view/ViewServer");

class Main {
  static async main() {
    const app = express();
    app.use(express.json());

    let strategyKey = "mongo";
    let strategy = new MongoPersistence();
    const controller = new ToolController(strategy);

    const setStrategy = (key) => {
      strategyKey = key;
      if (key === "csv") strategy = new CsvPersistence();
      else if (key === "json") strategy = new JsonPersistence();
      else strategy = new MongoPersistence();
      controller.setStrategy(strategy);
    };

    app.get("/api/strategy", (req, res) => {
      res.json({ strategy: strategyKey });
    });

    app.post("/api/strategy", (req, res) => {
      const key = String(req.body?.strategy || "").toLowerCase();
      if (!["mongo","csv","json"].includes(key)) return res.status(400).json({ ok:false, message:"Strategy not valid" });
      setStrategy(key);
      res.json({ ok:true });
    });

    app.get("/api/tools", async (req, res) => {
      try {
        const tools = await controller.getAllTools();
        res.json(tools.map(t => ({
          id: t.getId(),
          name: t.getName(),
          price: t.getPrice(),
          materials: t.getMaterials(),
          priceWithIva: t.getPriceWithIva()
        })));
      } catch (e) {
        res.status(500).json({ ok:false, message:e.message });
      }
    });

    app.get("/api/tools/:id", async (req, res) => {
      try {
        const t = await controller.findSculptureById(req.params.id);
        if (!t) return res.status(404).json({ ok:false, message:"Not found" });
        res.json({ ok:true, tool:{
          id: t.getId(),
          name: t.getName(),
          price: t.getPrice(),
          materials: t.getMaterials(),
          priceWithIva: t.getPriceWithIva()
        }});
      } catch (e) {
        res.status(500).json({ ok:false, message:e.message });
      }
    });

    app.post("/api/tools", async (req, res) => {
      try {
        const id = String(req.body?.id || "").trim();
        const name = String(req.body?.name || "").trim();
        const price = Number(req.body?.price);
        const materials = Array.isArray(req.body?.materials) ? req.body.materials.map(x => String(x).trim()).filter(Boolean) : [];
        if (!id || !name || !Number.isFinite(price)) return res.status(400).json({ ok:false, message:"invalide data" });
        const ok = await controller.createSculpture(id, name, price, materials);
        if (!ok) return res.status(409).json({ ok:false, message:"Cant not create" });
        res.json({ ok:true });
      } catch (e) {
        res.status(500).json({ ok:false, message:e.message });
      }
    });

    app.put("/api/tools/:id", async (req, res) => {
      try {
        const id = req.params.id;
        const name = String(req.body?.name || "").trim();
        const price = Number(req.body?.price);
        const materials = Array.isArray(req.body?.materials) ? req.body.materials.map(x => String(x).trim()).filter(Boolean) : [];
        if (!id || !name || !Number.isFinite(price)) return res.status(400).json({ ok:false, message:"invalid data" });
        const ok = await controller.updateSculpture(id, name, price, materials);
        if (!ok) return res.status(404).json({ ok:false, message:"Cant not update" });
        res.json({ ok:true });
      } catch (e) {
        res.status(500).json({ ok:false, message:e.message });
      }
    });

    app.delete("/api/tools/:id", async (req, res) => {
      try {
        const ok = await controller.deleteSculpture(req.params.id);
        if (!ok) return res.status(404).json({ ok:false, message:"Cant not delete" });
        res.json({ ok:true });
      } catch (e) {
        res.status(500).json({ ok:false, message:e.message });
      }
    });

    const viewDir = path.join(__dirname, "view");
    new ViewServer(app).mount(viewDir);

    const port = Number(process.env.PORT || 3000);
    const server = app.listen(port, () => {});

    const shutdown = async () => {
      server.close(() => {});
      try { await MongoConnection.getInstance().close(); } catch {}
      process.exit(0);
    };

    process.on("SIGINT", shutdown);
    process.on("SIGTERM", shutdown);
  }
}

if (require.main === module) Main.main();

module.exports = Main;
