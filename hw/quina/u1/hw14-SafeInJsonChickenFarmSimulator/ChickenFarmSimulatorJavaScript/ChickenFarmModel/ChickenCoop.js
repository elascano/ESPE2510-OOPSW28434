class ChickenCoop {
    constructor(coop_id, name) {
        this.id = coop_id;
        this.name = name;
        this.chickens = [];
    }

    add_chicken(chicken, silent = false) {
        this.chickens.push(chicken);
        if (!silent) {
            console.log(`Chicken ${chicken.name} added to ${this.name}.`);
        }
    }

    remove_chicken(name) {
        const initialLength = this.chickens.length;
        this.chickens = this.chickens.filter(c => c.name.toLowerCase() !== name.toLowerCase());
        
        if (this.chickens.length < initialLength) {
            console.log(`Chicken ${name} has been removed.`);
            return true;
        } else {
            return false;
        }
    }

    update_chicken(name, new_color, new_age, new_molting) {
        const chicken = this.get_chicken_by_name(name);
        if (chicken) {
            if (new_color !== null) chicken.color = new_color;
            if (new_age !== null && !isNaN(new_age)) chicken.age = new_age; 
            if (new_molting !== null) chicken.is_molting = new_molting;
            console.log(`Chicken ${chicken.name} updated successfully.`);
            return true;
        }
        return false;
    }

    show_coop() {
        console.log(`\n--- ${this.name} (ID: ${this.id}) ---`);
        
        if (this.chickens.length === 0) {
            console.log("No chickens in this coop yet.");
        } else {
            const header = (id, name, color, age, molting) => 
                `${String(id).padEnd(5)} ${name.padEnd(10)} ${color.padEnd(15)} ${String(age).padEnd(5)} ${molting.padEnd(10)}`;

            console.log(header("ID", "NAME", "COLOR", "AGE", "MOLTING"));
            console.log("-".repeat(45));
            
            this.chickens.forEach(chicken => {
                const molting = chicken.is_molting ? "Yes" : "No";
                console.log(header(
                    chicken.id, 
                    chicken.name, 
                    chicken.color, 
                    chicken.age, 
                    molting
                ));
            });
        }
    }

    get_chicken_by_name(name) {
        return this.chickens.find(c => c.name.toLowerCase() === name.toLowerCase()) || null;
    }

    to_dict() {
        return {
            id: this.id,
            name: this.name,
            chickens: this.chickens.map(c => c.to_dict())
        };
    }
}

module.exports = ChickenCoop;