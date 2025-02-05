package com.example.pokedex.ui.component

import androidx.compose.foundation.lazy.LazyColumn
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

        pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0 -> {
            emptyView()
        }

        else -> {

            when (pagingType) {
                PagingType.ROW -> {
                    LazyRowTarget(pagingItems, itemView)
                }

                PagingType.COLUMN -> {
                    LazyColumn {
                        item { header() }
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let { item ->
                                itemView(item)
                            }
                        }
                    }
                }

                PagingType.LAZY_ROW -> {
                    LazyRowTarget(pagingItems, itemView)
                }
            }


            if (pagingItems.loadState.append is LoadState.Loading) {
                extraItemsView()
            }

        }
    }

}