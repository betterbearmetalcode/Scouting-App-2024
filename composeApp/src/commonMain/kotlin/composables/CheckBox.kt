package composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState

@Composable
expect fun CheckBox(
    label: String,
    ifChecked: MutableState<ToggleableState>,
    modifier: Modifier
)