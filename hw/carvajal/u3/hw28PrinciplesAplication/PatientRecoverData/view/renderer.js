const PatientModel = require('../model/patient');
const PatientController = require('../controller/patientController');

const model = new PatientModel();

// IMPORTANTE: 'PatientController' con P mayúscula, pero la variable 'controller' con minúscula
controller = new PatientController(model); 

controller.refreshTable();