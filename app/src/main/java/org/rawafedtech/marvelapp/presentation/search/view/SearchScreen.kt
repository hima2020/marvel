package org.rawafedtech.marvelapp.presentation.search.view

import SearchView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
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


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black80)
            ) {
                item {
                    SearchView(
                        onWordChanged = { searchWord -> viewModel.loadNextItems(searchWord) },
                        onCancelClicked = { navController.navigateUp() }
                    )
                }

                itemsIndexed(viewModel.statePaging.items) { i, item ->
                    if (i >= viewModel.statePaging.items.size - 1 && !viewModel.statePaging.endReached && !viewModel.statePaging.isLoading) {
                        viewModel.loadNextItems(searchingWord = viewModel.word)
                    }
                    CharachterSearchItem(
                        character = item,
                        navController = navController
                    )
                }

                item {
                    AnimatedVisibility(visible = viewModel.statePaging.isLoading) {
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
fun CharachterSearchItem(
    character: CharacterItem,
    navController: NavController
) {
    var isPressed by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .clickable {
                navController.navigate(
                    NavigationScreens.Details.screenRoute + "?character=${Gson().toJson(character)}"
                )
            }
            .animateContentSize()
            .pointerInput(Unit) { detectTapGestures(onPress = { isPressed = true }) }
    ) {
        Crossfade(targetState = "${character.thumbnail?.path}.${character.thumbnail?.extension}") { imageUrl ->
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .scale(if (isPressed) 1.05f else 1f)
            )
        }

        Column(modifier = Modifier.padding(15.dp)) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .shadow(if (isPressed) 8.dp else 2.dp)
            ) {
                Text(
                    text = character.name.orEmpty(),
                    color = colorResource(R.color.white),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                )
            }
        }
    }
}
