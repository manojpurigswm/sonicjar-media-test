package com.sonicjar.media.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.sonicjar.media.R
import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TracksScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
){
    Scaffold(modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { MyAppBar() }
    ){ paddingValues ->
        val state = viewModel.lists.collectAsState()
        when(state.value){
            is Resource.Fail -> ErrorText(paddingValues)
            is Resource.Loading -> Loading(paddingValues)
            is Resource.Success -> TrackList(paddingValues, state.value.valueOrNull!!)
        }
    }
}

@Composable
fun Loading(paddingValues: PaddingValues){
    Box(modifier = Modifier.padding(paddingValues).fillMaxSize(),
        contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorText(paddingValues: PaddingValues){
    Box(modifier = Modifier.padding(paddingValues).fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text("Error")
    }
}

@Composable
fun TrackList(paddingValues: PaddingValues, tracks: List<Track>){
    Column (modifier = Modifier.padding(paddingValues).fillMaxSize()) {
        LazyColumn {
            items(tracks){
                TrackRow(it)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TrackRow(track: Track){
    Card(modifier = Modifier.fillMaxWidth().padding(2.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardColors(Color.White, Color.Unspecified, Color.Unspecified, Color.Unspecified)
    ) {
        Row (modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Box(modifier = Modifier.size(50.dp).align(Alignment.CenterVertically)){
                GlideImage(model = track.trackImageURL,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    loading = placeholder(R.drawable.ic_launcher_foreground),
                    failure = placeholder(R.drawable.ic_launcher_foreground),
                    modifier = Modifier.fillMaxSize().clip(CircleShape).border(2.dp, Color.Black, CircleShape)
                    )
            }
            Column(modifier = Modifier.fillMaxSize().align(Alignment.CenterVertically)){
                Text(text = track.trackTitle.trim(),
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp).fillMaxWidth(),
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Clip,
                    textAlign = TextAlign.Start)

                Text(text = track.trackSubtitle.trim(),
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 10.dp).fillMaxWidth(),
                    maxLines = 1,
                    fontWeight = FontWeight.Light,
                    overflow = TextOverflow.Clip,
                    textAlign = TextAlign.Start)
            }


        }
        //HorizontalDivider()
    }
}

@ExperimentalMaterial3Api
@Composable
fun MyAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        colors = TopAppBarColors(
            containerColor = Color(0xFFFFFFFF),
            titleContentColor = Color(0xFF3700B3),
            actionIconContentColor = Color(0xFFFFFFFF),
            scrolledContainerColor = Color(0xFFFFFFFF),
            navigationIconContentColor = Color(0xFF3700B3),
        ),
        modifier = Modifier.fillMaxWidth().shadow(Dp(5F))
    )
}
