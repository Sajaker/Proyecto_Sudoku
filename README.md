# Sudoku 9x9

El propósito del programa es resolver un tablero de Sudoku 9x9 incompleto o vacío usando **backtracking**.

## Características

- Representación de tablero 9x9 con matrices.
- Validación de reglas del Sudoku:
  - Filas
  - Columnas
  - Subcuadrículas 3x3
- Resolución automática del tablero mediante backtracking.
- Interfaz gráfica (Swing) para interactuar con el tablero.
- Soporte para tablero vacío y tablero con valores iniciales.
- Diferenciación visual entre valores iniciales y valores agregados por el algoritmo.

## Estructura del proyecto (Clases)

### Main

El archivo Main se encarga de iniciar la interfaz gráfica del programa.

### Sudoku9x9

La clase Sudoku9x9 contiene la lógica del algoritmo:
- Búsqueda de celdas vacías.
- Validación de números en filas, columnas y subcuadrículas.
- Backtracking con recursividad.
- Validación del tablero inicial.

### Interfaz

La clase Interfaz crea la GUI con Java Swing. Permite al usuario:
- Ingresar números en el tablero manualmente.
- Cargar un tablero de ejemplo.
- Resolver el tablero con un botón.
- Limpiar el tablero para empezar de cero.
- Ver la diferencia entre valores iniciales (negro, fondo gris) y valores resueltos (azul, fondo blanco).

## Funcionamiento del algoritmo

1. Busca una celda vacía (`0`).
2. Prueba números del 1 al 9.
3. Verifica si el número es válido (fila, columna, subcuadrícula 3x3).
4. Continúa recursivamente con la siguiente celda vacía.
5. Si no encuentra solución, retrocede (backtracking) y prueba otro valor.
6. Si no queda ningún número válido, retorna `false` y la recursión deshace la asignación.

## Modos de inicio

### Caso 1: Tablero vacío
El usuario puede iniciar con un tablero completamente vacío. El programa construirá una solución válida desde cero.

### Caso 2: Tablero con valores iniciales
El usuario puede ingresar una configuración parcial del Sudoku. El algoritmo completará el tablero respetando los valores iniciales y las restricciones del juego.

## Restricciones

- Cada fila debe tener números del 1 al 9 sin repetirse.
- Cada columna debe tener números del 1 al 9 sin repetirse.
- Cada subcuadrícula 3x3 debe tener números del 1 al 9 sin repetirse.
- Los números iniciales del tablero no se pueden modificar por el usuario una vez resuelto.

## Interfaz gráfica

En la interfaz se visualiza:
- La cuadrícula 9x9.
- Los valores iniciales dados por el usuario (color negro, fondo gris).
- Los valores agregados por el algoritmo (color azul, fondo blanco).
- La solución completa encontrada.

## ¿Por qué usamos Backtracking?

El problema del Sudoku tiene múltiples combinaciones posibles. Con ayuda del algoritmo de backtracking tenemos la posibilidad de probar diferentes soluciones de manera recursiva. Si una combinación no cumple con las restricciones del Sudoku, el algoritmo retrocede y prueba con otro número. Lo que nos permite encontrar una solución válida de manera eficiente.
