
const API_URL = 'http://localhost:3000/api/tvsets';


const form = document.getElementById('create-form');
const tvsetList = document.getElementById('tvset-list');
const messageContainer = document.getElementById('message-container');


function displayMessage(text, isError = false) {
    messageContainer.textContent = text;
    messageContainer.className = isError ? 'message error' : 'message success';
    messageContainer.style.display = 'block';
    setTimeout(() => { messageContainer.style.display = 'none'; }, 5000);
}

async function fetchAndDisplayTVs() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error('Error al cargar la lista');
        
        const tvSets = await response.json();
        tvsetList.innerHTML = ''; 

        if (tvSets.length === 0) {
            tvsetList.innerHTML = '<tr><td colspan="6">No hay productos registrados.</td></tr>';
            return;
        }

        tvSets.forEach(tv => {
            const row = tvsetList.insertRow();
            

            row.insertCell().textContent = tv.id;
            row.insertCell().textContent = tv.description;
            row.insertCell().textContent = tv.screenSizeInches.toFixed(1);
            row.insertCell().textContent = tv.priceUSD.toFixed(2);
            row.insertCell().textContent = tv.ratio; 
            

            const actionCell = row.insertCell();
            const deleteBtn = document.createElement('button');
            deleteBtn.textContent = 'Eliminar';
            deleteBtn.onclick = () => handleDelete(tv.id);
            actionCell.appendChild(deleteBtn);
        });

    } catch (error) {
        displayMessage('No se pudo conectar al servidor API. Asegúrese de que app.js esté corriendo.', true);
        console.error('Error de fetch:', error);
    }
}

form.addEventListener('submit', async (e) => {
    e.preventDefault();
    
 
    const newTV = {
        description: document.getElementById('description').value,
        screenSizeInches: parseFloat(document.getElementById('screenSizeInches').value),
        priceUSD: parseFloat(document.getElementById('priceUSD').value)
    };

    if (newTV.screenSizeInches <= 0 || newTV.priceUSD <= 0) {
        displayMessage('El tamaño y el precio deben ser mayores que cero.', true);
        return;
    }

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newTV)
        });

        const result = await response.json();

        if (!response.ok) throw new Error(result.message || 'Error desconocido al crear TV Set');
        
        displayMessage('TV Set CREADO exitosamente.');
        form.reset();
        fetchAndDisplayTVs(); 

    } catch (error) {
        displayMessage(`Error al guardar: ${error.message}`, true);
    }
});

async function handleDelete(id) {
    if (!confirm(`¿Estás seguro de ELIMINAR el TV Set con ID ${id}?`)) {
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        const result = await response.json();

        if (!response.ok) throw new Error(result.message || 'Error desconocido al eliminar');

        displayMessage(result.message || `TV Set con ID ${id} ELIMINADO.`);
        fetchAndDisplayTVs(); 
    } catch (error) {
        displayMessage(`Error al eliminar: ${error.message}`, true);
    }
}


fetchAndDisplayTVs();