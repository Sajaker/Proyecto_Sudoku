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

## Estructura del proyecto (Clases) 
### Main

El archivo main se encarga de iniciar el tablero y ejecutar su solución
### Sudoku9x9

La clase llamada Sudoku9x9 es la que tiene la logica del algoritmo: 
Tiene la busqueda de celdas vacías, validación de los números, el backtracking con su respectiva recursividad y la impresión del tablero 

### Interfaz
La interfaz se encarga de mostrarnos el tablero. Aqui es donde el usuario interactua con el Sudoku. Validando numeros y logrando llegar a una posible solución. 

## Funcionamiento
El algoritmo:
1. Busca una celda vacía (`0`).
2. Prueba números del 1 al 9.
3. Verifica si el número es válido.
4. Continúa recursivamente.
5. Si no encuentra solución, retrocede y prueba otro valor.

## Restricciones 
- Cada fila debe tener numero del 1 al 9 sin repetirse.
- Cada columna debe tener numeros del 1 al 9 sin repetirse.
- Cada subcuadrícula 3x3 debe tener numeros del 1 al 9 sin repetirse.
- Los numeros iniciales del tablero no se pueden modificar por el usuario.

## ¿Por qué usamos el Backtracking?
El problema del Sudoku tiene multiples combinaciones posibles. 
Con ayuda del algoritmo de backtracking tenemos la posibilidad de probar diferentes soluciones de manera recursiva. 
Si una combinación no cumple con las restricciones del Sudoku, el algoritmo retrocede y prueba con otro numero.
Lo que nos permite encontrar una solución valida de manera eficiente. 
