document.addEventListener("DOMContentLoaded", () => {
  const saveBtn = document.getElementById("saveBtn");
  const viewBtn = document.getElementById("viewBtn");
  const listDiv = document.getElementById("contactsList");

  if (!saveBtn || !viewBtn || !listDiv) {
    console.error("Required DOM elements not found: saveBtn, viewBtn or contactsList");
    return;
  }

  // SAVE
  saveBtn.addEventListener("click", async () => {
    const firstName = document.getElementById("firstName").value.trim();
    const lastName = document.getElementById("lastName").value.trim();
    const birthDate = document.getElementById("birthDate").value;
    const typeOfContact = document.getElementById("typeOfContact").value;
    const sex = document.getElementById("sex").value;
    const hobbiesSelect = document.getElementById("hobbies");
    const hobbies = hobbiesSelect ? Array.from(hobbiesSelect.selectedOptions).map(o => o.value) : [];
    const comments = document.getElementById("comments").value.trim();

    if (!firstName || !lastName || !birthDate || !typeOfContact || !sex) {
      alert("Please fill all required fields.");
      return;
    }

    const contactData = { firstName, lastName, birthDate, typeOfContact, sex, hobbies, comments };

    try {
      console.log("Sending /add with:", contactData);
      const response = await fetch("/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(contactData)
      });
      const result = await response.json();
      console.log("/add result:", result);
      alert(result.message || "Saved");
    } catch (err) {
      console.error("Save error:", err);
      alert("Save failed (see console).");
    }
  });

  // VIEW
  viewBtn.addEventListener("click", async () => {
    try {
      console.log("Requesting /getAll");
      const response = await fetch("/getAll");
      console.log("/getAll response status:", response.status);
      if (!response.ok) {
        const text = await response.text();
        console.error("Bad response from /getAll:", response.status, text);
        alert("Error loading contacts. See server console.");
        return;
      }

      const contacts = await response.json();
      console.log("Contacts received:", contacts);

      listDiv.innerHTML = "<h3>Contacts List:</h3>";

      if (!Array.isArray(contacts) || contacts.length === 0) {
        listDiv.innerHTML += "<p>No contacts found.</p>";
        return;
      }

      // Render defensivamente (comprobando campos)
      contacts.forEach(c => {
        const hobbiesText = Array.isArray(c.hobbies) ? c.hobbies.join(", ") : (c.hobbies || "None");
        const first = c.firstName || c.name || "Unknown";
        const last = c.lastName || c.lastname || "";
        const age = (typeof c.age === "number") ? c.age : "N/A";
        const birth = c.birthDate || "N/A";
        const type = c.typeOfContact || c.type || "N/A";
        const sex = c.sex || "N/A";
        const comments = c.comments || c.note || "None";

        listDiv.innerHTML += `
          <div class="contact-card">
            <strong>${first} ${last}</strong><br>
            Age: ${age}<br>
            Birthdate: ${birth}<br>
            Type: ${type}<br>
            Sex: ${sex}<br>
            Hobbies: ${hobbiesText}<br>
            Comments: ${comments}
          </div>
        `;
      });

    } catch (err) {
      console.error("Loading error:", err);
      alert("Error loading contacts (see console).");
    }
  });
});
