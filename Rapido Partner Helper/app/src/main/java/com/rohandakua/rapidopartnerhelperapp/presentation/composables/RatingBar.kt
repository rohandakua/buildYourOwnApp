package com.rohandakua.rapidopartnerhelperapp.presentation.composables

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import com.rohandakua.rapidopartnerhelperapp.ui.theme.mainTextColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.rohandakua.rapidopartnerhelperapp.R
import com.rohandakua.rapidopartnerhelperapp.domain.helperFunctions.gradientBrush

@Composable
@Preview
fun RatingBar(
    rating: Double = .5,
    modifier: Modifier = Modifier,
    starSize: Dp = 24.dp,
) {
    val fullStars = rating.toInt()
    val hasHalfStar = (rating - fullStars) >= 0.5
    val emptyStars = 5 - fullStars - if (hasHalfStar) 1 else 0

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        repeat(fullStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                modifier = Modifier.size(starSize)
                    .drawBehind {
                        drawCircle(
                            brush = gradientBrush,
                            alpha = 1f
                        )
                    }
            )
            Spacer(Modifier.size(10.dp))

        }

        if (hasHalfStar) {
            Icon(
                painter = painterResource(id = R.drawable.star_half_24px),
                contentDescription = "add new ride", tint = androidx.compose.ui.graphics.Color.Black,
                modifier = Modifier.size(starSize)
                    .drawBehind {
                        drawCircle(
                            brush = gradientBrush,
                            alpha = 1f
                        )
                    }

            )
            Spacer(Modifier.size(10.dp))
        }

        repeat(emptyStars) {
            Icon(
                painter = painterResource(id = R.drawable.star_rate_24px),
                contentDescription = "add new ride", tint = androidx.compose.ui.graphics.Color.Black,
                modifier = Modifier.size(starSize)
                    .drawBehind {
                        drawCircle(
                            brush = gradientBrush,
                            alpha = 1f
                        )
                    }

            )
            Spacer(Modifier.size(10.dp))
        }
    }
}
