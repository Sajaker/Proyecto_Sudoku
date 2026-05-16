# Sudoku 9x9

El proposito del programa es resolver un tablero incompleto usando **backtracking**.

## Características
- Representación de tablero 9x9 con matrices.
- Validación de reglas del Sudoku:
  + Filas
  + Columnas
  + Subcuadrículas 3x3
- Resolución automática del tablero.
- Impresión del resultado en consola.

## Estructura del proyecto
El proyecto se compone de de el archivo main, el cual se encarga de iniciar el tablero y ejecutar su solución, y la clase llamada Sudoku9x9 el cual con tiene la logica de validacion, resolución e imprecion

## Funcionamiento
El algoritmo:
1. Busca una celda vacía (`0`).
2. Prueba números del 1 al 9.
3. Verifica si el número es válido.
4. Continúa recursivamente.
5. Si no encuentra solución, retrocede y prueba otro valor.
