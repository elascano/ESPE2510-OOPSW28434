const express = require("express");
const ToyController = require("../controller/ToyController");

const router = express.Router();
const controller = new ToyController();


router.get("/", async (req, res) => {
  const toys = await controller.getAllToys();
  res.json(toys);
});


router.get("/:id", async (req, res) => {
  const toy = await controller.getToyById(parseInt(req.params.id));
  res.json(toy || { message: "Toy not found" });
});


router.post("/", async (req, res) => {
  const toy = await controller.createToy(req.body);
  res.json(toy);
});


router.put("/", async (req, res) => {
  await controller.updateToy(req.body);
  res.json({ message: "Toy updated" });
});


router.delete("/:id", async (req, res) => {
  await controller.deleteToy(parseInt(req.params.id));
  res.json({ message: "Toy deleted" });
});

module.exports = router;
