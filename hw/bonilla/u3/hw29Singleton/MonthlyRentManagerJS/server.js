import express from "express";
import path from "path";
import RentController from "./controller/RentController.js";
import RentalManager from "./utils/RentalManager.js";

const app = express();
const controller = new RentController();

app.use(express.json());
app.use(express.static("view"));

app.get("/", (req, res) => {
    res.sendFile(path.resolve("view/frm_menu.html"));
});

app.get("/pay-rent", (req, res) => {
    res.sendFile(path.resolve("view/frm_pay_rent.html"));
});

app.get("/modify-rent", (req, res) => {
    res.sendFile(path.resolve("view/frm_modify_rent.html"));
});

app.get("/calculate", (req, res) => {
    const { id, name, months } = req.query;
    const total = controller.getTotalToPay(id, name, parseInt(months));
    res.json({ total });
});

app.post("/update-rent", (req, res) => {
    const { rent } = req.body;
    RentalManager.getInstance().updateMonthlyRent(parseFloat(rent));
    res.sendStatus(200);
});

app.listen(3000, () => {
    console.log("Server running at http://localhost:3000");
});