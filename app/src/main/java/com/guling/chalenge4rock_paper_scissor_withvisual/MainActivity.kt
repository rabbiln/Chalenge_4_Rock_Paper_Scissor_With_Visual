package com.guling.chalenge4rock_paper_scissor_withvisual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun Rock()= 0
    fun Paper()= (-1)
    fun Scissors()= (1)

    fun compare_hands(hand1, hand2):
            hand1=inp
    winning_hand = 2  # this represents a draw
    if hand1 != hand2:
    if abs(hand1) == abs(hand2):
    winning_hand = max(hand1, hand2)
    else:
    winning_hand = min(hand1, hand2)
    return winning_hand
    }

