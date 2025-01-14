package composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import defaultOnPrimary
import defaultSecondary
import getCurrentTheme

@Composable
actual fun EnumerableValue(label: String, value: MutableIntState, alignment: Alignment, modifier: Modifier) {
    OutlinedButton(
        border = BorderStroke(2.dp, color = getCurrentTheme().primaryVariant),
        shape = RoundedCornerShape(0.dp),
        onClick = {
            value.value += 1
        },
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Text(
                text = label,
                fontSize = 18.sp,
                color = getCurrentTheme().primaryVariant,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Text(
                text = value.value.toString(),
                fontSize = 14.sp,
                color = getCurrentTheme().primaryVariant,
                modifier = Modifier.align(alignment)
            )
        }
    }
}


