const { contextBridge, ipcRenderer } = require('electron');

// Exponer APIs seguras al renderer process
contextBridge.exposeInMainWorld('salesAPI', {
  // Discount operations
  getCurrentDiscount: () => ipcRenderer.invoke('get-current-discount'),
  updateDiscount: (newDiscount) => ipcRenderer.invoke('update-discount', newDiscount),
  
  // Sale operations
  createSale: (itemName, price) => ipcRenderer.invoke('create-sale', itemName, price),
  getSales: () => ipcRenderer.invoke('get-sales'),
  clearSales: () => ipcRenderer.invoke('clear-sales')
});