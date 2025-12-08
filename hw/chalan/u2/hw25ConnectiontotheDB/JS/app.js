const { MongoClient } = require("mongodb");
const { differenceInYears } = require("date-fns");
const readline = require("readline");

// ------------------ MongoDB Connection ------------------
async function connectMongo() {
    try {
        const uri = "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/";
        const client = new MongoClient(uri);

        await client.connect();

        console.log("✅ Conectado a MongoDB (Node)");

        const db = client.db("ContactsDB");
        return db.collection("contacts");

    } catch (error) {
        console.log("❌ Error conectando:", error);
        return null;
    }
}

// ------------------ CLI User Interface ------------------
async function app() {
    const collection = await connectMongo();
    if (!collection) return;

    console.log("\n===============================");
    console.log("        CONTACTS APP");
    console.log("===============================\n");

    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });

    function ask(question) {
        return new Promise(resolve => rl.question(question, resolve));
    }

    const firstName = await ask("First Name: ");
    const lastName = await ask("Last Name: ");

    console.log("\nFecha de nacimiento (formato yyyy-mm-dd)");
    const birthDate = await ask("Birth Date: ");

    // Calcular edad
    let age = 0;
    try {
        age = differenceInYears(new Date(), new Date(birthDate));
    } catch {
        console.log("⚠ Error calculando edad, se usa 0");
    }

    console.log(`Edad calculada: ${age} años`);

    console.log("\nTypes: [Family, Friends, Job, Unknown]");
    const type = await ask("Type: ");

    console.log("\nSex:\n1) Male\n2) Female");
    const sexOption = await ask("Seleccione sexo (1/2): ");
    const sex = sexOption === "1" ? "Male" : "Female";

    console.log("\nHobbies:");
    const hobbiesList = [
        "Play soccer",
        "Djng",
        "Read",
        "Cook",
        "Swim",
        "Sing",
        "Play an instrument"
    ];

    hobbiesList.forEach((h, i) => {
        console.log(`${i + 1}) ${h}`);
    });

    const hobbiesInput = await ask("\nSeleccione hobbies (ej: 1,3,4): ");

    const selectedIndexes = hobbiesInput.split(",").map(n => parseInt(n.trim()) - 1);
    const hobbies = selectedIndexes.map(i => hobbiesList[i]).filter(h => h);

    console.log("\nComments:");
    const comments = await ask("> ");

    const doc = {
        firstName,
        lastName,
        birthDate,
        age,
        type,
        sex,
        hobbies,
        comments
    };

    await collection.insertOne(doc);

    console.log("\n==========================================");
    console.log("   ✔ CONTACTO GUARDADO EN MONGODB ✔");
    console.log("==========================================\n");

    rl.close();
}

app();
