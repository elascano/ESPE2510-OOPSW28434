export class HenHouse {
  constructor(id, name) {
    this.id = id;
    this.name = name;
    this.chickens = [];
  }

  addChicken(chicken) {
    if (this.chickens.some(c => c.id === chicken.id)) {
      console.log(`⚠️ Ya existe una gallina con ID ${chicken.id}`);
      return;
    }
    this.chickens.push(chicken);
    console.log(`🐔 Gallina "${chicken.name}" agregada al gallinero "${this.name}".`);
  }

  showChickens() {
    console.log(`\n=== Gallinero "${this.name}" ===`);
    if (this.chickens.length === 0) {
      console.log("No hay gallinas registradas.");
      return;
    }

    this.chickens.forEach(ch =>
      console.log(`- ${ch.name} (${ch.featherColor}, ${ch.age} años, molting: ${ch.molting})`)
    );
  }

  toJSON() {
    return {
      id: this.id,
      name: this.name,
      chickens: this.chickens.map(ch => ch.toJSON())
    };
  }
}

