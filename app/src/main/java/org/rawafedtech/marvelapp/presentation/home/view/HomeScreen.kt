package org.rawafedtech.marvelapp.presentation.home.view

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import org.rawafedtech.marvelapp.R
import org.rawafedtech.marvelapp.components.LoadingView
import org.rawafedtech.marvelapp.components.ErrorPlaceholder
import org.rawafedtech.marvelapp.data.model.CharacterItem
import org.rawafedtech.marvelapp.presentation.home.viewmodel.HomeViewModel
import org.rawafedtech.marvelapp.ui.navigation.NavigationScreens
import org.rawafedtech.marvelapp.ui.theme.Black80
import org.rawafedtech.marvelapp.utils.ParallelogramShape


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.statePaging
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (state.isLoading) {
                LoadingView(message = stringResource(R.string.empty))
            } else {
                Column (modifier = Modifier.background(Black80)){
                    Header { navController.navigate(NavigationScreens.Search.screenRoute) }
                    MarvelCharacterList(
                            items = state.items,
                        error = state.error ?: "",
                        endReached = state.endReached,
                        isLoading = state.isLoading,
                        onLoadMore = { viewModel.loadNextItems() },
                        onItemClick = { character ->
                            navController.navigate(
                                NavigationScreens.Details.screenRoute + "?character=${
                                    Gson().toJson(
                                        character
                                    )
                                }"
                            )
                        }
                    )
                }

            }
        }
    }
}

@Composable
fun MarvelCharacterList(
    items: List<CharacterItem>,
    error: String?,
    endReached: Boolean,
    isLoading: Boolean,
    onLoadMore: () -> Unit,
    onItemClick: (CharacterItem) -> Unit,
) {
    if (!error.isNullOrEmpty()){
        Box(modifier = Modifier.fillMaxSize()) {
            ErrorPlaceholder(
                errorMessage = "Something went wrong. Please try again.",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Black80)
    ) {
            itemsIndexed(items) { i, item ->
                if (i >= items.size - 3 && !endReached && !isLoading) {
                    onLoadMore()
                }
                CharacterItem(character = item, onClick = { onItemClick(item) })
            }

            item {
                if (isLoading) {
                    LoadingIndicator()
                }
            }
        }

}

@Composable
fun Header(onSearchClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.marvel_logo),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier
                .height(50.dp)
                .weight(1.7f, true)
        )

        IconButton(onClick = onSearchClick, modifier = Modifier.weight(0.3f, true)) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.app_name),
                tint = Color.Red
            )
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterItem,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = "${character.thumbnail?.path}.${character.thumbnail?.extension}",
                placeholder = painterResource(R.drawable.place_holder_loading),
                error = painterResource(R.drawable.place_holder_failed)
            ),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        CharacterInfoOverlay(character.name, modifier = Modifier.align(Alignment.BottomStart))
    }
}

@Composable
fun CharacterInfoOverlay(name: String?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(15.dp)
    ) {

        Box(
            modifier = Modifier
                .clip(ParallelogramShape(slantAngle = 15f))
                .background(White)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = name.orEmpty(),
                color = colorResource(R.color.black),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun LoadingIndicator() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}