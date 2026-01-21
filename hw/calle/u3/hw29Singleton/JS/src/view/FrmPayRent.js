import { RentController } from '../controller/RentController.js';

export const FrmPayRent = {
    controller: new RentController(),
    residentId: 1756055065,
    residentName: "John Doe",

    render: () => `
        <div class="frame">
            <h1 class="perpetua-title">Pay Rent</h1>
            <p><strong>Resident ID:</strong> ${FrmPayRent.residentId}</p>
            <label>Select the Month:</label>
            <select id="cbxMonth">
                ${[...Array(13).keys()].map(i => `<option value="${i}">${i}</option>`).join('')}
            </select>
            <p>Value for rental: <span id="lblValueRental">$0.00</span></p>
            <button id="btnPay">Pay</button>
            <button onclick="window.router.navigate('menu')">Return to menu</button>
        </div>
    `,
    init: () => {
        const cbx = document.getElementById('cbxMonth');
        const lbl = document.getElementById('lblValueRental');
        
        cbx.onchange = () => {
            const total = FrmPayRent.controller.getTotalToPay(
                FrmPayRent.residentId, FrmPayRent.residentName, parseInt(cbx.value)
            );
            lbl.innerText = `$${total.toFixed(2)}`;
        };

        document.getElementById('btnPay').onclick = () => {
            if(cbx.value == "0") return alert("Select months");
            alert(`Payment Successful!\nTotal: ${lbl.innerText}`);
        };
    }
};