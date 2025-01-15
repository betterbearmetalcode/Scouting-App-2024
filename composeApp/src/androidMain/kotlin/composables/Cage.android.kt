package composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TriStateCheckbox
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import defaultBackgroundVariant
import defaultOnPrimary
import defaultSecondary

@Composable
actual fun Cage(
    label: String,
    ifChecked: MutableState<ToggleableState>,
    isDeep: MutableState<Boolean>,
    cageChecked1: MutableState<ToggleableState>,
    cageChecked2: MutableState<ToggleableState>,
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
        },
        border = BorderStroke(3.dp, Color.Yellow),
        shape = RoundedCornerShape(25.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = defaultBackgroundVariant),
        modifier = Modifier
            .padding(10.dp)
    ) {
        Column(){
            Text(text = if (isDeep.value) "Deep" else "Shallow",
                color = defaultOnPrimary,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 15.dp)
            )
            TriStateCheckbox(
                state = ifChecked.value,
                onClick = {
                    ifChecked.value = getNewState(ifChecked.value)

                    cageChecked1.value = ToggleableState.Off
                    cageChecked2.value = ToggleableState.Off
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .scale(1.5f)
            )
        }
    }
}