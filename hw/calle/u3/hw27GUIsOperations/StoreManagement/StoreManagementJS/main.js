const { app, BrowserWindow } = require('electron');
const path = require('path');

function createWindow() {
    const win = new BrowserWindow({
        width: 1000, height: 650,
        webPreferences: { nodeIntegration: true, contextIsolation: false }
    });
    win.loadFile(path.join(__dirname, 'views/index.html'));
}
app.whenReady().then(createWindow);