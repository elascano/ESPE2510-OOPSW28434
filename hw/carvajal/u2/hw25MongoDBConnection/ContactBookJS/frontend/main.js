const API = "http://localhost:3000/api";

// VALIDACIONES -----------------------
function validarCorreo(email) {
    return /\S+@\S+\.\S+/.test(email);
}

function validarTelefono(tel) {
    return /^[0-9]{7,10}$/.test(tel);
}

function validarTexto(txt) {
    return /^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$/.test(txt);
}

// GUARDAR ----------------------------
async function guardar() {
    const data = obtenerDatos();
    if (!validarDatos(data)) return;

    await fetch(`${API}/contactos`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    });

    alert("Contacto guardado");
    cargarTabla();
}

// BUSCAR -----------------------------
async function buscar() {
    const cedula = document.getElementById("cedula").value;
    if (cedula === "") return alert("Ingrese la cédula");

    const res = await fetch(`${API}/contactos/${cedula}`);
    const data = await res.json();

    if (!data) return alert("No encontrado");

    mostrarDatos(data);
}

// ACTUALIZAR -------------------------
async function actualizar() {
    const data = obtenerDatos();
    if (!validarDatos(data)) return;

    await fetch(`${API}/contactos/${data.cedula}`, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    });

    alert("Actualizado");
    cargarTabla();
}

// ELIMINAR ---------------------------
async function eliminar() {
    const cedula = document.getElementById("cedula").value;
    if (cedula === "") return alert("Ingrese la cédula");

    await fetch(`${API}/contactos/${cedula}`, {method: "DELETE"});
    alert("Eliminado");
    cargarTabla();
}

// EXTRA ------------------------------
function obtenerDatos() {
    return {
        nombre: document.getElementById("nombre").value.trim(),
        apellido: document.getElementById("apellido").value.trim(),
        cedula: document.getElementById("cedula").value.trim(),
        telefono: document.getElementById("telefono").value.trim(),
        email: document.getElementById("email").value.trim()
    };
}

function mostrarDatos(d) {
    document.getElementById("nombre").value = d.nombre;
    document.getElementById("apellido").value = d.apellido;
    document.getElementById("cedula").value = d.cedula;
    document.getElementById("telefono").value = d.telefono;
    document.getElementById("email").value = d.email;
}

function validarDatos(d) {
    if (!validarTexto(d.nombre)) return alert("Nombre inválido");
    if (!validarTexto(d.apellido)) return alert("Apellido inválido");
    if (d.cedula.length < 5) return alert("Cédula inválida");
    if (!validarTelefono(d.telefono)) return alert("Teléfono inválido");
    if (!validarCorreo(d.email)) return alert("Email inválido");
    return true;
}

async function cargarTabla() {
    const res = await fetch(`${API}/contactos`);
    const lista = await res.json();

    let html = "";
    lista.forEach(c => {
        html += `
            <tr>
                <td>${c.nombre}</td>
                <td>${c.apellido}</td>
                <td>${c.cedula}</td>
                <td>${c.telefono}</td>
                <td>${c.email}</td>
            </tr>`;
    });

    document.querySelector("#tabla tbody").innerHTML = html;
}

cargarTabla();
