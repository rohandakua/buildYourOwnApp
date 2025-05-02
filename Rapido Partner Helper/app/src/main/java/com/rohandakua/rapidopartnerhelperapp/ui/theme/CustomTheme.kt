package com.rohandakua.rapidopartnerhelperapp.ui.theme

import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rohandakua.rapidopartnerhelperapp.R

val LexendDeca = FontFamily(
    Font(R.font.lexend_deca, FontWeight.Normal)
)
val typography = Typography(bodyMedium = Typography().bodyMedium.copy(fontFamily = LexendDeca))


@Composable
fun CustomTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = typography,
        content = content
    )
}