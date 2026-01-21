export default class MainView {

    constructor(controller) {
        this.controller = controller;

        document.getElementById("addTaskBtn")
            .addEventListener("click", () =>
                this.controller.addTask(
                    taskName.value,
                    taskDate.value
                )
            );

        document.getElementById("updateDaysBtn")
            .addEventListener("click", () =>
                this.controller.updateAlertDays(alertDays.value)
            );
    }

    updateTaskList(text) {
        document.getElementById("taskList").textContent = text;
    }

    showAlert(message) {
        alert(message);
    }
}
