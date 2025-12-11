const { contextBridge, ipcRenderer } = require("electron");

contextBridge.exposeInMainWorld("api", {
<<<<<<< HEAD
    saveContact: (data) => ipcRenderer.invoke("save-contact", data)
});
=======
    saveContact: (data) => ipcRenderer.invoke("save-contact", data),
    findContact: (query) => ipcRenderer.invoke("find-contact", query),
    // ELIMINADO: deleteContact
    updateContact: (id, data) => ipcRenderer.invoke("update-contact", id, data) 
});
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
