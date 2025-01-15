package composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import defaultPrimaryVariant
import getCurrentTheme

@Composable
actual fun CheckBox(
    label: String,
    ifChecked: MutableState<Boolean>,
    modifier: Modifier
){

    var backgroundColor = remember { mutableStateOf(Color.Black) }
    var textColor = remember { mutableStateOf(Color.White) }

    OutlinedButton (
        border = BorderStroke(2.dp, color = getCurrentTheme().primaryVariant),
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor.value),
        onClick = {
            ifChecked.value = !ifChecked.value

            if(ifChecked.value) {
                backgroundColor.value = Color.White
                textColor.value = Color.Black
            } else {
                backgroundColor.value = Color.Black
                textColor.value = Color.White
            }
        },
        modifier = modifier
    ) {
        Text(
            text = label,
            color = textColor.value
        )
    }
}