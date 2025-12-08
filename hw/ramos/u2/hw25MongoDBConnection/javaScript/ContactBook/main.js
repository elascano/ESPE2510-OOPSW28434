const { app, BrowserWindow } = require('electron');
const path = require('path');

function createWindow() {
    const win = new BrowserWindow({
        width: 800, height: 700,
        autoHideMenuBar: true,
        webPreferences: {
            nodeIntegration: true,
            contextIsolation: false
        }
    });

    win.loadURL('data:text/html;charset=utf-8,<html><body style="margin:0; background:#ece9d8;"></body></html>');

    const appPath = path.join(__dirname, 'App.js').replace(/\\/g, '/');

    win.webContents.on('did-finish-load', () => {
        win.webContents.executeJavaScript(`
            try {
                require('${appPath}');
            } catch(e) {
                alert("Error cargando App.js: " + e.message);
                console.error(e);
            }
        `);
    });
}

app.whenReady().then(createWindow);