package com.mobile.newsapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mobile.newsapp.domain.model.Article

@Composable
fun NewsArticleCard(
    modifier: Modifier = Modifier, // Modifier for customizing the layout of the card
    article: Article, // The news article to be displayed
    onCardClicked: (Article) -> Unit // Callback for when the card is clicked

    ){
    Card(
        modifier = modifier.clickable { onCardClicked(article) } // Clickable card with the specified modifier
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Display the image associated with the news article
            ImageHolder(imageUrl = article.urlToImage)
            // Display the title of the news article with specified text style and layout constraints
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
                )
            // Add spacing between the title and the source/publishedAt information
            Spacer(modifier = Modifier.height(8.dp))
            // Display the source name and publishedAt information in a row with specified layout constraints
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Display the source name with specified text style
                Text(
                    text = article.source.name ?: "",
                    style = MaterialTheme.typography.bodySmall,
                )
                // Display the publishedAt information with specified text style
                Text(
                    text = article.publishedAt ?: "",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }

    }
}