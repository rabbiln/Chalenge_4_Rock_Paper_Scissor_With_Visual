package com.guling.chalenge4rock_paper_scissor_withvisual.ui.game

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.guling.chalenge4rock_paper_scissor_withvisual.ComputerRpsManager
import com.guling.chalenge4rock_paper_scissor_withvisual.IOUtils
import com.guling.chalenge4rock_paper_scissor_withvisual.R
import com.guling.chalenge4rock_paper_scissor_withvisual.RpsManager
import com.guling.chalenge4rock_paper_scissor_withvisual.databinding.ActivityRpsBinding
import com.guling.chalenge4rock_paper_scissor_withvisual.enum.GameState
import com.guling.chalenge4rock_paper_scissor_withvisual.enum.PlayerSide
import com.guling.chalenge4rock_paper_scissor_withvisual.enum.PlayerState
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
        setOnClickListeners()
        supportActionBar?.hide()
    }

    private fun setOnClickListeners() {
        binding.ivRock.setOnClickListener {
            rpsManager.movePlayerRock()
        }
        binding.ivPaper.setOnClickListener {
            rpsManager.movePlayerPaper()
        }
        binding.ivScissor.setOnClickListener {
            rpsManager.movePlayerScissor()
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
        binding.tvStatusGame.text = when (gameState) {
            GameState.IDLE -> getString(R.string.VS)
            GameState.STARTED -> getString(R.string.VS)
            GameState.PLAYERWIN -> getString(R.string.text_win)
            GameState.PLAYERLOSE -> getString(R.string.text_lose)
            GameState.DRAW -> getString(R.string.text_draw)
            GameState.FINISHED -> getString(R.string.VS)
        }
    }

    override fun onGameFinished(gameState: GameState, winner: Player) {
        if (winner.playerSide == PlayerSide.PLAYER) {
            binding.tvStatusGame.text = getString(R.string.text_win)
        }
        else if (winner.playerSide == PlayerSide.COMPUTER) {
            binding.tvStatusGame.text = getString(R.string.text_lose)
        }
        else {
            binding.tvStatusGame.text = getString(R.string.text_draw)
        }
    }

    private fun setCharacterState(player: Player, iconDrawableRes: Int) {
        val ivCharRock: ImageView?
        val ivCharPaper: ImageView?
        val ivCharScissor: ImageView?
        val drawable = ContextCompat.getDrawable(this, iconDrawableRes)

        if (player.playerSide == PlayerSide.PLAYER) {
            ivCharRock = binding.ivRock
            ivCharPaper = binding.ivPaper
            ivCharScissor = binding.ivScissor
        } else {
            ivCharRock = binding.ivRockRigt
            ivCharPaper = binding.ivPaperRight
            ivCharScissor = binding.ivScissorRight
        }

        when (player.playerState) {
            PlayerState.IDLE -> {
                ivCharRock.visibility = View.VISIBLE
                ivCharPaper.visibility = View.VISIBLE
                ivCharScissor.visibility = View.VISIBLE
            }
            PlayerState.ROCK -> {
                ivCharRock.visibility = View.VISIBLE
                ivCharPaper.visibility = View.VISIBLE
                ivCharScissor.visibility = View.VISIBLE
                ivCharRock.setImageDrawable(drawable)
            }
            PlayerState.PAPER -> {
                ivCharRock.visibility = View.VISIBLE
                ivCharPaper.visibility = View.VISIBLE
                ivCharScissor.visibility = View.VISIBLE
                ivCharPaper.setImageDrawable(drawable)

            }
            PlayerState.SCISSOR -> {
                ivCharRock.visibility = View.VISIBLE
                ivCharPaper.visibility = View.VISIBLE
                ivCharScissor.visibility = View.VISIBLE
                ivCharScissor.setImageDrawable(drawable)
            }
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

