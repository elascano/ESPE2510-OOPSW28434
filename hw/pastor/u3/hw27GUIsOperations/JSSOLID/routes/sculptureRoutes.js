//Simplemente conecta las URL con tu controlador.
const express = require('express');
const router = express.Router();
// Importamos el controlador nuevo
const controller = require('../controllers/sculptureController');

router.get('/', controller.getAllSculptures);
router.get('/:id', controller.getSculptureById);
router.post('/', controller.createSculpture);
router.put('/:id', controller.updateSculpture);
router.delete('/:id', controller.deleteSculpture);

module.exports = router;