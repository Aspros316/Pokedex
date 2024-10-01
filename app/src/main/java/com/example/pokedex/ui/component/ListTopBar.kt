package com.example.pokedex.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpOffset
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ListTopAppBar(
    openDrawer: () -> Unit,
    actionItems: List<ActionItem>
) {
    TopAppBar(
        title = { /*...*/ },
        navigationIcon = {/*...*/ },
        actions = {
            val (icons, options) = actionItems.partition { it.icon != null } // (1)

            icons.forEach { // (2)
                IconButton(onClick = it.action) {
                    Icon(imageVector = it.icon!!, contentDescription = it.name)
                }
            }

            val (isExpanded, setExpanded) = remember { mutableStateOf(false) } // (3)

            OverflowMenuAction(isExpanded, setExpanded, options) // (4)
        }
    )
}

@Composable
private fun OverflowMenuAction(
    expanded: Boolean,
    setExpanded: (Boolean) -> Unit,
    options: List<ActionItem>
) {
    IconButton(onClick = { setExpanded(true) }) {
        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Ver más")

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { setExpanded(false) },
            offset = DpOffset(x = 0.dp, y = 4.dp)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        option.action
                        setExpanded(false)
                    },
                    text = { Text(text = option.name) }
                )
            }
        }
    }
}