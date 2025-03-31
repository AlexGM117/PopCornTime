package com.example.popcornchallenge.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.popcornchallenge.presentation.theme.Purple40

@Composable
fun ItemDetailScreen(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .background(Purple40)
            .size(150.dp))
        Text(text = "Item Detail Screen")
    }
}

@Composable
@Preview(
    showSystemUi = true,
    showBackground = true,
    device = PIXEL_4_XL
)
private fun ItemDetailScreenPreview() {
    ItemDetailScreen(innerPadding = PaddingValues(all = 16.dp))
}