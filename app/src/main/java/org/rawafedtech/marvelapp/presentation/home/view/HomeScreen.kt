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
import org.rawafedtech.marvelapp.R
import org.rawafedtech.marvelapp.components.EmptyView
import org.rawafedtech.marvelapp.data.model.CharacterItem
import org.rawafedtech.marvelapp.presentation.home.viewmodel.HomeViewModel
import org.rawafedtech.marvelapp.ui.theme.Black80
import org.rawafedtech.marvelapp.utils.ParallelogramShape


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {

    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
            if (viewModel.statePaging.items.isEmpty()) {
                EmptyView(message = stringResource(R.string.empty))
            } else {
                MarvelCharacterList(viewModel = viewModel, navController = navController)
            }
        }

    }
}

@Composable
fun MarvelCharacterList(
    viewModel: HomeViewModel,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Black80)
    ) {
        item {
            Box(modifier = Modifier.clickable {
            }) {
                Header {
                    //TODO Naviagte To Search Screen


                }


            }
        }

        itemsIndexed(viewModel.statePaging.items) { i, item ->
            if (i >= viewModel.statePaging.items.size - 1 && !viewModel.statePaging.endReached && !viewModel.statePaging.isLoading) {
                viewModel.loadNextItems()
            }
            CharacterItem(character = item, navController = navController)
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

@Composable
fun Header(onSearchClick: (() -> Unit)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(

            painter = painterResource(id = R.drawable.marvel_logo),
            contentDescription = "Marvel Logo", modifier = Modifier
                .height(50.dp)
                .weight(1.7f, true)
        )

        IconButton(onClick = onSearchClick, modifier = Modifier.weight(0.3f, true)) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Red
            )
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
            //TODO Navigate To Details Screen And Passing the marvel obj
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