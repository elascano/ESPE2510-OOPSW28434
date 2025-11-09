const { TaxModel } = require('./model/TaxModel');
const { TaxView } = require('./view/TaxView');

async function startApp() {
    const model = new TaxModel();
    const view = new TaxView();

    try {
        const data = await view.getTaxData();

        if (Object.values(data).some(isNaN)) {
            throw new Error("Please enter valid numeric values.");
        }

        const annualGrossIncome = data.monthlySalary * 12.0;
        
        const annualTaxableBase = model.calculateAnnualTaxableBase(
            data.monthlySalary
        );

        const annualTaxDue = model.calculateAnnualIncomeTax(
            annualTaxableBase
        );

        const taxRebateAmount = model.calculateTaxRebate(
            data.personalExpenses, 
            data.dependents, 
            annualGrossIncome
        );

        let finalAnnualTax = annualTaxDue - taxRebateAmount;
        if (finalAnnualTax < 0) {
            finalAnnualTax = 0.0;
        }

        const finalBalance = finalAnnualTax - data.annualWithholdings;

        view.displayTaxReport({
            ...data,
            annualGrossIncome,
            annualTaxableBase,
            annualTaxDue,
            taxRebateAmount,
            finalAnnualTax,
            finalBalance
        });

    } catch (error) {
        view.displayError(error.message);
    }
}

startApp();