package org.rawafedtech.marvelapp.presentation.details.view

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
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
    )
    {

        Surface(
            modifier = Modifier
                .height(200.dp)
                .background(Color.DarkGray)
        ) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(model = "${selectedCharacterItem?.thumbnail?.path}.${selectedCharacterItem?.thumbnail?.extension}"),
                    contentDescription = selectedCharacterItem?.description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Image(imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(40.dp)
                        .height(40.dp)
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .align(Alignment.TopStart)
                        .clickable {
                            navController.navigateUp()
                        }


                )
            }

        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.DarkGray),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            if (!selectedCharacterItem?.name.isNullOrEmpty()) {
                item {
                    Text(
                        text = "NAME",
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,

                        )
                    Text(
                        text = selectedCharacterItem?.name ?: "",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
            if (!selectedCharacterItem?.description.isNullOrEmpty())
                item {
                    Text(
                        text = "DESCRIPTION",
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = selectedCharacterItem?.description ?: "",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            if (viewModel.stateComicsMarvel.items.isNotEmpty()) {
                item {
                    Text(
                        text = "Comics",
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        itemsIndexed(viewModel.stateComicsMarvel.items) { i, item ->
                            if (i >= viewModel.stateComicsMarvel.items.size - 1 && !viewModel.stateComicsMarvel.endReached && !viewModel.stateComicsMarvel.isLoading) {
                                viewModel.loadComicsNextItems()
                            }
                            Image(
                                painter = rememberAsyncImagePainter("${item.thumbnail?.path}.${item.thumbnail?.extension}"),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(Color.Black)
                            )
                        }
                        item {
                            if (viewModel.stateComicsMarvel.isLoading) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }

                }


            }
            if (viewModel.stateSeriesMarvel.items.isNotEmpty()) {
                item {
                    Text(
                        text = "Series",
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        itemsIndexed(viewModel.stateSeriesMarvel.items) { i, item ->
                            if (i >= viewModel.stateSeriesMarvel.items.size - 1 && !viewModel.stateSeriesMarvel.endReached && !viewModel.stateSeriesMarvel.isLoading) {
                                viewModel.loadSeriesNextItems()
                            }
                            Image(
                                painter = rememberAsyncImagePainter("${item.thumbnail?.path}.${item.thumbnail?.extension}"),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(Color.Black)
                            )
//            CharacterItem(character = item, navController = navController)
                        }
                        item {
                            if (viewModel.stateSeriesMarvel.isLoading) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }
            if (viewModel.stateStoriesMarvel.items.isNotEmpty()) {
                item {
                    Text(
                        text = "Stories",
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        itemsIndexed(viewModel.stateStoriesMarvel.items) { i, item ->
                            if (i >= viewModel.stateStoriesMarvel.items.size - 1 && !viewModel.stateStoriesMarvel.endReached && !viewModel.stateStoriesMarvel.isLoading) {
                                viewModel.loadStoriesNextItems()
                            }
                            Image(
                                painter = rememberAsyncImagePainter("${item.thumbnail?.path}.${item.thumbnail?.extension}"),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(Color.Black)
                            )
                        }
                        item {
                            if (viewModel.stateStoriesMarvel.isLoading) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }
            if (viewModel.stateEventsMarvel.items.isNotEmpty()) {
                item {
                    Text(
                        text = "Events",
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        itemsIndexed(viewModel.stateEventsMarvel.items) { i, item ->
                            if (i >= viewModel.stateEventsMarvel.items.size - 1 && !viewModel.stateEventsMarvel.endReached && !viewModel.stateEventsMarvel.isLoading) {
                                viewModel.loadEventsNextItems()
                            }
                            Image(
                                painter = rememberAsyncImagePainter("${item.thumbnail?.path}.${item.thumbnail?.extension}"),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(Color.Black)
                            )
                        }
                        item {
                            if (viewModel.stateEventsMarvel.isLoading) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }

            }
            item {
                Text(
                    text = "RELATED LINKS",
                    color = Color.Red,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
            }

        }

    }

}