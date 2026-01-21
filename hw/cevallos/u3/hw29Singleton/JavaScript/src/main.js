const { app, BrowserWindow, ipcMain } = require('electron');
const path = require('path');
const SalesController = require('./controllers/SalesController');

let mainWindow;
let salesController;

function createWindow() {
  mainWindow = new BrowserWindow({
    width: 1400,
    height: 900,
    webPreferences: {
      nodeIntegration: false,
      contextIsolation: true,
      enableRemoteModule: false,
      preload: path.join(__dirname, 'preload.js')
    },
    icon: path.join(__dirname, '../assets/icon.png'),
    show: false
  });

  mainWindow.loadFile('index.html');
  
  mainWindow.once('ready-to-show', () => {
    mainWindow.show();
    
    if (process.env.NODE_ENV === 'development') {
      mainWindow.webContents.openDevTools();
    }
  });

  mainWindow.on('closed', () => {
    mainWindow = null;
  });
}

// Inicializar el controlador
function initializeController() {
  salesController = new SalesController();
}

// Configurar IPC handlers
function setupIpcHandlers() {
  // Get current discount
  ipcMain.handle('get-current-discount', async () => {
    return salesController.getCurrentDiscount();
  });

  // Update discount
  ipcMain.handle('update-discount', async (event, newDiscount) => {
    try {
      const success = salesController.updateDiscountPercentage(newDiscount);
      return { success, discount: newDiscount };
    } catch (error) {
      return { success: false, error: error.message };
    }
  });

  // Create sale
  ipcMain.handle('create-sale', async (event, itemName, price) => {
    try {
      const sale = salesController.createSale(itemName, price);
      return { success: true, sale: sale.toJSON() };
    } catch (error) {
      return { success: false, error: error.message };
    }
  });

  // Get all sales
  ipcMain.handle('get-sales', async () => {
    const sales = salesController.getSalesSummary();
    const stats = salesController.getSalesStatistics();
    return { sales, stats };
  });

  // Clear sales
  ipcMain.handle('clear-sales', async () => {
    const success = salesController.clearSales();
    return { success };
  });
}

app.whenReady().then(() => {
  initializeController();
  setupIpcHandlers();
  createWindow();
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit();
  }
});

app.on('activate', () => {
  if (mainWindow === null) {
    initializeController();
    setupIpcHandlers();
    createWindow();
  }
});