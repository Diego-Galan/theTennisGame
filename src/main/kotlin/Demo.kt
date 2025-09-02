// Demo.kt
// A small console demo to test TennisGame step-by-step.

fun main() {
    fun hr() = println("".padEnd(50, '-'))

    // Utility to print a step with the current score
    fun show(step: String, g: TennisGame) {
        println(step.padEnd(35, ' ') + " -> " + g.getScore())
    }

    // DEMO 1: Straight win for Player 1
    println("DEMO 1: Straight win for Player 1")
    val g1 = TennisGame()
    show("Start", g1)
    g1.awardPoint(1); show("P1 scores (1)", g1)
    g1.awardPoint(1); show("P1 scores (2)", g1)
    g1.awardPoint(1); show("P1 scores (3)", g1)
    g1.awardPoint(1); show("P1 scores (4)", g1)
    // Late attempt: should be ignored as the game is already won
    g1.awardPoint(2); show("Late attempt P2", g1)
    hr()

    // DEMO 2: Deuce/Advantage cycles; Player 2 wins
    println("DEMO 2: Deuce/Advantage cycles; Player 2 wins")
    val g2 = TennisGame()
    show("Start", g2)
    // 3-3 to reach 40–40 (Deuce)
    repeat(3) { g2.awardPoint(1) }; show("P1 scores 3 times (40–Love...)", g2)
    repeat(3) { g2.awardPoint(2) }; show("P2 scores 3 times (reaching Deuce)", g2)
    // Advantage P1
    g2.awardPoint(1); show("P1 scores -> Advantage P1", g2)
    // Back to Deuce
    g2.awardPoint(2); show("P2 scores -> Deuce", g2)
    // Advantage P2
    g2.awardPoint(2); show("P2 scores -> Advantage P2", g2)
    // P2 wins
    g2.awardPoint(2); show("P2 scores -> Player 2 wins", g2)
    hr()

    // DEMO 3: High ties remain Deuce
    println("DEMO 3: High ties remain Deuce")
    val g3 = TennisGame()
    repeat(3) { g3.awardPoint(1); g3.awardPoint(2) } // 3–3 (Deuce)
    show("3–3 -> Deuce", g3)
    g3.awardPoint(1); show("P1 scores -> Adv P1", g3)
    g3.awardPoint(2); show("P2 scores -> Deuce (4–4)", g3)
    g3.awardPoint(1); show("P1 scores -> Adv P1", g3)
    g3.awardPoint(2); show("P2 scores -> Deuce (5–5)", g3)
    hr()

    println("DEMO FINISHED ✅")
}
// END DEMO
