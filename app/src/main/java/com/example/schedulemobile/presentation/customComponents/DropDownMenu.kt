@file:OptIn(ExperimentalFoundationApi::class)

package com.example.schedulemobile.presentation.customComponents

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Popup
import com.example.schedulemobile.domain.models.group.GroupList
import com.example.schedulemobile.presentation.customComponents.sample.AutoCompleteValueSample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupDropdownMenu(
    groups: GroupList?,
    selectedGroup: String
) {

    //var group = selectedGroup
    var expanded by remember { mutableStateOf(false) }
    var group by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val scope = rememberCoroutineScope()
    var visibility by remember { mutableStateOf(false) }

    var items = groups?.items?.map { it.name }
    if (items == null) {
        items = listOf("ИСПП-01", "КСК-21", "КСК-22", "ССА-21 ССА-23", "ССА-22 ИСПП-23", "ИСПП-21", "ИСПВ-21 ИСПВ-23",)
    }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 0f else 180f,
        animationSpec = tween(durationMillis = 400, easing = FastOutLinearInEasing),
    )

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(paddingValues),
            content = {
                //AutoCompleteValueSample(items = items)
                /*OutlinedTextField(
                    value = group,
                    onValueChange = {
                        expanded = true
                        group = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expanded = !expanded
                        }
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    label = { Text(text = "Группа") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "",
                            Modifier
                                .rotate(rotation)
                                .clickable {
                                    expanded = !expanded
                                }
                        )
                    }
                )*/

                /*ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = group,
                        onValueChange = {
                            group = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        label = { Text(text = "Группа") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        }
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                    ) {
                        list.forEach { groupName ->
                            DropdownMenuItem(
                                text = { Text(text = groupName) },
                                onClick = { group = groupName }
                            )
                        }
                    }
                }*/

                /*Column(
                    modifier = Modifier
                        .heightIn(0.dp, 150.dp)
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
                    content = {
                        AnimatedVisibility(
                            visible = visibility,
                            enter = expandVertically(
                                animationSpec = tween(400)
                            ),
                            exit = shrinkVertically(
                                animationSpec = tween(400)
                            ),
                            content = {
                                LazyColumn(
                                    content = {
                                        list.forEach { groupName ->
                                            item { Text(text = groupName) }
                                        }
                                    }
                                )
                            }
                        )
                    }
                )*/


                /*DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                ) {
                    list.forEach { groupName ->
                        DropdownMenuItem(
                            text = { Text(text = groupName) },
                            onClick = {
                                group = groupName
                            }
                        )
                    }
                }*/
            }
        )
    }
}