const { app, BrowserWindow } = require('electron');
const path = require('path');

function createWindow() {
    const win = new BrowserWindow({
        width: 900, height: 700,
        webPreferences: {
            nodeIntegration: true,
            contextIsolation: false
        }
    });

    win.loadURL('data:text/html;charset=utf-8,<html><body style="font-family:sans-serif;"><h2>Cargando interfaz...</h2></body></html>');

    win.webContents.openDevTools();

    win.webContents.on('did-finish-load', () => {
        const appPath = path.join(__dirname, 'App.js').replace(/\\/g, '/');

        win.webContents.executeJavaScript(`
            try {
                require('${appPath}');
            } catch (e) {
                document.body.innerHTML = '<h2 style="color:red">OCURRIÓ UN ERROR:</h2><h3 style="color:red">' + e.message + '</h3><pre>' + e.stack + '</pre>';
                console.error(e);
                alert("Error: " + e.message);
            }
        `);
    });
}

app.whenReady().then(createWindow);