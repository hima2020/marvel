package org.rawafedtech.marvelapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.rawafedtech.marvelapp.R

@Composable
fun ErrorPlaceholder(
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error), // Replace with your error image resource
            contentDescription = "Error Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = errorMessage,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}