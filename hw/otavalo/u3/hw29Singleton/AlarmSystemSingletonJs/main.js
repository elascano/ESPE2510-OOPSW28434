const { app, BrowserWindow, ipcMain } = require('electron');
const AlarmController = require('./controller/AlarmController');

app.disableHardwareAcceleration();

function createWindow() {
    const win = new BrowserWindow({
        width: 600,
        height: 500,
        webPreferences: { 
            nodeIntegration: true, 
            contextIsolation: false 
        }
    });

    win.loadFile('view/FrmAlarm.html');
    win.webContents.on('did-finish-load', async () => {
        try {
            await AlarmController.run(win);
        } catch (error) {
            console.error("Error al ejecutar alertas iniciales:", error);
        }
    });
}
ipcMain.handle('update-stock', async (event, newLimit) => {
    try {
        return await AlarmController.handleUpdateStock(newLimit);
    } catch (error) {
        console.error("Error en update-stock:", error);
        throw error;
    }
});

app.whenReady().then(() => {
    createWindow();

    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length === 0) createWindow();
    });
});

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') app.quit();
});