package composables

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import defaultPrimaryVariant

@Composable
actual fun CheckBox(
    label: String,
    ifChecked: MutableState<Boolean>,
    modifier: Modifier
){

    var backgroundColor = remember { mutableStateOf(Color.Black) }
    var textColor = remember { mutableStateOf(defaultPrimaryVariant) }

    Button(
        onClick = {
            ifChecked.value = !ifChecked.value

            if(ifChecked.value) {
                backgroundColor.value = defaultPrimaryVariant
                textColor.value = Color.Black
            } else {
                backgroundColor.value = Color.Black
                textColor.value = defaultPrimaryVariant
            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor.value),
        shape = RoundedCornerShape(0.dp),
        modifier = modifier
    ) {
        Text(
            text = label,
            color = textColor.value
        )
    }
}