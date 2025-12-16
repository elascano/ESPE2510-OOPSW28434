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
    const id = document.getElementById("id").value.trim();
    const name = document.getElementById("Name").value.trim();
    const age = document.getElementById("age").value;
    const tshirt = document.getElementById("tshirt").value;


    if (!id || !name || !age || !tshirt) {
      alert("Please fill all required fields.");
      return;
    }

    const SoccerPlayersData = { id, name, birthDate, age, tshirt};

    try {
      console.log("Sending /add with:", SoccerPlayersData);
      const response = await fetch("/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(SoccerPlayersData)
      });
      const result = await response.json();
      console.log("/add result:", result);
      alert(result.message || "Saved");
    } catch (err) {
      console.error("Save error:", err);
      alert("Save failed (see console).");
    }
  });

});
