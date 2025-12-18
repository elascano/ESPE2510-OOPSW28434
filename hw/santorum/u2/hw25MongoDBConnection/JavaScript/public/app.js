async function saveContact() {
    const data = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        age: parseInt(document.getElementById("age").value),
        type: document.getElementById("type").value,
        sex: document.getElementById("sex").value,
        hobbies: document.getElementById("hobbies").value.split(",").map(h => h.trim()),
        comments: document.getElementById("comments").value
    };

    const res = await fetch("/api/saveContact", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    const response = await res.json();
    alert(response.message);
}
