import Event from "../model/Event.js";
import Discount from "../model/Discount.js";

class EventController {

    constructor(view) {
        this.view = view;
        this.discount = new Discount();
    }

    changeDiscount(value) {
        this.discount.update(value);
        this.view.showMessage("Descuento actualizado correctamente");
    }

    calculateEventPrice(name, price) {
        const event = new Event(name, price);
        const finalPrice = this.discount.apply(event.price);
        this.view.showEvent(event, finalPrice, this.discount.getPercentage());
    }
}

export default EventController;
