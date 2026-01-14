const { app, BrowserWindow } = require('electron');
const path = require('path');
const config = require('./config.json');

function createWindow() {
    const win = new BrowserWindow({
        width: 600,
        height: 800,
        title: config.app_title,
        webPreferences: {
            nodeIntegration: true, 
            contextIsolation: false, 
            enableRemoteModule: true
        }
    });

    win.loadFile(path.join(__dirname, 'view/FrmCompuersEntry.html'));
}

app.whenReady().then(createWindow);

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') app.quit();
});