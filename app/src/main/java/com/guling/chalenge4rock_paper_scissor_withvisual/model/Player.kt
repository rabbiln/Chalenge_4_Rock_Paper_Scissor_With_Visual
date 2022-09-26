package com.guling.chalenge4rock_paper_scissor_withvisual.model

import com.guling.chalenge4rock_paper_scissor_withvisual.enum.PlayerSide
import com.guling.chalenge4rock_paper_scissor_withvisual.enum.PlayerState

data class Player(
    val playerSide: PlayerSide,
    var playerState: PlayerState,
    )
