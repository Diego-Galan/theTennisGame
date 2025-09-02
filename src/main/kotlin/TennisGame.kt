
import kotlin.math.abs

/**
 * Represents a single game of Tennis between two players.
 *
 * This class tracks scores based on points awarded and formats the output
 * according to standard tennis rules: Love, 15, 30, 40, Deuce, Advantage, and Win.
 * The game state becomes final once a player wins.
 */
class TennisGame {

    private var p1Score = 0
    private var p2Score = 0

    /**
     * Awards a point to the specified player.
     *
     * @param playerId The player who scored, either 1 or 2.
     *                 Calls are ignored if the game has already been won.
     */
    fun awardPoint(playerId: Int) {
        if (hasWinner()) {
            return // Game is over, no more points can be awarded.
        }

        when (playerId) {
            1 -> p1Score++
            2 -> p2Score++
        }
    }

    /**
     * Returns the current score as a formatted string.
     *
     * The output follows a strict priority: Win > Deuce > Advantage > Normal Score.
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
     * Resets the game to its initial state (0-0, "Love–Love").
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

    // -- discarded because of redundance --
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
        return when (score) {
            0 -> "Love"
            1 -> "15"
            2 -> "30"
            3 -> "40"
            else -> "" // Should not be reached in normal flow
        }
    }
}
