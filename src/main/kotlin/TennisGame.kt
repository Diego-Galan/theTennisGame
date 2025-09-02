
import kotlin.math.abs

/**
 * Represents a single game of Tennis between two players.
 *
 * This class tracks scores for a single game and does not handle sets or matches.
 * The game state becomes final once a player wins, and further points are ignored.
 */
class TennisGame {

    private var p1Score = 0
    private var p2Score = 0

    /**
     * Awards a point to the specified player, updating the internal score.
     *
     * This call is ignored if the game has already been won.
     *
     * @param playerId The player who scored, must be 1 or 2.
     * @throws IllegalArgumentException if playerId is not 1 or 2.
     * @sample `awardPoint(1)`
     */
    fun awardPoint(playerId: Int) {
        require(playerId == 1 || playerId == 2) { "playerId must be 1 or 2" }

        if (hasWinner()) {
            return // Game is over, no more points can be awarded.
        }

        when (playerId) {
            1 -> p1Score++
            2 -> p2Score++
        }
    }

    /**
     * Returns the current score as a formatted string (e.g., "15–30", "Deuce").
     *
     * The output follows a strict priority: Win > Deuce > Advantage > Normal Score.
     *
     * @return The formatted score string.
     * @sample `val score = getScore() // "Advantage Player 1"`
     */
    fun getScore(): String {
        return when {
            hasWinner() -> "Player ${getLeadingPlayer()} wins"
            isDeuce() -> "Deuce"
            hasAdvantage() -> "Advantage Player ${getLeadingPlayer()}"
            else -> formatNormalScore()
        }
    }

    /**
     * Resets the game to its initial state (0-0).
     *
     * @sample `reset() // Score is now "Love–Love"`
     */
    fun reset() {
        p1Score = 0
        p2Score = 0
    }

    // --- Private Helper Functions ---

    private fun hasWinner(): Boolean {
        val hasEnoughPoints = p1Score >= 4 || p2Score >= 4
        val hasWinningMargin = abs(p1Score - p2Score) >= 2
        return hasEnoughPoints && hasWinningMargin
    }

    private fun isDeuce(): Boolean {
        return p1Score >= 3 && p1Score == p2Score
    }

    private fun hasAdvantage(): Boolean {
        val isPastDeuce = p1Score >= 3 && p2Score >= 3
        val hasAdvantageMargin = abs(p1Score - p2Score) == 1
        return isPastDeuce && hasAdvantageMargin
    }

    /**
     * Determines which player is currently ahead on points.
     * Assumes it's only called when there is a winner or a player with advantage.
     */
    private fun getLeadingPlayer(): Int {
        return if (p1Score > p2Score) 1 else 2
    }

    // -- Commented out for reference as requested --
    /*
    private fun winnerIdOrNull(): Int? {
        if (!hasWinner()) return null
        return if (p1Score > p2Score) 1 else 2
    }

    private fun advantagePlayerOrNull(): Int? {
        if (!hasAdvantage()) return null
        return if (p1Score > p2Score) 1 else 2
    }
    */

    private fun formatNormalScore(): String {
        return "${mapScoreToText(p1Score)}–${mapScoreToText(p2Score)}"
    }

    private fun mapScoreToText(score: Int): String {
        require(score in 0..3) { "Unexpected score: $score" }
        return when (score) {
            0 -> "Love"
            1 -> "15"
            2 -> "30"
            3 -> "40"
            else -> throw IllegalStateException("Score is out of range 0-3 after require check")
        }
    }
}
