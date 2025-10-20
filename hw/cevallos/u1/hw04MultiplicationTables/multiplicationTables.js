const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function generarTabla(numero, repeticiones) {
    console.log('\n' + '='.repeat(40));
    console.log(`GENERANDO ${repeticiones} TABLA(S) DEL ${numero}`);
    console.log('='.repeat(40));
    
    for (let r = 1; r <= repeticiones; r++) {
        console.log(`\n--- Tabla ${r} del ${numero} ---`);
        
        for (let i = 1; i <= 10; i++) {
            const resultado = numero * i;
            console.log(`${numero} x ${i} = ${resultado}`);
        }
    }
}

function preguntarNumero() {
    rl.question('\n¿De qué número quieres la tabla de multiplicar? ', (numeroInput) => {
        const numero = parseInt(numeroInput);
        
        if (isNaN(numero) || numero <= 0) {
            console.log('Por favor, ingresa un número válido mayor a 0.');
            return preguntarNumero();
        }
        
        preguntarRepeticiones(numero);
    });
}

function preguntarRepeticiones(numero) {
    rl.question('¿Cuántas veces quieres repetir la tabla? ', (repeticionesInput) => {
        const repeticiones = parseInt(repeticionesInput);
        
        if (isNaN(repeticiones) || repeticiones <= 0) {
            console.log('Por favor, ingresa un número válido mayor a 0.');
            return preguntarRepeticiones(numero);
        }
        
        generarTabla(numero, repeticiones);
        preguntarContinuar();
    });
}

function preguntarContinuar() {
    rl.question('\n¿Quieres generar otra tabla? (s/n): ', (respuesta) => {
        if (respuesta.toLowerCase() === 's' || respuesta.toLowerCase() === 'si') {
            preguntarNumero();
        } else {
            console.log('¡Gracias por usar el generador de tablas!');
            rl.close();
        }
    });
}

function iniciarPrograma() {
    console.log('GENERADOR DE TABLAS DE MULTIPLICAR');
    console.log('====================================');
    preguntarNumero();
}

iniciarPrograma();