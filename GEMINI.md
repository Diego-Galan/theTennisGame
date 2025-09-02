
### 1. Mapa de Puntuaciones Base (Modo Normal)

Este mapa cubre los marcadores antes de que cualquier jugador anote 4 veces. La lógica es una simple correspondencia entre el contador de puntos y el texto.

**Mapeo de Puntos a Texto:**
*   `0` → "Love"
*   `1` → "15"
*   `2` → "30"
*   `3` → "40"

| Puntos P1 (`p1`) | Puntos P2 (`p2`) | Texto Esperado |
| :--------------- | :--------------- | :------------- |
| 0                | 0                | Love–Love      |
| 0                | 1                | Love–15        |
| 0                | 2                | Love–30        |
| 0                | 3                | Love–40        |
| 1                | 0                | 15–Love        |
| 1                | 1                | 15–15          |
| 1                | 2                | 15–30          |
| 1                | 3                | 15–40          |
| 2                | 0                | 30–Love        |
| 2                | 1                | 30–15          |
| 2                | 2                | 30–30          |
| 2                | 3                | 30–40          |
| 3                | 0                | 40–Love        |
| 3                | 1                | 40–15          |
| 3                | 2                | 40–30          |

*Nota: El caso (3, 3) se excluye porque pertenece a la regla de "Deuce".*

---

### 2. Tabla de Verdad Compacta (Puntuaciones 0 a 6)

Esta tabla es la herramienta más potente para validar la lógica completa. Recorre todas las combinaciones de puntos de 0 a 6 y clasifica cada una según las reglas de prioridad.

| `p1` | `p2` | Categoría        | Texto Esperado        |
| :--- | :--- | :--------------- | :-------------------- |
| 0-3  | 0-3  | Normal           | *(Ver Tabla 1)*       |
| 3    | 3    | Deuce            | Deuce                 |
| 4    | 0    | Player 1 wins    | Player 1 wins         |
| 4    | 1    | Player 1 wins    | Player 1 wins         |
| 4    | 2    | Player 1 wins    | Player 1 wins         |
| 4    | 3    | Advantage P1     | Advantage Player 1    |
| 4    | 4    | Deuce            | Deuce                 |
| 4    | 5    | Advantage P2     | Advantage Player 2    |
| 4    | 6    | Player 2 wins    | Player 2 wins         |
| 5    | 3    | Player 1 wins    | Player 1 wins         |
| 5    | 4    | Advantage P1     | Advantage Player 1    |
| 5    | 5    | Deuce            | Deuce                 |
| 5    | 6    | Advantage P2     | Advantage Player 2    |
| 6    | 4    | Player 1 wins    | Player 1 wins         |
| 6    | 5    | Advantage P1     | Advantage Player 1    |
| 6    | 6    | Deuce            | Deuce                 |

*Nota: Las combinaciones simétricas (ej. 0-4 vs 4-0) se comportan de manera análoga para el otro jugador.*

---

### 3. Secuencias de Juego Representativas

Estas secuencias simulan partidas reales y validan la transición entre estados.

**a) Victoria directa de P1 (P1, P1, P1, P1)**
| Paso | Puntos P1 | Puntos P2 | Texto Esperado |
| :--- | :-------- | :-------- | :------------- |
| 0    | 0         | 0         | Love–Love      |
| 1    | 1         | 0         | 15–Love        |
| 2    | 2         | 0         | 30–Love        |
| 3    | 3         | 0         | 40–Love        |
| 4    | 4         | 0         | Player 1 wins  |

**b) Entrada a Deuce (P1, P1, P1, P2, P2, P2)**
| Paso | Puntos P1 | Puntos P2 | Texto Esperado |
| :--- | :-------- | :-------- | :------------- |
| ...  | 2         | 2         | 30–30          |
| 3    | 3         | 2         | 40–30          |
| 4    | 3         | 3         | Deuce          |

**c) Advantage → Deuce → Advantage → Win (desde Deuce, secuencia: P1, P2, P1, P1)**
| Paso | Puntos P1 | Puntos P2 | Texto Esperado     |
| :--- | :-------- | :-------- | :----------------- |
| 0    | 3         | 3         | Deuce              |
| 1    | 4         | 3         | Advantage Player 1 |
| 2    | 4         | 4         | Deuce              |
| 3    | 5         | 4         | Advantage Player 1 |
| 4    | 6         | 4         | Player 1 wins      |

