document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('contactForm');
    const messageElement = document.getElementById('message');

    const showMessage = (text, isError = false) => {
        messageElement.textContent = text;
        messageElement.className = isError ? 'error' : 'success';
        setTimeout(() => messageElement.textContent = "", 5000);
    };

    const clearForm = () => {
        form.reset();
        document.getElementById('type').value = "Friend";
        document.getElementById('hobbies').value = "Cook";
        document.getElementById('sex_female').checked = true;
        document.getElementById('birth_date').value = "01/01/2000";
        document.getElementById('contact_id').value = "AUTO";
    };

    if (!window.contactAPI) {
        showMessage("ERROR: contactAPI no disponible", true);
    }

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const formData = new FormData(form);
        const data = Object.fromEntries(formData.entries());
        data.contact_id = null;

        try {
            const result = await window.contactAPI.saveContact(data);
            if (result.error) {
                showMessage(result.error, true);
            } else {
                showMessage(result.message);
                clearForm();
            }
        } catch (err) {
            showMessage("ERROR de comunicación con el backend", true);
        }
    });

    clearForm();
});