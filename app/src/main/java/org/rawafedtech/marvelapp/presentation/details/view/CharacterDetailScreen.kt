package org.rawafedtech.marvelapp.presentation.details.view

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import org.rawafedtech.marvelapp.R
import org.rawafedtech.marvelapp.data.model.CharacterItem
import org.rawafedtech.marvelapp.presentation.details.viewmodel.DetailsViewModel

@Composable
fun CharacterDetailScreen(
    navController: NavController,
    selectedCharacterItem: CharacterItem?,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    viewModel.setCharId(selectedCharacterItem?.id!!)
    Column(
        modifier = Modifier.background(Color.DarkGray)
    ) {

        Surface(
            modifier = Modifier
                .height(200.dp)
                .background(Color.DarkGray)
        ) {
            Box {
                Crossfade(targetState = "${selectedCharacterItem?.thumbnail?.path}.${selectedCharacterItem?.thumbnail?.extension}") { imageUrl ->
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = selectedCharacterItem?.description,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
                Image(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(40.dp)
                        .align(Alignment.TopStart)
                        .clickable { navController.navigateUp() }
                )
            }
        }

        if (!viewModel.stateEventsMarvel.error.isNullOrEmpty()) {
            Toast.makeText(LocalContext.current, "Cannot Get The Events", Toast.LENGTH_LONG).show()
        }
        if (!viewModel.stateStoriesMarvel.error.isNullOrEmpty()) {
            Toast.makeText(LocalContext.current, "Cannot Get The Stories", Toast.LENGTH_LONG).show()
        }
        if (!viewModel.stateSeriesMarvel.error.isNullOrEmpty()) {
            Toast.makeText(LocalContext.current, "Something Went Series ", Toast.LENGTH_LONG).show()
        }
        if (!viewModel.stateComicsMarvel.error.isNullOrEmpty()) {
            Toast.makeText(LocalContext.current, "Something Went Comics ", Toast.LENGTH_LONG).show()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.DarkGray),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            if (!selectedCharacterItem.name.isNullOrEmpty()) {
                item {
                    CharacterInfoSection(label = "NAME", text = selectedCharacterItem.name)
                }
            }

            if (!selectedCharacterItem.description.isNullOrEmpty())
                item {
                    CharacterInfoSection(
                        label = "DESCRIPTION",
                        text = selectedCharacterItem.description
                    )
                }

            if (viewModel.stateComicsMarvel.items.isNotEmpty()) {
                item {
                    LazyRowSection(
                        title = "Comics",
                        items = viewModel.stateComicsMarvel.items,
                        isLoading = viewModel.stateComicsMarvel.isLoading,
                        endReached = viewModel.stateComicsMarvel.endReached,
                        loadMoreItems = viewModel::loadComicsNextItems
                    )
                }
            }



            if (viewModel.stateSeriesMarvel.items.isNotEmpty()) {
                item {
                    LazyRowSection(
                        title = "Series",
                        items = viewModel.stateSeriesMarvel.items,
                        isLoading = viewModel.stateSeriesMarvel.isLoading,
                        endReached = viewModel.stateSeriesMarvel.endReached,
                        loadMoreItems = viewModel::loadSeriesNextItems
                    )
                }
            }

            if (viewModel.stateStoriesMarvel.items.isNotEmpty()) {
                item {
                    LazyRowSection(
                        title = "Stories",
                        items = viewModel.stateStoriesMarvel.items,
                        isLoading = viewModel.stateStoriesMarvel.isLoading,
                        endReached = viewModel.stateStoriesMarvel.endReached,
                        loadMoreItems = viewModel::loadStoriesNextItems
                    )
                }
            }

            if (viewModel.stateEventsMarvel.items.isNotEmpty()) {
                item {
                    LazyRowSection(
                        title = "Events",
                        items = viewModel.stateEventsMarvel.items,
                        isLoading = viewModel.stateEventsMarvel.isLoading,
                        endReached = viewModel.stateEventsMarvel.endReached,
                        loadMoreItems = viewModel::loadEventsNextItems
                    )
                }
            }


        }
    }
}

@Composable
fun CharacterInfoSection(label: String, text: String) {
    Column {
        Text(
            text = label,
            color = Color.Red,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun LazyRowSection(
    title: String,
    items: List<CharacterItem>,
    isLoading: Boolean,
    endReached: Boolean,
    loadMoreItems: () -> Unit
) {
    if (items.isNotEmpty()) {
        Text(
            text = title,
            color = Color.Red,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(items) { index, item ->
                if (index >= items.size - 2 && !endReached && !isLoading) {
                    loadMoreItems()
                }
                Crossfade(targetState = item) { currentItem ->
                    Image(
                        painter = rememberAsyncImagePainter(
                            "${currentItem.thumbnail?.path}.${currentItem.thumbnail?.extension}",
                            placeholder = painterResource(R.drawable.place_holder_loading),
                            error = painterResource(R.drawable.place_holder_failed)
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,

                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Black)
                    )
                }
            }

            if (isLoading) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}