const express = require("express");
const ContactController = require("../controllers/ContactController");

const router = express.Router();

router.post("/", ContactController.create);
router.get("/", ContactController.getAll);
router.put("/:id", ContactController.update);
router.delete("/:id", ContactController.delete);

module.exports = router;