import express from "express";
import path from "path";
import { fileURLToPath } from "url";
import { Config } from "./utils/Config.js";
import { GenericController } from "./controller/ProductController.js";

const app = express();
const controller = new GenericController();

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

app.use(express.json());
app.use(express.static(path.join(__dirname, "view")));

app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "view", "FrmMainMenu.html"));
});

app.get("/api/entities", async (req, res) => {
    res.json(await controller.getAll());
});

app.get("/api/entities/:id", async (req, res) => {
    res.json(await controller.getById(req.params.id));
});

app.post("/api/entities", async (req, res) => {
    res.json(await controller.create(req.body));
});

app.put("/api/entities/:id", async (req, res) => {
    res.json(await controller.update(req.params.id, req.body));
});

app.delete("/api/entities/:id", async (req, res) => {
    res.json(await controller.delete(req.params.id));
});

app.listen(Config.port, () => {
    console.log(`Server running on http://localhost:${Config.port}`);
});

app.get("/api/nextId", async (req, res) => {
    const nextId = await controller.getNextId();
    res.send(nextId.toString());
});