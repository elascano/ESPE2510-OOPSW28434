@echo off
echo ==========================================
echo    SIMULADOR DE HORMIGAS
echo ==========================================
echo.

echo Limpiando compilaciones anteriores...
if exist model\*.class del model\*.class
if exist view\*.class del view\*.class  
if exist controller\*.class del controller\*.class

echo.
echo Compilando todos los archivos...

javac ^
model\Area.java ^
model\Ant.java ^
model\Cell.java ^
model\Colony.java ^
model\Nest.java ^
model\Food.java ^
model\FoodPile.java ^
model\PheromoneDrop.java ^
model\AntEater.java ^
view\SimulatorDisplay.java ^
controller\SimulationController.java

if errorlevel 1 (
    echo.
    echo ERROR: Fallo en la compilacion
    echo.
    echo Verifica que tengas estos archivos:
    echo - model\Area.java
    echo - model\Ant.java
    echo - model\Cell.java
    echo - model\Colony.java
    echo - model\Nest.java
    echo - model\Food.java
    echo - model\FoodPile.java
    echo - model\PheromoneDrop.java
    echo - model\AntEater.java
    echo - view\SimulatorDisplay.java
    echo - controller\SimulationController.java
    pause
    exit /b 1
)

echo.
echo ==========================================
echo    COMPILACION EXITOSA!
echo ==========================================
echo.

echo Ejecutando simulacion...
echo.
java controller.SimulationController

echo.
pause
