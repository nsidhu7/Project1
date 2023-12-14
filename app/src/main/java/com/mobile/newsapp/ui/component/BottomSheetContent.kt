package com.mobile.newsapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mobile.newsapp.domain.model.Article
import com.mobile.newsapp.ui.news_screen.NewsScreenEvent

@Composable
fun BottomSheetContent(
    article: Article,
    onReadFullStoryButtonClicked: () -> Unit
){
    // Surface to provide a background and elevation for the content
    Surface(modifier = Modifier.padding(16.dp)) {
        // Column to vertically stack child elements
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Display the article title with medium typography
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium
            )
            // Spacer to add vertical space between elements
            Spacer(modifier = Modifier.height(8.dp))
            // Display the article description with medium body typography
            Text(
                text = article.description ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            // Spacer to add vertical space between elements
            Spacer(modifier = Modifier.height(8.dp))
            // ImageHolder to display the article image
            ImageHolder(imageUrl = article.urlToImage)
            // Spacer to add vertical space between elements
            Spacer(modifier = Modifier.height(8.dp))
            // Display the article content with medium body typography
            Text(
                text = article.content ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            // Spacer to add vertical space between elements
            Spacer(modifier = Modifier.height(8.dp))
            // Row to horizontally align child elements with space between them
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Display the article author with small bold typography
                Text(
                    text = article.author ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                // Display the article source name with small bold typography
                Text(
                    text = article.source.name ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                // Spacer to add vertical space between elements
                Spacer(modifier = Modifier.height(8.dp))
                // Button to trigger the "Read Full Story" action
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onReadFullStoryButtonClicked
                ) {
                    Text(text = "Read Full Story")
                }
            }
        }
    }
}
