# 🧩 Sudoku 9x9 con Backtracking

Este proyecto implementa la resolución de un Sudoku 9x9 utilizando la técnica de **backtracking**.

## 📌 Descripción del proyecto

El objetivo es desarrollar un programa capaz de resolver un tablero de Sudoku 9x9, cumpliendo las reglas del juego:

* No repetir números en filas
* No repetir números en columnas
* No repetir números en subcuadrículas 3x3

El sistema permite:

* Resolver un Sudoku vacío
* Resolver un Sudoku con valores iniciales

---

## 🧠 Técnica utilizada

Se utiliza **backtracking**, una técnica que prueba posibles soluciones y retrocede cuando encuentra un error.

---

### 🔹 ¿Qué es un Sudoku 9x9?

Es un juego de lógica que consiste en completar una cuadrícula de 9x9 con números del 1 al 9.

### 🎯 Objetivo

Llenar todas las celdas del tablero respetando las reglas del juego.

---

### ⚠️ Restricciones

* No se pueden repetir números en una fila
* No se pueden repetir números en una columna
* No se pueden repetir números en una subcuadrícula 3x3

---

### ✅ Solución válida

Una solución es válida cuando:

* El tablero está completamente lleno
* Se cumplen todas las restricciones

---

### 🔁 Uso de Backtracking

El algoritmo funciona de la siguiente manera:

1. Busca una celda vacía
2. Intenta colocar un número del 1 al 9
3. Verifica si cumple las restricciones
4. Si es válido, continúa
5. Si no, prueba otro número
6. Si ninguno funciona, retrocede (backtracking)

---

### ❓ Preguntas orientadoras

* **¿Cómo se identifica una celda vacía?**
  Se representa con el valor `0`.

* **¿Qué pasa si ningún número sirve?**
  El algoritmo retrocede a la celda anterior.

---
