class ProfessorController {
    constructor(model, view) {
        this.model = model;
        this.view = view;
    }

    async handleSave() {
    const name = document.getElementById('ent_name').value;
    const idNum = document.getElementById('ent_id').value;
    const dept = document.getElementById('combo_dept').value;
    const salary = parseFloat(document.getElementById('ent_salary').value);

    if (!name || !idNum || !dept || isNaN(salary)) {
        alert("Please fill all fields correctly.");
        return;
    }

    const nameRegex = /^[a-zA-ZÀ-ÿ\s]+$/;
    if (!nameRegex.test(name)) {
        alert("Name only can contain letters and spaces.");
        return;
    }

    const idRegex = /^\d{1,5}$/;
        if (!idRegex.test(idNum)) {
            alert("ID must be an integer of up to 5 digits.");
            entId.focus();
            return;
        }

    if (salary < 0) {
        alert("Salary cannot be negative.");
        return;
    }

    try {
        await this.model.insertProfessor(name, idNum, dept, salary);
        alert("Professor saved successfully!");
        await this.refreshTable();
        this.clearFields(); 
    } catch (error) {
        console.error(error);
        alert("Database error. Please try again.");
    }
}

    async refreshTable() {
        const tbody = document.getElementById('table_body');
        tbody.innerHTML = '';
        const professors = await this.model.getAll();
        
        professors.forEach(p => {
            const row = `<tr>
                <td>${p.id_number}</td>
                <td>${p.name}</td>
                <td>${p.subject}</td>
                <td>$${p.bonus.toFixed(2)}</td>
            </tr>`;
            tbody.innerHTML += row;
        });
    }

    clearFields() {
        document.getElementById('ent_name').value = '';
        document.getElementById('ent_id').value = '';
        document.getElementById('ent_salary').value = '';
        document.getElementById('combo_dept').value = '';
    }
}

module.exports = ProfessorController;