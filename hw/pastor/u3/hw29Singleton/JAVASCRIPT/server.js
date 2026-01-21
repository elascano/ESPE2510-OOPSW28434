import express from "express";
import { StockAlertController } from "./Controller/StockAlertController.js";

const app = express();
app.use(express.json());
app.use(express.static("View"));

const controller = new StockAlertController();
await controller.init();

app.get("/check-stock", async (req, res) => {
    const products = await controller.checkStock();
    res.json(products);
});

app.post("/update-minimum-stock", async (req, res) => {
    const { value } = req.body;
    await controller.updateMinimumStock(value);
    res.json({ ok: true });
});

app.listen(3000, () => {
    console.log("✅ Server running at http://localhost:3000");
});
