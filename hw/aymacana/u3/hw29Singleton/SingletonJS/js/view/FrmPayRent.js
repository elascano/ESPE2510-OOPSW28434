// js/view/FrmPayRent.js
class FrmPayRent {
    constructor() {
        this.controller = new RentController();
        
        this.residentId = 1756055065;
        this.residentName = "John Doe";
        
        this.selectedMonths = 0;
        this.totalAmount = 0;
        this.monthlyRent = 0;
        
        this.elements = {};
    
        this.loadMonthlyRent();
        
        window.addEventListener('rentUpdated', (event) => {
            this.monthlyRent = event.detail.newRent;
            this.updateUI();
        });
    }
    
    loadMonthlyRent() {
        try {
            const rentalManager = RentalManager.getInstance();
            this.monthlyRent = rentalManager.getMonthlyRent();
        } catch (error) {
            console.error("Error cargando renta:", error);
            this.monthlyRent = 20.0;
        }
    }
    
    render() {
        const appContainer = document.getElementById('app');
        
        appContainer.innerHTML = `
            <div class="header">
                <h1><i class="fas fa-money-check-alt"></i> PAY RENT</h1>
            </div>
            
            <div class="form-group">
                <label>Resident ID:</label>
                <div class="info-display">
                    <span class="info-label">ID</span>
                    <span class="info-value" id="residentId">${this.residentId}</span>
                </div>
            </div>
            
            <div class="form-group">
                <label>Select Months (1-12):</label>
                <div class="select-wrapper">
                    <select id="monthSelector">
                        <option value="0">Select months...</option>
                        ${Array.from({length: 12}, (_, i) => i + 1)
                            .map(num => `<option value="${num}">${num} month${num > 1 ? 's' : ''}</option>`)
                            .join('')}
                    </select>
                </div>
            </div>
            
            <div class="form-group">
                <label>Monthly Rent:</label>
                <div class="info-display">
                    <span class="info-label">Value</span>
                    <span class="info-value rent" id="monthlyRent">$${this.monthlyRent.toFixed(2)}</span>
                </div>
            </div>
            
            <div class="form-group">
                <label>Total to Pay:</label>
                <div class="info-display">
                    <span class="info-label">Total</span>
                    <span class="info-value total" id="totalAmount">$0.00</span>
                </div>
            </div>
            
            <div class="button-group">
                <button class="btn btn-pay" id="payButton" disabled>
                    <i class="fas fa-credit-card"></i> PAY NOW
                </button>
                <button class="btn btn-exit" id="exitButton">
                    <i class="fas fa-sign-out-alt"></i> EXIT
                </button>
            </div>
            
            <!-- Modal para modificar renta (oculto por defecto) -->
            <div class="modal-overlay" id="modifyModal">
                <div class="modal">
                    <div class="modal-header">
                        <h3><i class="fas fa-edit"></i> Modify Rent Value</h3>
                    </div>
                    <div class="input-group">
                        <label>Current Monthly Rent:</label>
                        <div class="info-display" style="margin-bottom: 15px;">
                            <span class="info-value rent">$${this.monthlyRent.toFixed(2)}</span>
                        </div>
                        <label for="newRentInput">New Monthly Rent:</label>
                        <input type="number" 
                               id="newRentInput" 
                               step="0.01" 
                               min="0.01" 
                               value="${this.monthlyRent}"
                               placeholder="Enter new rent value">
                    </div>
                    <div class="modal-buttons">
                        <button class="btn-modal btn-cancel" id="cancelButton">
                            <i class="fas fa-times"></i> Cancel
                        </button>
                        <button class="btn-modal btn-update" id="updateButton">
                            <i class="fas fa-check"></i> Update
                        </button>
                    </div>
                </div>
            </div>
            
            <!-- Botón flotante para modificar renta -->
            <button class="btn-modify" id="modifyButton" style="
                position: fixed;
                bottom: 20px;
                right: 20px;
                width: 60px;
                height: 60px;
                border-radius: 50%;
                border: none;
                background: #f39c12;
                color: white;
                font-size: 24px;
                cursor: pointer;
                box-shadow: 0 4px 10px rgba(0,0,0,0.2);
                transition: all 0.3s ease;
                z-index: 100;
            ">
                <i class="fas fa-cog"></i>
            </button>
        `;
        
        this.elements = {
            monthSelector: document.getElementById('monthSelector'),
            payButton: document.getElementById('payButton'),
            exitButton: document.getElementById('exitButton'),
            modifyButton: document.getElementById('modifyButton'),
            modifyModal: document.getElementById('modifyModal'),
            newRentInput: document.getElementById('newRentInput'),
            cancelButton: document.getElementById('cancelButton'),
            updateButton: document.getElementById('updateButton'),
            residentId: document.getElementById('residentId'),
            monthlyRent: document.getElementById('monthlyRent'),
            totalAmount: document.getElementById('totalAmount')
        };

        this.setupEventListeners();
    }
    
