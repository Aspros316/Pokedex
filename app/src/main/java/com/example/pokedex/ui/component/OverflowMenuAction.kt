package com.example.pokedex.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.pokedex.R

@Composable
private fun OverflowMenuExample(showSnackbar: (String) -> Unit) {

    val actionItems = listOf(
        ActionItem(
            "Buscar",
            Icons.Filled.Search,
            action = { showSnackbar("Buscar") },
            order = 1
        ),
        ActionItem(
            "Filtrar",
            ImageVector.vectorResource(id = R.drawable.error_icon),
            action = { showSnackbar("Filtrar") },
            order = 2
        ),
        ActionItem(
            "Refrescar",
            action = { showSnackbar("Refrescar") },
            order = 3
        ),
        ActionItem(
            "Ajustes",
            action = { showSnackbar("Ajustes") },
            order = 4
        ),
        ActionItem(
            "Enviar sugerencias",
            action = { showSnackbar("Enviar sugerencias") },
            order = 5
        ),
        ActionItem(
            "Ayuda",
            action = { showSnackbar("Ayuda") },
            order = 6
        ),
        ActionItem(
            "Cerrar sesión",
            action = { showSnackbar("Cerrar sesión") },
            order = 7
        )
    )

    ListTopAppBar(
        openDrawer = { /* Abrimos un drawer */ },
        actionItems = actionItems
    )
}

data class ActionItem(
    val name: String,
    val icon: ImageVector? = null,
    val action: () -> Unit,
    val order: Int
)