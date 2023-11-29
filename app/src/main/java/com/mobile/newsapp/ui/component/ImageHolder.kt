package com.mobile.newsapp.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mobile.newsapp.R
import retrofit2.http.Url

@Composable
fun ImageHolder(
    imageUrl: String?, // The URL of the image to be loaded
    modifier: Modifier = Modifier // Modifier for customizing the layout of the ImageHolder
) {
    AsyncImage(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Image", // Accessibility content description for the image
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .aspectRatio(16 / 9f),
        placeholder = painterResource(id = R.drawable.loading), // Placeholder image resource while loading
        error = painterResource(id = R.drawable.news) // Error image resource to display on image load failure
    )
}
