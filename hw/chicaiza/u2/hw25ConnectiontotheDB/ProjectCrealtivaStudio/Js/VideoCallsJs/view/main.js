const form = document.getElementById("videoCallForm");
const viewBtn = document.getElementById("viewCallsBtn");
const videoCallsList = document.getElementById("videoCallsList");

// Validación simple de hora HH:MM
function validateHour(hour) {
    const pattern = /^([01]\d|2[0-3]):([0-5]\d)$/;
    return pattern.test(hour);
}

// Guardar videollamada
form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const customerId = document.getElementById("customerId").value.trim();
    const videoCallDate = document.getElementById("videoCallDate").value;
    const hour = document.getElementById("hour").value;
    const medium = document.getElementById("medium").value;
    const note = document.getElementById("note").value.trim();

    if (!customerId || !videoCallDate || !hour || !medium) {
        alert("Todos los campos obligatorios deben ser completados.");
        return;
    }

    if (!validateHour(hour)) {
        alert("Ingrese la hora en formato HH:MM (00:00 a 23:59).");
        return;
    }

    try {
        const res = await fetch("/add", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ customerId, videoCallDate, hour, medium, note })
        });
        const result = await res.json();
        alert(result.message);
        form.reset();
    } catch (err) {
        console.error(err);
        alert("Error al guardar la videollamada.");
    }
});

// Ver videollamadas
viewBtn.addEventListener("click", async () => {
    try {
        const res = await fetch("/getAll");
        const calls = await res.json();

        if (calls.length === 0) {
            videoCallsList.innerHTML = "<p>No hay videollamadas registradas en la nube.</p>";
            return;
        }

        let html = "<ul>";
        calls.forEach(vc => {
            html += `<li><strong>${vc.customerId}</strong> - ${vc.videoCallDate} ${vc.hour} - ${vc.medium}<br>${vc.note}</li><hr>`;
        });
        html += "</ul>";

        videoCallsList.innerHTML = html;
    } catch (err) {
        console.error(err);
        alert("Error al obtener las videollamadas.");
    }
});
