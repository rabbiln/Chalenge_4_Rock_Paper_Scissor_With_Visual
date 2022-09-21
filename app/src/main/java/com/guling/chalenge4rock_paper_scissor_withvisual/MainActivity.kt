package com.guling.chalenge4rock_paper_scissor_withvisual

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

   /* fun run() {
        compare_hands()
        result()
        val rock = 0
        val paper = (-1)
        val scissors = (1)
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            MainActivity().run()
        }
    }

    private fun compare_hands(hand1, hand2) {
        val hand1 = IOUtils.readString()
        var winning_hand = 2
        if (hand1 != hand2){
            if((hand1) == (hand2)) {
                winning_hand = max(hand1, hand2)
            }
        }
         else {
            winning_hand = min(hand1, hand2)
            return winning_hand
        }
    }*/
}

