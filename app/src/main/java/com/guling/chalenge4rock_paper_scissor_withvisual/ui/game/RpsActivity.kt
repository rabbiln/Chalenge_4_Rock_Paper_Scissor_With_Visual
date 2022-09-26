package com.guling.chalenge4rock_paper_scissor_withvisual.ui.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.guling.chalenge4rock_paper_scissor_withvisual.ComputerRpsManager
import com.guling.chalenge4rock_paper_scissor_withvisual.IOUtils
import com.guling.chalenge4rock_paper_scissor_withvisual.R
import com.guling.chalenge4rock_paper_scissor_withvisual.RpsManager
import com.guling.chalenge4rock_paper_scissor_withvisual.databinding.ActivityRpsBinding
import com.guling.chalenge4rock_paper_scissor_withvisual.enum.GameState
import com.guling.chalenge4rock_paper_scissor_withvisual.model.Player

class RpsActivity : AppCompatActivity() {
    private val binding: ActivityRpsBinding by lazy {
        ActivityRpsBinding.inflate(layoutInflater)
    }

    private val rpsManager: RpsManager by lazy {
        ComputerRpsManager(this)
    }

    override fun oncreate(savedInstancesState: Bundle?){
        super.onCreate(savedInstancesState)
        setContentView(binding.root)
        rpsManager.initGame()
        setOnClickListener()
        supportActionBar?.hide()
    }

    private fun setOnClickListeners() {
        binding.ivRock.setOnClickListener {
            rpsManager.movePlayerToTop()
        }
        binding.ivPaper.setOnClickListener {
            rpsManager.movePlayerToBottom()
        }
        binding.ivScissor.setOnClickListener {
            rpsManager.movePlayerToBottom()
        }
        binding.ivRefresh.setOnClickListener {
            rpsManager.startOrRestartGame()
        }
    }

    override fun onPlayerStatusChanged(player: Player, iconDrawableRes: Int) {
        setCharacterState(player, iconDrawableRes)
    }

    override fun onGameStateChanged(gameState: GameState) {
        binding.tvStatusGame.text = ""
        binding.tvActionGame.text = when (gameState) {
            GameState.IDLE -> getString(R.string.)
            GameState.STARTED -> getString(R.string.text_fire)
            GameState.FINISHED -> getString(R.string.text_restart)
        }
    }

    override fun onGameFinished(gameState: GameState, winner: Player) {
        if (winner.playerSide == PlayerSide.PLAYER_ONE) {
            binding.tvStatusGame.text = getString(R.string.text_you_win)
        } else {
            binding.tvStatusGame.text = getString(R.string.text_you_lose)
        }
    }

    fun run() {
        inputPlayer()
        val rock = 0
        val paper = 1
        val scissors = 2
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            RpsActivity().run()
        }
    }

    private fun inputPlayer() {
        val inputPlayer1 = IOUtils.readString()
        val inputPlayer2 = IOUtils.readString()
        println("Masukkan pemain 1 : $inputPlayer1")
        println("Masukkan pemain 2 : $inputPlayer2")
        if (inputPlayer1 == "gunting" && inputPlayer2 == "batu") {
            println("Pemain 2 MENANG!")
        }
        if (inputPlayer1 == "batu" && inputPlayer2 == "gunting") {
            println("Pemain 1 MENANG!")
        }
        if (inputPlayer1 == "kertas" && inputPlayer2 == "gunting") {
            println("Pemain 2 MENANG!")
        }
        if (inputPlayer1 == "gunting" && inputPlayer2 == "kertas") {
            println("Pemain 1 MENANG!")
        }
        if (inputPlayer1 == "kertas" && inputPlayer2 == "batu") {
            println("Pemain 1 MENANG!")
        }
        if (inputPlayer1 == "batu" && inputPlayer2 == "kertas") {
            println("Pemain 2 MENANG!")
        }
        if (inputPlayer1 == inputPlayer2) {
            println("DRAW!")
        }
    }
}

