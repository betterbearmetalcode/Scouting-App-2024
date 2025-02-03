package pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.pop
import com.bumble.appyx.components.backstack.operation.push
import composables.EnumerableValue
import defaultSecondary
import exportScoutData
import keyboardAsState
import nodes.*
import java.lang.Integer.parseInt

@Composable
actual fun TeleMenu (
    backStack: BackStack<AutoTeleSelectorNode.NavTarget>,
    mainMenuBackStack: BackStack<RootNode.NavTarget>,

    selectAuto: MutableState<Boolean>,

    match: MutableState<String>,
    team: MutableIntState,
    robotStartPosition: MutableIntState,
) {
    val scrollState = rememberScrollState(0)
    val isScrollEnabled = remember { mutableStateOf(true) }
    val isKeyboardOpen by keyboardAsState()
    val context = LocalContext.current

    fun bob() {
        backStack.pop()
        teamDataArray[TeamMatchKey(parseInt(match.value), team.intValue)] = createOutput(team, robotStartPosition)
    }

    if (!isKeyboardOpen) {
        isScrollEnabled.value = true
    }
    Column(
        Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .verticalScroll(state = scrollState, enabled = isScrollEnabled.value)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth(3 / 4f)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    EnumerableValue(
                        "L4 scored",
                        teleLFour,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth(1 / 2f)
                    )
                    EnumerableValue(
                        "L4 missed",
                        teleLFourMissed,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth(1 / 2f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    EnumerableValue(
                        "L3 Algae",
                        teleLThreeAlgae,
                        alignment = Alignment.BottomEnd,
                        modifier = Modifier.fillMaxWidth(2 / 8f)
                    )
                    EnumerableValue(
                        "L3 scored",
                        teleLThree,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth(3 / 8f)
                    )
                    EnumerableValue(
                        "L3 missed",
                        teleLThreeMissed,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth(3 / 8f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    EnumerableValue(
                        "L2 Algae",
                        teleLTwoAlgae,
                        alignment = Alignment.BottomEnd,
                        modifier = Modifier.fillMaxWidth(2 / 8f)
                    )
                    EnumerableValue(
                        "L2 scored",
                        teleLTwo,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth(3 / 8f)
                    )
                    EnumerableValue(
                        "L2 missed",
                        teleLTwoMissed,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth(3 / 8f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    EnumerableValue(
                        "L1 scored",
                        teleLOne,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth(1 / 2f)
                    )
                    EnumerableValue(
                        "L1 missed",
                        teleLOneMissed,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth(1 / 2f)
                    )
                }
                EnumerableValue(
                    "Processed",
                    teleProcessed,
                    alignment = Alignment.CenterEnd,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(modifier = Modifier.fillMaxWidth(1 / 4f)) {
                EnumerableValue(
                    label = "Net Scored",
                    value = teleNet,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(1 / 2f)
                )
                EnumerableValue(
                    label = "Net Missed",
                    value = teleNetMissed,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(1 / 2f)
                )
            }
            OutlinedButton(
                border = BorderStroke(2.dp, color = Color.Yellow),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = defaultSecondary),
                onClick = {
                    backStack.push(AutoTeleSelectorNode.NavTarget.EndGameScouting)
                    selectAuto.value = true
                },
                modifier = Modifier
            ) {
                Text(
                    text = "EndGame",
                    color = Color.Yellow,
                    fontSize = 35.sp
                )
            }

            OutlinedButton(
                border = BorderStroke(2.dp, color = Color.Yellow),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = defaultSecondary),
                onClick = {
                    bob()
                },
                modifier = Modifier
            ) {
                Text(
                    text = "Back",
                    color = Color.Yellow
                )
            }
        }
    }
}
