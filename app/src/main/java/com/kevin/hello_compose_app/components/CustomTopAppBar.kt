package com.kevin.hello_compose_app.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    navController: NavController,
    title: String,
    navigationIcon: @Composable () -> Unit = {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "Localized description"
            )
        }
    },
    actions: @Composable () -> Unit = {},
) {
    CenterAlignedTopAppBar(title = {
        Text(
            title, maxLines = 1, overflow = TextOverflow.Ellipsis
        )
    }, navigationIcon = {
        navigationIcon()
    }, actions = {
        actions()
    }, colors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = Color.Green //Add your own color here, just to clarify.
    )
    )
}