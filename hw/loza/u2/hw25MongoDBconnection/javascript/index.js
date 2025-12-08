const fs = require("fs");
const readline = require("readline-sync");
const { MongoClient } = require("mongodb");


async function guardarEnMongo(student) {
    const uri = "mongodb+srv://Steven:Steven2001@cluster0.mp8muds.mongodb.net/?appName=Cluster0";

    const client = new MongoClient(uri);

    try {
        await client.connect();
        const db = client.db("escuela");
        const collection = db.collection("estudiantes");

        await collection.insertOne(student);

        console.log(" Estudiante guardado correctamente en MongoDB");
    } catch (error) {
        console.error(" Error conectando a MongoDB:", error);
    } finally {
        await client.close();
    }
}


function guardarEnJson(student, archivo = "students.json") {
    let data = [];

    if (fs.existsSync(archivo)) {
        const fileContent = fs.readFileSync(archivo);
        data = JSON.parse(fileContent);
    }

    data.push(student);

    fs.writeFileSync(archivo, JSON.stringify(data, null, 4));
    console.log("✔ Estudiante guardado en archivo JSON");
}


async function main() {
    console.log("=== Registro de Estudiantes ===");

    const nombre = readline.question("Nombre: ");
    const edad = readline.questionInt("Edad: ");
    const correo = readline.question("Correo: ");

    const student = {
        nombre,
        edad,
        correo
    };

    await guardarEnMongo(student);
    guardarEnJson(student);
}

main();