**d) Varias vueltas entre Advantage y Deuce (desde Deuce, secuencia: P1, P2, P2, P1)**
| Paso | Puntos P1 | Puntos P2 | Texto Esperado     |
| :--- | :-------- | :-------- | :----------------- |
| 0    | 4         | 4         | Deuce              |
| 1    | 5         | 4         | Advantage Player 1 |
| 2    | 5         | 5         | Deuce              |
| 3    | 5         | 6         | Advantage Player 2 |
| 4    | 6         | 6         | Deuce              |

**e) Intento de anotar tras victoria (secuencia: P1, P1, P1, P1, luego P2)**
| Paso | Puntos P1 | Puntos P2 | Texto Esperado |
| :--- | :-------- | :-------- | :------------- |
| ...  | 3         | 0         | 40–Love        |
| 4    | 4         | 0         | Player 1 wins  |
| 5    | 4         | 1         | Player 1 wins  |

**f) Remontada de P2 desde 0-40 para ganar (secuencia: P1,P1,P1, P2,P2,P2, P2,P2,P2)**
| Paso | Puntos P1 | Puntos P2 | Texto Esperado     |
| :--- | :-------- | :-------- | :----------------- |
| ...  | 3         | 0         | 40–Love            |
| 4    | 3         | 1         | 40–15              |
| 5    | 3         | 2         | 40–30              |
| 6    | 3         | 3         | Deuce              |
| 7    | 3         | 4         | Advantage Player 2 |
| 8    | 3         | 5         | Player 2 wins      |

---

### 4. Checklist de Cobertura para el Candidato

Usa esta lista para autoevaluar tu implementación contra el diseño validado.

-   [ ] **Mapeo Normal:** ¿El código produce los textos de la **Tabla 1** para puntuaciones por debajo de 40-40?
-   [ ] **Deuce Correcto:** ¿El código devuelve "Deuce" para `(3,3)`, `(4,4)`, `(5,5)` y cualquier `(n,n)` con `n >= 3`, como se ve en la **Tabla 2**?
-   [ ] **Advantage Correcto:** ¿El código devuelve "Advantage Player X" cuando `p1, p2 >= 3` y la diferencia es 1 (ej: `(4,3)`, `(5,4)`), como en la **Tabla 2**?
-   [ ] **Victoria Correcta:** ¿El código devuelve "Player X wins" cuando un jugador tiene `>= 4` puntos Y una ventaja de `>= 2` puntos (ej: `(4,2)`, `(5,3)`, `(6,4)`), como en la **Tabla 2**?
-   [ ] **Estado Final Inmutable:** ¿La función `awardPoint` deja de cambiar el marcador visible una vez que se ha alcanzado un estado de victoria? (Verifica con la **Secuencia (e)**).
-   [ ] **Función `reset`:** ¿La función `reset` restaura los contadores a `p1=0, p2=0`, resultando en un `getScore()` de "Love–Love"?

#### **"Pitfalls" Típicos y Cómo Detectarlos:**

1.  **Pitfall: Confundir `>` con `>=` en las condiciones de Deuce/Advantage.**
    *   **Síntoma:** Tu código no entra en "Deuce" en `(3,3)` o no muestra "Advantage" en `(4,3)`.
    *   **Detección:** La **Tabla 2** te mostrará inmediatamente una discrepancia en las filas donde `p1` o `p2` son exactamente `3`.

2.  **Pitfall: Lógica de victoria incompleta (olvidar la diferencia de 2 puntos).**
    *   **Síntoma:** Tu código declara "Player 1 wins" en `(4,3)`.
    *   **Detección:** La **Tabla 2** es clara: `(4,3)` debe ser "Advantage Player 1". Si tu código no coincide, la condición `abs(p1-p2) >= 2` probablemente falta o es incorrecta.

3.  **Pitfall: No implementar la jerarquía de reglas correctamente.**
    *   **Síntoma:** El marcador muestra "Advantage Player 1" para una puntuación de `(4,2)`.
    *   **Detección:** Esto ocurre si la regla de Advantage se comprueba antes que la regla de Victoria. Las **Secuencias de Juego** y la **Tabla 2** revelarán este error, ya que el estado esperado para `(4,2)` es "Player 1 wins".
