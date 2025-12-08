const { MongoClient } = require("mongodb");

// ----------- MongoDB Connection -----------
async function connectMongo() {
    const uri = "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/";
    const client = new MongoClient(uri);

    await client.connect();
    console.log("Connected to MongoDB");

    return client.db("ContactsDB").collection("contacts");
}

let collection = null;

(async () => {
    collection = await connectMongo();
})();

// ----------- Calculate age -----------
function calcAge(dateText) {
    try {
        const birth = new Date(dateText);
        const today = new Date();

        let age = today.getFullYear() - birth.getFullYear();
        const m = today.getMonth() - birth.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) {
            age--;
        }
        return age;
    } catch {
        return 0;
    }
}

// ----------- Save Contact -----------
async function saveContact() {

    const firstName = document.getElementById("firstName").value;
    const lastName  = document.getElementById("lastName").value;
    const birthDate = document.getElementById("birthDate").value;
    const age       = calcAge(birthDate);
    document.getElementById("age").value = age;

    const type = document.getElementById("type").value;

    let sex = "";
    document.getElementsByName("sex").forEach(r => {
        if (r.checked) sex = r.value;
    });

    const hobbiesSelect = document.getElementById("hobbies");
    const hobbies = [...hobbiesSelect.options]
        .filter(o => o.selected)
        .map(o => o.value);

    const comments = document.getElementById("comments").value;

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

    alert("Contact saved in MongoDB!");
}
