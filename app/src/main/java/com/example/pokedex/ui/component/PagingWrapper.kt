package com.example.pokedex.ui.component

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

enum class PagingType {
    ROW,
    COLUMN,
    LAZY_ROW
}


@Composable
fun <T : Any> PagingWrapper(
    pagingType: PagingType,
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    initialView: @Composable () -> Unit = {},
    emptyView: @Composable () -> Unit = {},
    extraItemsView: @Composable () -> Unit = {},
    header: @Composable () -> Unit = {},
) {

    when {
        pagingItems.loadState.refresh is LoadState.Loading && pagingItems.itemCount == 0 -> {
            //Carga inicial
            initialView()
        }


    }


}