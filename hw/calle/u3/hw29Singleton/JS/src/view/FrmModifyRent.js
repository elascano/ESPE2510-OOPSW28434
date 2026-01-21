import { RentalManager } from '../utils/RentalManager.js';

export const FrmModifyRent = {
    render: () => `
        <div class="frame">
            <h1 class="perpetua-title">Modify Rent</h1>
            <label>Change Rent:</label>
            <input type="number" id="txtValue" step="0.01">
            <button id="btnUpdate">Update</button>
            <button onclick="window.router.navigate('menu')">Return to menu</button>
        </div>
    `,
    init: () => {
        document.getElementById('btnUpdate').onclick = () => {
            const val = parseFloat(document.getElementById('txtValue').value);
            if (RentalManager.getInstance().updateMonthlyRent(val)) {
                alert("Rent updated successfully!");
            } else {
                alert("Error: Enter a valid value greater than 0");
            }
        };
    }
};