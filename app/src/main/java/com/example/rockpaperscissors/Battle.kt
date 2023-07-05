package com.example.rockpaperscissors

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun Battle() {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp

    var startAnimation by remember {
        mutableStateOf(true)
    }

    var x by remember { mutableStateOf(0) }
    var y by remember { mutableStateOf(0) }

    var finalY by remember {
        mutableStateOf(screenHeight)
    }
    var finalX by remember {
        mutableStateOf(screenWidth - 100)
    }

    val rockOffSetY by animateIntAsState(
        targetValue = if (startAnimation) 0 else finalY,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(2500, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        )
    )

    val rockOffSetX by animateIntAsState(
        targetValue = if (startAnimation) 0 else finalX,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(2500, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        ),
    )

    LaunchedEffect(key1 = rockOffSetY, key2 = rockOffSetY, block = {
        startAnimation = false
        if (rockOffSetY == finalY || rockOffSetX == finalX) {
            val randX = Random.nextInt(from = 0, until = screenWidth)
            val randY = Random.nextInt(from = 0, until = screenHeight)
            finalX = randX
            finalY = randY


            x = rockOffSetX
            y = rockOffSetY
        } else {
            x = rockOffSetX
            y = rockOffSetY
        }

    })


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Entity(
            modifier = Modifier
                .offset(y = y.dp, x = x.dp),
            icon = R.drawable.rock
        )

    }

}


@Composable
fun Entity(
    modifier: Modifier,
    @DrawableRes icon: Int
) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
        contentScale = ContentScale.Fit
    )
}