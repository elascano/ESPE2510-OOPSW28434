@echo off
chcp 65001 >nul
title Constructor Soccer Team Manager - CON RUTA COMPLETA
color 0A

echo ╔══════════════════════════════════════════════════════╗
echo ║        CONSTRUCTOR CON RUTA COMPLETA                 ║
echo ╚══════════════════════════════════════════════════════╝
echo.

:: RUTA COMPLETA A PYINSTALLER
set PYINSTALLER_PATH="C:\Users\DELL\AppData\Roaming\Python\Python314\Scripts\pyinstaller.exe"

echo [1/4] 🔍 Usando PyInstaller desde:
echo    %PYINSTALLER_PATH%

if not exist %PYINSTALLER_PATH% (
    echo    ❌ ERROR: No existe en esa ruta
    echo.
    echo    📁 Buscando alternativas...
    dir "C:\Users\DELL\AppData\Roaming\Python\Python314\Scripts\pyinstaller*" /s
    pause
    exit /b 1
)

echo    ✅ PyInstaller encontrado

:: ==========================================================
:: 2. LIMPIAR
:: ==========================================================
echo.
echo [2/4] 🧹 Limpiando builds anteriores...

if exist build rmdir /s /q build
if exist dist rmdir /s /q dist
if exist __pycache__ rmdir /s /q __pycache__
del /q *.spec 2>nul

echo    ✅ Limpieza completada

:: ==========================================================
:: 3. CONSTRUIR EJECUTABLE
:: ==========================================================
echo.
echo [3/4] 🔨 Construyendo ejecutable...
echo    ⏳ Esto puede tomar 2-3 minutos...
echo    📝 Por favor, espere...

%PYINSTALLER_PATH% ^
    --onefile ^
    --windowed ^
    --clean ^
    --name "SoccerTeamManager" ^
    --hidden-import pymongo ^
    --hidden-import pymongo.srv_resolver ^
    --hidden-import pymongo.monitoring ^
    --hidden-import pymongo.auth ^
    --hidden-import dnspython ^
    --collect-all pymongo ^
    --noupx ^
    --noconfirm ^
    main.py

if errorlevel 1 (
    echo.
    echo ❌ ERROR durante la construcción.
    echo 💡 Posibles soluciones:
    echo   1. Verifica que main.py exista
    echo   2. Prueba este comando manualmente:
    echo      %PYINSTALLER_PATH% --onefile --windowed main.py
    pause
    exit /b 1
)

echo    ✅ Ejecutable construido

:: ==========================================================
:: 4. CREAR VERSIÓN PORTABLE
:: ==========================================================
echo.
echo [4/4] 💼 Creando versión portable...

if exist "SoccerTeamManager_Portable" rmdir /s /q "SoccerTeamManager_Portable"
mkdir "SoccerTeamManager_Portable" 2>nul

if exist "dist\SoccerTeamManager.exe" (
    copy "dist\SoccerTeamManager.exe" "SoccerTeamManager_Portable\SoccerTeamManager.exe" >nul
    echo    ✅ Ejecutable copiado
    
    :: Crear README
    (
    echo SOCCER TEAM MANAGER - VERSIÓN PORTABLE
    echo ======================================
    echo.
    echo INSTRUCCIONES:
    echo 1. Ejecutar "SoccerTeamManager.exe"
    echo 2. No necesita instalación
    echo.
    echo Desarrollado con Python + Tkinter + MongoDB
    ) > "SoccerTeamManager_Portable\README.txt"
    
    echo    ✅ README creado
) else (
    echo    ⚠️ No se encontró el ejecutable
)

echo.
echo ╔══════════════════════════════════════════════════════╗
echo ║                 ✅ CONSTRUCCIÓN EXITOSA              ║
echo ╚══════════════════════════════════════════════════════╝
echo.
echo 📁 ARCHIVOS GENERADOS:
echo.
echo    📂 dist\SoccerTeamManager.exe
echo    📂 SoccerTeamManager_Portable\
echo.
echo 🚀 Para probar: dist\SoccerTeamManager.exe
echo.
pause