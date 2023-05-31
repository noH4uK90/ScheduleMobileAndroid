package com.example.schedulemobile.presentation.customComponents.sample

/*@Composable
fun AutoCompleteObjectSample(groups: GroupList) {
    AutoCompleteBox(
        items = groups.items,
        itemContent = { group ->
            GroupAutoCompleteItem(group)
        }
    ) {
        var value by remember { mutableStateOf("") }
        val view = LocalView.current

        onItemSelected { group ->
            value = group.name
            filter(value)
            view.clearFocus()
        }

        TextSearchBar(
            modifier = Modifier,
            value = value,
            label = "Search with objects",
            onDoneActionClick = {
                view.clearFocus()
            },
            onClearClick = {
                value = ""
                filter(value)
                view.clearFocus()
            },
            onFocusChanged = { focusState ->
                isSearching = focusState.isFocused == true
            },
            onValueChanged = { query ->
                value = query
                filter(value)
            }
        )
    }
}*/

/*
@Composable
fun GroupAutoCompleteItem(group: Group) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = group.name)
    }
}*/
