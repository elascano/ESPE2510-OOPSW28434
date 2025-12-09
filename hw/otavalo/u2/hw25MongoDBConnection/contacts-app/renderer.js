const { contextBridge, ipcRenderer } = require("electron");

contextBridge.exposeInMainWorld("api", {
    saveContact: (data) => ipcRenderer.invoke("save-contact", data),
    findContact: (query) => ipcRenderer.invoke("find-contact", query),
    // ELIMINADO: deleteContact
    updateContact: (id, data) => ipcRenderer.invoke("update-contact", id, data) 
});