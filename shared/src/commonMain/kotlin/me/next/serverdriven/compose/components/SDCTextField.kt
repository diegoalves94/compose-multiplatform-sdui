package me.next.serverdriven.compose.components

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import me.next.serverdriven.compose.loadComponent
import me.next.serverdriven.core.tree.ServerDrivenNode

@Composable
fun SDCTextField(node: ServerDrivenNode, state: MutableMap<String, String>) {
    val onChangeUpdateState =
        node.propertyString("onChangeUpdateState") ?: "TextField_${node.id}"
    if (state[onChangeUpdateState] == null) {
        node.propertyString("text")?.run {
            state[onChangeUpdateState] = this
        }
    }
    TextField(
        value = state[onChangeUpdateState] ?: "",
        onValueChange = { state[onChangeUpdateState] = it },
        label = {
            node.children?.let {
                for (serverDrivenNode in it) {
                    loadComponent(node = serverDrivenNode, dataState = state)
                }
            }
        }
    )
}