package org.rawafedtech.marvelapp.presentation.search.view

import SearchView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import org.rawafedtech.marvelapp.R
import org.rawafedtech.marvelapp.data.model.CharacterItem
import org.rawafedtech.marvelapp.presentation.search.viewmodel.SearchViewModel
import org.rawafedtech.marvelapp.ui.navigation.NavigationScreens
import org.rawafedtech.marvelapp.ui.theme.Black80
import org.rawafedtech.marvelapp.utils.ParallelogramShape


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {

    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black80)
            ) {
                item {
                    SearchView(onWordChanged = { searchWord ->
                        viewModel.loadNextItems(searchWord)
                    }, onCancelClicked = {
                        navController.navigateUp()
                    })
                }

                itemsIndexed(viewModel.statePaging.items) { i, item ->
                    if (i >= viewModel.statePaging.items.size - 1 && !viewModel.statePaging.endReached && !viewModel.statePaging.isLoading) {
                        viewModel.loadNextItems(searchingWord = viewModel.word)
                    }
                    CharacterItem(
                        character = item,
                        navController = navController
                    )
                }

                item {
                    if (viewModel.statePaging.isLoading) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }


        }

    }
}

@Composable
fun CharacterItem(
    character: CharacterItem,
    navController: NavController
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate(
                NavigationScreens.Details.screenRoute + "?character=${
                    Gson().toJson(
                        character
                    )
                }"
            )
        }) {
        Image(
            painter = rememberAsyncImagePainter(model = "${character.thumbnail?.path}.${character.thumbnail?.extension}"),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(15.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(ParallelogramShape(slantAngle = 15f)) // Apply the custom parallelogram shape
                    .background(White)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = character.name ?: "",
                    color = colorResource(R.color.black),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

            }
        }

    }
}
