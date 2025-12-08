const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('contactAPI', {
    saveContact: (data) => ipcRenderer.invoke('save-contact', data)
});
