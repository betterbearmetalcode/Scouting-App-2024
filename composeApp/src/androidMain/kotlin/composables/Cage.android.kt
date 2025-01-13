package composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TriStateCheckbox
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import defaultOnPrimary

@Composable
actual fun Cage(
    label: String,
    ifChecked: MutableState<ToggleableState>,
    isDeep: MutableState<Boolean>,
    modifier: Modifier
) {
    fun getNewState(state: ToggleableState) = when (state) {
        ToggleableState.Off -> ToggleableState.On
        ToggleableState.Indeterminate -> ToggleableState.Off
        ToggleableState.On -> ToggleableState.Indeterminate
    }
    OutlinedButton(
        onClick = {
            isDeep.value = !isDeep.value
        }
    ) {
        Row(){
        Text(text = if (isDeep.value) "Deep" else "Shallow",
            color = defaultOnPrimary
        )
            TriStateCheckbox(
                state = ifChecked.value,
                onClick = {
                    ifChecked.value = getNewState(ifChecked.value)
                },
            )
        }
    }
}