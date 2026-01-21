async function loadTasks() {
    const res = await fetch('/tasks');
    const tasks = await res.json();

    const list = document.getElementById('tasks');
    list.innerHTML = '';

    tasks.forEach(t => {
        const li = document.createElement('li');
        li.textContent = `${t.name} - ${t.remainingDays} days`;
        list.appendChild(li);
    });
}

async function addTask() {
    const name = document.getElementById('name').value;
    const date = document.getElementById('date').value;

    await fetch('/tasks', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, dueDate: date })
    });

    loadTasks();
}

loadTasks();
