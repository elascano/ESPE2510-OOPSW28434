# Exam Solution/make_exe_simple.py
import os
import sys
import shutil
import subprocess

print("🔧 Creando ejecutable TeamManagementSystem.exe...")

# 1. Verificar que tenemos todos los archivos necesarios
required_files = [
    "main.py",
    "Controller/Soccer_team_controller.py",
    "Model/Soccer_team.py",
    "Utils/CRUD_operations.py", 
    "Utils/validations.py"
]

for file in required_files:
    if not os.path.exists(file):
        print(f"❌ Faltante: {file}")
        sys.exit(1)

# 2. Verificar el archivo .ui
ui_files = ["View/GUIS.ui", "GUIS.ui"]
ui_path = None
for ui_file in ui_files:
    if os.path.exists(ui_file):
        ui_path = ui_file
        break

if not ui_path:
    print("❌ No se encontró GUIS.ui")
    print("⚠️  Se usará interfaz básica en su lugar")

# 3. Crear comando de PyInstaller
pyinstaller_cmd = [
    "pyinstaller",
    "--name=TeamManagementSystem",
    "--onefile",
    "--windowed",
    "--clean",
    "--noconfirm"
]

# 4. Agregar archivos de datos si existen
if ui_path and os.path.exists(ui_path):
    pyinstaller_cmd.append(f"--add-data={ui_path};.")
    print(f"✅ Incluyendo archivo UI: {ui_path}")

# 5. Agregar imports ocultos necesarios
hidden_imports = [
    "PyQt6.QtWidgets",
    "PyQt6.QtCore", 
    "PyQt6.QtGui",
    "pymongo",
    "bson"
]

for imp in hidden_imports:
    pyinstaller_cmd.append(f"--hidden-import={imp}")

# 6. Agregar el archivo principal
pyinstaller_cmd.append("main.py")

# 7. Convertir a string para ejecutar
cmd_str = " ".join(pyinstaller_cmd)
print(f"\n🚀 Ejecutando: {cmd_str}")

# 8. Ejecutar PyInstaller
result = subprocess.run(cmd_str, shell=True, capture_output=True, text=True)

if result.stdout:
    print(result.stdout)
if result.stderr:
    print(f"⚠️  {result.stderr}")

# 9. Verificar si se creó el ejecutable
exe_path = "dist/TeamManagementSystem.exe"
if os.path.exists(exe_path):
    print("\n✅ Ejecutable creado exitosamente!")
    print(f"📂 Ubicación: {exe_path}")
    
    # Copiar a la carpeta raíz para facilidad de acceso
    shutil.copy2(exe_path, "TeamManagementSystem.exe")
    print("✅ Copiado a: TeamManagementSystem.exe")
    
    # Crear un archivo README simple
    with open("README_EXE.txt", "w", encoding="utf-8") as f:
        f.write("""Team Management System
=====================

Para ejecutar el programa:
1. Haga doble clic en "TeamManagementSystem.exe"
2. El programa se iniciará automáticamente

Notas:
- No requiere instalación
- Necesita conexión a Internet para MongoDB
- Si no funciona, ejecute como administrador

Desarrollado por Mateo
© 2024
""")
    print("✅ Archivo README_EXE.txt creado")
    
else:
    print("\n❌ No se pudo crear el ejecutable")
    if result.returncode != 0:
        print(f"Código de error: {result.returncode}")