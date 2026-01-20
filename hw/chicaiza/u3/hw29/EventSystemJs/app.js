import EventController from "./ec/edu/espe/eventsystem/controller/EventController.js";
import EventView from "./ec/edu/espe/eventsystem/view/EventView.js";

const view = new EventView();
const controller = new EventController(view);

view.controller = controller;
view.showMenu();