package composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import getCurrentTheme

@Composable
actual fun EnumerableValueAuto(
    label: String,
    value: MutableIntState,
    alignment: Alignment,
    modifier: Modifier
) {
    OutlinedButton(
        border = BorderStroke(2.dp, color = getCurrentTheme().primaryVariant),
        shape = RoundedCornerShape(0.dp),
        onClick = {
            value.value += 1
        },
        contentPadding = PaddingValues(5.dp, 5.dp),
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = label + ": \n" + value.intValue.toString(),
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
    }

}