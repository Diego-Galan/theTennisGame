// Demo.kt
// Pequeña demo por consola para probar TennisGame paso a paso.

fun main() {
    fun hr() = println("".padEnd(50, '-'))

    // Utilidad para imprimir un paso con el marcador actual
    fun show(step: String, g: TennisGame) {
        println(step.padEnd(35, ' ') + " -> " + g.getScore())
    }

    // DEMO 1: Victoria directa de Player 1
    println("DEMO 1: Victoria directa de Player 1")
    val g1 = TennisGame()
    show("Inicio", g1)
    g1.awardPoint(1); show("P1 anota (1)", g1)
    g1.awardPoint(1); show("P1 anota (2)", g1)
    g1.awardPoint(1); show("P1 anota (3)", g1)
    g1.awardPoint(1); show("P1 anota (4)", g1)
    // Intento tardío: debe ignorarse porque ya hay ganador
    g1.awardPoint(2); show("Intento tardío P2", g1)
    hr()

    // DEMO 2: Deuce -> Advantage -> Deuce -> Advantage -> Win (Player 2)
    println("DEMO 2: Deuce y ciclos de Advantage; gana P2")
    val g2 = TennisGame()
    show("Inicio", g2)
    // 3-3 para llegar a 40–40 (Deuce)
    repeat(3) { g2.awardPoint(1) }; show("P1 anota 3 (40–Love → ...)", g2)
    repeat(3) { g2.awardPoint(2) }; show("P2 anota 3 (llegamos a Deuce)", g2)
    // Advantage P1
    g2.awardPoint(1); show("P1 anota → Advantage P1", g2)
    // De vuelta a Deuce
    g2.awardPoint(2); show("P2 anota → Deuce", g2)
    // Advantage P2
    g2.awardPoint(2); show("P2 anota → Advantage P2", g2)
    // Gana P2
    g2.awardPoint(2); show("P2 anota → Player 2 wins", g2)
    hr()

    // DEMO 3 (opcional): Empates altos (4–4, 5–5 => Deuce)
    println("DEMO 3: Empates altos siguen siendo Deuce")
    val g3 = TennisGame()
    repeat(3) { g3.awardPoint(1); g3.awardPoint(2) } // 3–3 (Deuce)
    show("3–3 → Deuce", g3)
    g3.awardPoint(1); show("P1 anota → Adv P1", g3)
    g3.awardPoint(2); show("P2 anota → Deuce (4–4)", g3)
    g3.awardPoint(1); show("P1 anota → Adv P1", g3)
    g3.awardPoint(2); show("P2 anota → Deuce (5–5)", g3)
    hr()

    println("FIN DE LA DEMO ✅")
}
// FIN DEMO