package pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import composables.EnumerableValue
import defaultSecondary
import exportScoutData
import keyboardAsState
import nodes.*
import setTeam
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
    val isScrollEnabled = remember{ mutableStateOf(true) }
    val isKeyboardOpen by keyboardAsState()
    val context = LocalContext.current

    fun bob() {
        mainMenuBackStack.pop()
        matchScoutArray.putIfAbsent(robotStartPosition.intValue, HashMap())
        matchScoutArray[robotStartPosition.intValue]?.set(parseInt(match.value), createOutput(team, robotStartPosition))
        exportScoutData(context)
    }

    if(!isKeyboardOpen){
        isScrollEnabled.value = true
    }

    Column(
        Modifier
            .verticalScroll(state = scrollState, enabled = isScrollEnabled.value)
            .padding(20.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(7/8f)
        ) {
            Row {
                EnumerableValue("L4 scored", teleLFour, alignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth(1/2f))
                EnumerableValue("L4 missed", teleLFourMissed, alignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth(1/2f))
            }
            Row {
                EnumerableValue("L3 Algae", teleLThreeAlgae, alignment = Alignment.BottomEnd, modifier = Modifier.fillMaxWidth(2/8f))
                EnumerableValue("L3 scored", teleLThree, alignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth(3/8f))
                EnumerableValue("L3 missed", teleLThreeMissed, alignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth(3/8f))
            }
            Row {
                EnumerableValue("L2 Algae", teleLTwoAlgae, alignment = Alignment.BottomEnd, modifier = Modifier.fillMaxWidth(2/8f))
                EnumerableValue("L2 scored", teleLTwo, alignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth(3/8f))
                EnumerableValue("L2 missed", teleLTwoMissed, alignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth(3/8f))
            }
            Row {
                EnumerableValue("L1 scored", teleLOne, alignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth(1/2f))
                EnumerableValue("L1 missed", teleLOneMissed, alignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth(1/2f))
            }
            EnumerableValue("Processed", teleProcessed, alignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth())
        }
        Column(modifier = Modifier.fillMaxWidth(1/8f) ) {
            OutlinedButton(
                border = BorderStroke(3.dp, Color. Yellow),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = defaultSecondary),
                onClick = {
                    teleNet.value++;
                },
                modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxHeight(1/2f).fillMaxWidth(1f)
            ) {
                Text("Net", fontSize = 20.sp)
            }
            OutlinedButton(
                border = BorderStroke(3.dp, Color.Yellow),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = defaultSecondary),
                onClick = {
                    teleNetMissed.value++;
                },
                modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxHeight(1/2f).fillMaxWidth(1f)
            ) {
                Text("Net Missed", fontSize = 20.sp)
            }
        }
        OutlinedButton(
            border = BorderStroke(3.dp, Color.Yellow),
            shape = RoundedCornerShape(25.dp),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = defaultSecondary),
            onClick = {
                matchScoutArray.putIfAbsent(robotStartPosition.intValue, HashMap())
                matchScoutArray[robotStartPosition.intValue]?.set(parseInt(match.value),
                    createOutput(team, robotStartPosition)
                )
                match.value = (parseInt(match.value) + 1).toString()
                reset()
                teleNotes.value = ""
                selectAuto.value = false
                exportScoutData(context)
                loadData(parseInt(match.value), team, robotStartPosition)
                backStack.pop()
                setTeam(team,match,robotStartPosition.intValue)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Next Match", fontSize = 20.sp)
        }

        OutlinedButton(
            border = BorderStroke(2.dp, color = Color.Yellow),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = defaultSecondary),
            onClick = {
                bob()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = "Back",
                color = Color.Yellow
            )
        }
    }
}
