# Lógica del Solucionador de Sudoku

Este documento detalla el comportamiento, las restricciones y el funcionamiento del algoritmo de Backtracking aplicado para resolver el Sudoku.

---

## 1. Estado Inicial
El estado inicial se representa mediante una matriz o cuadrícula bidimensional de **9x9**. Dependiendo de la elección del usuario, este estado puede ser:

* **Tablero vacío:** Todas las celdas tienen un valor de `0` (o vacío), indicando que el algoritmo debe construir la solución completa desde cero.
* **Tablero parcialmente lleno:** Algunas celdas contienen números válidos del **1 al 9** proporcionados por el usuario como pistas iniciales, y el resto contiene `0`. El algoritmo respetará estos valores fijos y no intentará modificarlos.

---

## 2. Decisiones Posibles
Cuando el algoritmo se encuentra en una celda vacía (representada por un `0`), la decisión que debe tomar es elegir un número entero entre **1 y 9**. El algoritmo probará cada uno de estos números en orden secuencial para ver cuál encaja correctamente.

---

## 3. Validaciones
Antes de tomar la decisión de escribir un número definitivo en una celda vacía ubicada en la posición `(fila, columna)`, el algoritmo debe comprobar obligatoriamente **tres restricciones** para garantizar que la jugada es válida:

1. **Revisar fila:** Verificar que el número elegido no exista ya en ninguna otra celda de la misma fila.
2. **Revisar columna:** Verificar que el número elegido no exista ya en ninguna otra celda de la misma columna.
3. **Revisar subcuadro 3x3:** Calcular a qué bloque o cuadrícula menor de **3x3** pertenece la celda actual y verificar que el número no esté presente dentro de ese bloque.

---

## 4. Caso Base
En la recursividad, el caso base es la condición que le dice al algoritmo: *¡Detente, ya terminaste!* Para el Sudoku, el caso base se alcanza cuando **ya no quedan celdas vacías** en el tablero (es decir, ya no se encuentra ningún `0`). Si el algoritmo logra llenar todas las celdas cumpliendo las validaciones, significa que ha encontrado el Sudoku resuelto.

---

## 5. Backtracking (Retroceso)
Esta es la esencia del algoritmo. ¿Qué ocurre si ponemos un número que parece válido al principio, pero más adelante nos damos cuenta de que nos lleva a un callejón sin salida (ningún número del 1 al 9 sirve en una celda futura)?

El algoritmo realiza el **backtracking**:
* **Deshace** su decisión anterior, borrando el número que había puesto (volviendo a poner la celda en `0`).
* **Retrocede** al paso anterior en el código recursivo.
* **Prueba** con el siguiente número posible para esa celda.