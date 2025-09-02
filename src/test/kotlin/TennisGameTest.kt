import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.BeforeTest

class TennisGameTest {

    private lateinit var game: TennisGame

    @BeforeTest
    fun setUp() {
        game = TennisGame()
    }

    private fun awardPoints(player: Int, times: Int) {
        repeat(times) {
            game.awardPoint(player)
        }
    }

    @Test
    fun `initial score is Love-Love`() {
        assertEquals("Love–Love", game.getScore())
    }

    @Test
    fun `scores up to 30-30`() {
        awardPoints(1, 1)
        assertEquals("15–Love", game.getScore())
        awardPoints(2, 1)
        assertEquals("15–15", game.getScore())
        awardPoints(1, 1)
        assertEquals("30–15", game.getScore())
        awardPoints(2, 1)
        assertEquals("30–30", game.getScore())
    }

    @Test
    fun `direct win for player 1`() {
        awardPoints(1, 4)
        assertEquals("Player 1 wins", game.getScore())
    }

    @Test
    fun `entering deuce`() {
        awardPoints(1, 3)
        awardPoints(2, 2)
        assertEquals("40–30", game.getScore())
        game.awardPoint(2)
        assertEquals("Deuce", game.getScore())
    }

    @Test
    fun `advantage cycle and win`() {
        awardPoints(1, 3)
        awardPoints(2, 3)
        assertEquals("Deuce", game.getScore())

        game.awardPoint(1)
        assertEquals("Advantage Player 1", game.getScore())

        game.awardPoint(2)
        assertEquals("Deuce", game.getScore())

        game.awardPoint(1)
        assertEquals("Advantage Player 1", game.getScore())

        game.awardPoint(1)
        assertEquals("Player 1 wins", game.getScore())
    }

    @Test
    fun `awardPoint is ignored after a win`() {
        awardPoints(1, 4)
        assertEquals("Player 1 wins", game.getScore())

        // Try to award more points
        game.awardPoint(2)
        game.awardPoint(1)

        // Score should not change
        assertEquals("Player 1 wins", game.getScore())
    }

    @Test
    fun `reset returns to Love-Love after a game`() {
        awardPoints(1, 4)
        game.reset()
        assertEquals("Love–Love", game.getScore())
    }
    
    @Test
    fun `player 2 wins after deuce`() {
        awardPoints(1, 3)
        awardPoints(2, 3)
        assertEquals("Deuce", game.getScore())

        game.awardPoint(2)
        assertEquals("Advantage Player 2", game.getScore())

        game.awardPoint(2)
        assertEquals("Player 2 wins", game.getScore())
    }
}
