
class IncomeTax {
    
    static contributionIESS(monthlySalary, institutionalSector) {
        this._validateSalary(monthlySalary);
        this._validateSector(institutionalSector);
        
        let baseCalculo;
        
        if (institutionalSector === 2 && monthlySalary > 2300) {
            baseCalculo = 2300;
        } else {
            baseCalculo = monthlySalary;
        }

        const generalInsurance = baseCalculo * 0.0945;
        const unemploymentInsurance = baseCalculo * 0.02;
        const totalContributionIESS = generalInsurance + unemploymentInsurance;

        return totalContributionIESS;
    }

    static calculateTaxWithoutDeductions(annualNetSalaryBeforeTax) {
        this._validatePositiveNumber(annualNetSalaryBeforeTax, "Annual net salary");
        
        let tax = 0;

        if (annualNetSalaryBeforeTax <= 11902) {
            tax = 0;
        } else if (annualNetSalaryBeforeTax <= 15159) {
            tax = (annualNetSalaryBeforeTax - 11902) * 0.05;
        } else if (annualNetSalaryBeforeTax <= 19682) {
            tax = 163 + (annualNetSalaryBeforeTax - 15159) * 0.10;
        } else if (annualNetSalaryBeforeTax <= 26031) {
            tax = 615 + (annualNetSalaryBeforeTax - 19682) * 0.12;
        } else if (annualNetSalaryBeforeTax <= 34255) {
            tax = 1377 + (annualNetSalaryBeforeTax - 26031) * 0.15;
        } else if (annualNetSalaryBeforeTax <= 45407) {
            tax = 2611 + (annualNetSalaryBeforeTax - 34255) * 0.20;
        } else if (annualNetSalaryBeforeTax <= 60450) {
            tax = 4841 + (annualNetSalaryBeforeTax - 45407) * 0.25;
        } else if (annualNetSalaryBeforeTax <= 80605) {
            tax = 8602 + (annualNetSalaryBeforeTax - 60450) * 0.30;
        } else if (annualNetSalaryBeforeTax <= 107199) {
            tax = 14648 + (annualNetSalaryBeforeTax - 80605) * 0.35;
        } else {
            tax = 23956 + (annualNetSalaryBeforeTax - 107199) * 0.37;
        }

        return tax;
    }
    
    static getMarginalTaxRate(income) {
        this._validatePositiveNumber(income, "Income");
        
        if (income <= 11902) {
            return 0.00;
        } else if (income <= 15159) {
            return 0.05;
        } else if (income <= 19682) {
            return 0.10;
        } else if (income <= 26031) {
            return 0.12;
        } else if (income <= 34255) {
            return 0.15;
        } else if (income <= 45407) {
            return 0.20;
        } else if (income <= 60450) {
            return 0.25;
        } else if (income <= 80605) {
            return 0.30;
        } else if (income <= 107199) {
            return 0.35;
        } else {
            return 0.37;
        }
    }

    static calculateRebaja(annualNetSalaryBeforeTax, monthlyExpenses) {
        this._validatePositiveNumber(annualNetSalaryBeforeTax, "Annual net salary");
        this._validateNonNegativeNumber(monthlyExpenses, "Monthly expenses");
        
        const annualExpenses = monthlyExpenses * 12;
        const deductibleExpenses = Math.min(annualExpenses, 5344);
        let baseForRebaja;
        const marginalRate = this.getMarginalTaxRate(annualNetSalaryBeforeTax);

        if (annualNetSalaryBeforeTax > 26031 && annualNetSalaryBeforeTax <= 34255) {
            baseForRebaja = 6423.53;
        } else {
            baseForRebaja = deductibleExpenses;
        }

        const rebate = baseForRebaja * marginalRate;
        return rebate;
    }

    static calculateIncomeTax(annualNetSalaryBeforeTax, monthlyExpenses) {
        this._validatePositiveNumber(annualNetSalaryBeforeTax, "Annual net salary");
        this._validateNonNegativeNumber(monthlyExpenses, "Monthly expenses");
        
        const taxWithoutDeductions = this.calculateTaxWithoutDeductions(annualNetSalaryBeforeTax);
        const rebate = this.calculateRebaja(annualNetSalaryBeforeTax, monthlyExpenses);
        const finalTax = taxWithoutDeductions - rebate;
        
        return Math.max(finalTax, 0);
    }

    static showTaxResults(monthlySalary, institutionalSector, monthlyExpenses) {
        this._validateSalary(monthlySalary);
        this._validateSector(institutionalSector);
        this._validateNonNegativeNumber(monthlyExpenses, "Monthly expenses");
        
        const annualGrossSalary = monthlySalary * 12;
        const monthlyIESS = this.contributionIESS(monthlySalary, institutionalSector);
        const annualIESS = monthlyIESS * 12;
        const annualNetBeforeTax = annualGrossSalary - annualIESS;

        const incomeTax = this.calculateIncomeTax(annualNetBeforeTax, monthlyExpenses);
        const finalNetSalary = annualNetBeforeTax - incomeTax;

        console.log("\n==========================================");
        console.log("         INCOME TAX RESULTS");
        console.log("==========================================");

        console.log(`YOUR ANNUAL INCOME TAX IS: $${incomeTax.toFixed(2)}`);
        console.log(`Your annual net salary is: $${finalNetSalary.toFixed(2)}`);
        console.log("==========================================");
    }

    
    static _validateSalary(salary) {
        if (typeof salary !== 'number' || isNaN(salary)) {
            throw new Error("Salary must be a valid number.");
        }
        if (salary <= 0) {
            throw new Error("Salary must be greater than 0.");
        }
        if (salary > 20000) {
            throw new Error("Salary cannot exceed $20,000.");
        }
    }

    static _validateSector(sector) {
        if (typeof sector !== 'number' || isNaN(sector)) {
            throw new Error("Sector must be a valid number.");
        }
        if (sector !== 1 && sector !== 2) {
            throw new Error("Sector must be 1 (Public) or 2 (Private).");
        }
    }

    static _validatePositiveNumber(value, fieldName) {
        if (typeof value !== 'number' || isNaN(value)) {
            throw new Error(`${fieldName} must be a valid number.`);
        }
        if (value < 0) {
            throw new Error(`${fieldName} cannot be negative.`);
        }
    }

    static _validateNonNegativeNumber(value, fieldName) {
        if (typeof value !== 'number' || isNaN(value)) {
            throw new Error(`${fieldName} must be a valid number.`);
        }
        if (value < 0) {
            throw new Error(`${fieldName} cannot be negative.`);
        }
    }
    
    static validateInputs(monthlySalary, monthlyExpenses, institutionalSector) {
        const errors = [];
        
        try {
            this._validateSalary(monthlySalary);
        } catch (error) {
            errors.push(error.message);
        }
        
        try {
            this._validateNonNegativeNumber(monthlyExpenses, "Monthly expenses");
        } catch (error) {
            errors.push(error.message);
        }
        
        try {
            this._validateSector(institutionalSector);
        } catch (error) {
            errors.push(error.message);
        }
        
        return {
            isValid: errors.length === 0,
            errors: errors
        };
    }
}

if (typeof module !== 'undefined' && module.exports) {
    module.exports = IncomeTax;
}