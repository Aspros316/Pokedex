package com.example.pokedex.ui.component

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

@Composable
 fun <T : Any> LazyVerticalGridTarget(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    header: @Composable () -> Unit = {}
) {}