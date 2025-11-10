



package ec.edu.espe.taxoperations.model;

/**
 *
 * @author Daniel
 */

public class Tax {
    private float salary;
    private float expenses;

    public Tax(float salary, float expenses) {
        this.salary = salary;
        this.expenses = expenses;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getExpenses() {
        return expenses;
    }

    public void setExpenses(float expenses) {
        this.expenses = expenses;
    }

    /**
     * Computes the income tax based on a progressive scale.
     * @return tax amount to pay
     */
    public float computeTax() {
        float taxableIncome = salary - expenses;
        float tax = 0;

        if (taxableIncome <= 500) {
            tax = 0;
        } else if (taxableIncome <= 1000) {
            tax = (taxableIncome - 500) * 0.10f;
        } else if (taxableIncome <= 2000) {
            tax = (500 * 0.10f) + (taxableIncome - 1000) * 0.20f;
        } else {
            tax = (500 * 0.10f) + (1000 * 0.20f) + (taxableIncome - 2000) * 0.30f;
        }

        return tax;
    }
}
