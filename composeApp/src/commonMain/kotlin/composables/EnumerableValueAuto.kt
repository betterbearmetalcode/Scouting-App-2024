package composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
expect fun EnumerableValueAuto(
    label: String,
    value: MutableIntState,
    alignment: Alignment,
    modifier: Modifier
)