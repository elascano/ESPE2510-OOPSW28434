import { ChickenModel } from './models/Chicken.js';
import { ChickenController } from './controllers/chickenController.js';
import { ChickenView } from './views/chickenView.js';

const model = new ChickenModel();
const controller = new ChickenController(model);
const view = new ChickenView(controller);

view.run();