    setupEventListeners() {
        this.elements.monthSelector.addEventListener('change', (e) => {
            this.selectedMonths = parseInt(e.target.value);
            this.onMonthsSelected();
        });
        
        this.elements.payButton.addEventListener('click', () => {
            this.processPayment();
        });
        
        this.elements.exitButton.addEventListener('click', () => {
            if (confirm("Are you sure you want to exit?")) {
                alert("Application would close here. In browser, we just show this message.");
            }
        });
        
        this.elements.modifyButton.addEventListener('click', () => {
            this.showModifyModal();
        });
        
        this.elements.cancelButton.addEventListener('click', () => {
            this.hideModifyModal();
        });
        
        this.elements.updateButton.addEventListener('click', () => {
            this.updateMonthlyRent();
        });
        
        this.elements.modifyModal.addEventListener('click', (e) => {
            if (e.target === this.elements.modifyModal) {
                this.hideModifyModal();
            }
        });
        
        this.elements.newRentInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                this.updateMonthlyRent();
            }
        });
    }
    
    onMonthsSelected() {
        if (this.selectedMonths > 0) {
            try {
                this.totalAmount = this.controller.getTotalToPay(
                    this.residentId,
                    this.residentName,
                    this.selectedMonths
                );
                
                this.elements.totalAmount.textContent = `$${this.totalAmount.toFixed(2)}`;
                this.elements.payButton.disabled = false;
                
            } catch (error) {
                alert(`Error: ${error.message}`);
                this.elements.payButton.disabled = true;
                this.elements.totalAmount.textContent = "$0.00";
            }
        } else {
            this.elements.payButton.disabled = true;
            this.elements.totalAmount.textContent = "$0.00";
        }
    }
    
    processPayment() {
        if (this.selectedMonths === 0) {
            alert("Please select the number of months first");
            return;
        }
        
        const confirmation = confirm(
            `Confirm payment of $${this.totalAmount.toFixed(2)} for ${this.selectedMonths} month(s)?`
        );
        
        if (confirmation) {
            const result = this.controller.processPayment(
                this.residentId,
                this.residentName,
                this.selectedMonths
            );
            
            if (result.success) {
                alert(`Payment successful!\n\nResident ID: ${this.residentId}\nMonths: ${this.selectedMonths}\nAmount: $${this.totalAmount.toFixed(2)}`);
                
                this.resetForm();
            } else {
                alert(`Payment failed: ${result.message}`);
            }
        }
    }
    
    showModifyModal() {
        this.elements.newRentInput.value = this.monthlyRent.toFixed(2);
        this.elements.modifyModal.classList.add('active');
        this.elements.newRentInput.focus();
        this.elements.newRentInput.select();
    }
    
    hideModifyModal() {
        this.elements.modifyModal.classList.remove('active');
    }
    
    updateMonthlyRent() {
        try {
            const newRent = parseFloat(this.elements.newRentInput.value);
            
            if (isNaN(newRent) || newRent <= 0) {
                alert("Please enter a valid number greater than 0");
                return;
            }
            
            const rentalManager = RentalManager.getInstance();
            rentalManager.updateMonthlyRent(newRent);
            
            this.monthlyRent = newRent;
            this.updateUI();
            this.hideModifyModal();
            
            alert(`Rent value updated to $${newRent.toFixed(2)}`);
            
        } catch (error) {
            alert(`Error: ${error.message}`);
        }
    }
    
    updateUI() {
        this.elements.monthlyRent.textContent = `$${this.monthlyRent.toFixed(2)}`;
        
        if (this.selectedMonths > 0) {
            this.onMonthsSelected();
        }
    }
    
    resetForm() {
        this.selectedMonths = 0;
        this.totalAmount = 0;
        this.elements.monthSelector.value = "0";
        this.elements.totalAmount.textContent = "$0.00";
        this.elements.payButton.disabled = true;
    }
}

window.FrmPayRent = FrmPayRent;