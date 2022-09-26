package com.guling.chalenge4rock_paper_scissor_withvisual

import com.guling.chalenge4rock_paper_scissor_withvisual.enum.GameState
import com.guling.chalenge4rock_paper_scissor_withvisual.enum.PlayerSide
import com.guling.chalenge4rock_paper_scissor_withvisual.enum.PlayerState
import com.guling.chalenge4rock_paper_scissor_withvisual.model.Player
import com.guling.chalenge4rock_paper_scissor_withvisual.ui.game.RpsActivity
import kotlin.random.Random

interface RpsManager {
    fun initGame()
    fun startOrRestartGame()
}

    interface RpsListener {
        fun onPlayerStatusChanged(player: Player, iconDrawableRes: Int)
        fun onGameStateChanged(gameState: GameState)
        fun onGameFinished(GameState: GameState, Winner: Player)
    }

    class ComputerRpsManager(
        private val listener: RpsActivity,
    ) : RpsManager {
        private lateinit var player: Player

        private lateinit var computer: Player

        private lateinit var gameState: GameState


        override fun initGame() {
            setGameState(GameState.IDLE)
            player = Player(PlayerSide.PLAYER, PlayerState.IDLE)
            computer = Player(PlayerSide.COMPUTER, PlayerState.IDLE)
            notifyPlayerDataChanged()
            setGameState(GameState.STARTED)
        }



        private fun notifyPlayerDataChanged() {
            listener.onPlayerStatusChanged(
                player,
                getPlayerDrawableByState(player.playerState)
            )
            listener.onPlayerStatusChanged(
                computer,
                getComputerDrawableByState(computer.playerState)
            )
        }
        private fun setPlayerState(
            playerState: PlayerState = player.playerState
        ) {
            player.apply {
                this.playerState = playerState
            }
            listener.onPlayerStatusChanged(
                player,
                getPlayerDrawableByState(player.playerState)
            )
        }

        private fun setComputerState(
            playerState: PlayerState = computer.playerState
        ) {
            computer.apply {
                this.playerState = playerState
            }
            listener.onPlayerStatusChanged(
                computer,
                getComputerDrawableByState(computer.playerState)
            )
        }


        private fun getPlayerDrawableByState(playerState: PlayerState): Int {
            return when (playerState) {
                PlayerState.IDLE -> R.drawable.ic_launcher_background
                PlayerState.ROCK -> R.drawable.ic_rock
                PlayerState.PAPER -> R.drawable.ic_paper
                PlayerState.SCISSOR -> R.drawable.ic_scissor

            }
        }

        private fun getComputerDrawableByState(playerState: PlayerState): Int {
            return when (playerState) {
                PlayerState.IDLE -> R.drawable.ic_launcher_background
                PlayerState.ROCK -> R.drawable.ic_rock
                PlayerState.PAPER -> R.drawable.ic_paper
                PlayerState.SCISSOR -> R.drawable.ic_scissor
            }
        }

        private fun getPlayerStateByOrdinal(index: Int): PlayerState {
            return PlayerState.values()[index]
        }

        private fun setGameState(newGameState: GameState) {
            gameState = newGameState
            listener.onGameStateChanged(gameState)
        }

        private fun startGame() {
            computer.apply {
                playerState = getComputerState()
            }
            checkPlayerWinner()
        }


        private fun getComputerState(): PlayerState {
            val randomState = Random.nextInt(0, until = PlayerState.values().size)
            return getPlayerStateByOrdinal(randomState)
        }

        private fun checkPlayerWinner() {
            val draw = if (player.playerState == computer.playerState) {
                player
            } else {
                computer
            }
            setGameState(GameState.FINISHED)
            listener.onGameFinished(gameState, draw)
        }

        private fun resetGame() {
            initGame()
        }

    override fun startOrRestartGame() {
        if (gameState != GameState.FINISHED) {
            startGame()
        } else {
            resetGame()
        }
    }
}