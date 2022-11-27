package com.emon.repositories

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.emon.common.LoadingAnimation
import com.emon.common.PaddingValuesBody
import com.emon.entity.repositories.RepositoriesListApiItem

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RepositoriesScreen(viewModel:RepositoriesViewModel = hiltViewModel(), onNavigateToProfile: (userId: String) -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Repositories List")
                },
                backgroundColor = Color.Black,
                contentColor = Color.White,
                elevation = 12.dp
            )
        },
    ){ padding ->
        when (val result=viewModel.uiState.collectAsStateWithLifecycle().value) {
            is RepositoriesViewModel.RepositoriesListUiState.RepositoriesListApiSuccess -> {
                val repolist = remember { result.data.list }
                RepoListUi(repolist, onClick = { onNavigateToProfile(it) })
            }
            is RepositoriesViewModel.RepositoriesListUiState.ApiError -> {
                val context = LocalContext.current
                Toast.makeText(
                    context,
                    "" + result.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            is RepositoriesViewModel.RepositoriesListUiState.Loading -> {
                if (result.isLoading) LoadingUI()
            }
        }
        PaddingValuesBody(paddingValues = padding)
    }

}

@Composable
fun RepoListUi(repolist: List<RepositoriesListApiItem>, onClick: (userName: String) -> Unit) {
    LazyColumn(modifier = Modifier.background(color = Color.White)) {
        items(
            count = repolist.size,
            key = { repolist[it].name },
            itemContent = { index ->
                val repo = repolist[index]
                RepoCardItem(repo, onClick)
            }
        )
    }
}

@Composable
fun RepoCardItem(
    repo: RepositoriesListApiItem,
    onClick: (userName: String) -> Unit
) {
    Card(
        elevation = 4.dp, backgroundColor = Color.White, modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        Column(
            Modifier
                .clickable(onClick = { onClick(repo.owner.login) })
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(repo.owner.avatar_url),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .border(width = 2.dp, color = Color.LightGray, CircleShape)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Text(repo.owner.login, fontWeight = FontWeight.Bold)
                    Text(repo.name)
                }
            }
            Spacer(Modifier.size(16.dp))
            Text(repo.full_name)
            Text(repo.description)
            Spacer(Modifier.size(10.dp))

            Row() {
                Row(
                    Modifier
                        .weight(1f),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = com.emon.assets.R.drawable.ic_baseline_info_24),
                        contentDescription = null
                    )
                    Text(text = repo.language, color = Color.Black, fontWeight = FontWeight.Bold)
                }
                Row(
                    Modifier
                        .weight(1f),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = com.emon.assets.R.drawable.ic_baseline_star_rate_24),
                        contentDescription = null
                    )
                    Text(
                        text = "${repo.stargazers_count} Star",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    Modifier
                        .weight(1f),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource(id = com.emon.assets.R.drawable.ic_baseline_share_24),
                        contentDescription = null
                    )
                    Text(
                        text = "${repo.forks_count} Forked",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                }
            }

        }
    }
}

@Composable
fun LoadingUI() {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        LoadingAnimation()
    }
}