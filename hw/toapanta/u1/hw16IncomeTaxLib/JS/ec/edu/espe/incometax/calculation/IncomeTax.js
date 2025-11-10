const readline = require('readline');

class IncomeTax {
    
    static contributionIESS(monthlySalary, institutionalSector) {
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
        const annualExpenses = monthlyExpenses * 12;
        const deductibleExpenses = Math.min(annualExpenses, 5344);
        let baseForRebaja = 0;
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
        const taxWithoutDeductions = this.calculateTaxWithoutDeductions(annualNetSalaryBeforeTax);
        const rebate = this.calculateRebaja(annualNetSalaryBeforeTax, monthlyExpenses);
        const finalTax = taxWithoutDeductions - rebate;
        
        return Math.max(finalTax, 0);
    }

    static showTaxResults(monthlySalary, institutionalSector, monthlyExpenses) {
        const annualGrossSalary = monthlySalary * 12;
        const monthlyIESS = this.contributionIESS(monthlySalary, institutionalSector);
        const annualIESS = monthlyIESS * 12;
        const annualNetBeforeTax = annualGrossSalary - annualIESS;

        const incomeTax = this.calculateIncomeTax(annualNetBeforeTax, monthlyExpenses);
        const finalNetSalary = annualNetBeforeTax - incomeTax;

        console.log("INCOME TAX RESULTS");
        console.log(`YOUR ANNUAL INCOME TAX IS: $${incomeTax.toFixed(2)}`);
        console.log(`Your annual net salary is: $${finalNetSalary.toFixed(2)}`);
    }

    static getValidatedDouble(message) {
        const rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });

        return new Promise((resolve) => {
            const askValue = () => {
                rl.question(message, (input) => {
                    const value = parseFloat(input);

                    if (isNaN(value)) {
                        console.log("Enter a valid number.");
                        askValue();
                    } else if (value < 0) {
                        console.log("ERROR: Value cannot Be Negative.");
                        askValue();
                    } else if (value === 0) {
                        console.log("ERROR: Value cannot Be Zero.");
                        askValue();
                    } else if (value > 20000) {
                        console.log(`ERROR: exceed $20000`);
                        askValue();
                    } else {
                        rl.close();
                        resolve(value);
                    }
                });
            };
            askValue();
        });
    }

    static getValidatedSector() {
        const rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });

        return new Promise((resolve) => {
            const askSector = () => {
                rl.question("sector 1=Public, 2=Private: ", (input) => {
                    const sector = parseInt(input);

                    if (sector === 1 || sector === 2) {
                        rl.close();
                        resolve(sector);
                    } else {
                        console.log("must be 1 (Public) or 2 (Private).");
                        askSector();
                    }
                });
            };
            askSector();
        });
    }
}

module.exports = { IncomeTax };