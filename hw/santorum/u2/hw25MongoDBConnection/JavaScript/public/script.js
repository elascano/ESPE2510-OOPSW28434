function calculateAge(birth) {
    const parts = birth.split("/");
    if (parts.length !== 3) return null;

    const day = parseInt(parts[0]);
    const month = parseInt(parts[1]);
    const year = parseInt(parts[2]);

    const birthDate = new Date(year, month - 1, day);
    const diff = Date.now() - birthDate.getTime();
    const age = new Date(diff).getUTCFullYear() - 1970;

    return age;
}

document.getElementById("birthDate").addEventListener("input", () => {
    const dateStr = document.getElementById("birthDate").value;
    const age = calculateAge(dateStr);
    document.getElementById("age").value = age || "";
});

async function saveContact() {
    const selectedHobbies = Array.from(
        document.getElementById("hobbies").selectedOptions
    ).map(opt => opt.value);

    const sex = document.querySelector('input[name="sex"]:checked').value;

    const contact = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        age: parseInt(document.getElementById("age").value),
        type: document.getElementById("type").value,
        sex: sex,
        hobbies: selectedHobbies,
        comments: document.getElementById("comments").value
    };

    console.log("ENVIANDO AL BACKEND:", contact);

    const response = await fetch("/api/saveContact", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(contact)
    });

    const result = await response.json();
    console.log("RESPUESTA DEL BACKEND:", result);
    alert("Guardado!");
}
