package composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState

@Composable
actual fun Cage(
    label: String,
    ifChecked: MutableState<ToggleableState>,
    isDeep: MutableState<Boolean>,
    modifier: Modifier
) {
}