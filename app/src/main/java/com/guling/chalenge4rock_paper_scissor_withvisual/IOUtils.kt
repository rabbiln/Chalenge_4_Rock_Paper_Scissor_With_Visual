package com.guling.chalenge4rock_paper_scissor_withvisual


object IOUtils {
    fun readString(default: String = ""): String {
        return readLine() ?: default
    }
}