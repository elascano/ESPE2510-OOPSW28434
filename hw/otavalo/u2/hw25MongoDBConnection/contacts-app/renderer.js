const { contextBridge, ipcRenderer } = require("electron");

contextBridge.exposeInMainWorld("api", {
    saveContact: (data) => ipcRenderer.invoke("save-contact", data)
});
