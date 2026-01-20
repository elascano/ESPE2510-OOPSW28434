import ProductController from "./controller/ProductController.js";
import ConsoleView from "./view/ConsoleView.js";

const controller = new ProductController();
const view = new ConsoleView(controller);

view.start();

