package com.sonicjar.media

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BaseTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF6200EE),
            onPrimaryContainer = Color(0xFF3700B3),
            onPrimary = Color(0xFFCCCCCC),
            secondary = Color(0xFF6200EE),
            onSecondaryContainer = Color(0xFF6200EE),
            onSecondary =Color(0xFFCCCCCC),
            tertiary = Color(0xFFCCCCCC)
        )
    ) {
        content()
    }
}