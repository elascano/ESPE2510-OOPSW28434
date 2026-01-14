class ProfessorController {
    constructor(model) {
        this.model = model;
    }

    async refreshTable() {
        const tbody = document.getElementById('table_body');
        if (!tbody) return;
        
        tbody.innerHTML = '';
        const professors = await this.model.getAll();
        
        professors.forEach(p => {
            const salary = parseFloat(p.salary) || 0;
            const calculatedBonus = salary * 0.15;

            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${p.id_number}</td>
                <td>${p.name}</td>
                <td>${p.subject}</td>
                <td>$${salary.toFixed(2)}</td>
                <td style="color: blue; font-weight: bold;">$${calculatedBonus.toFixed(2)}</td>
            `;
            tbody.appendChild(row);
        });
    }
}
module.exports = ProfessorController;