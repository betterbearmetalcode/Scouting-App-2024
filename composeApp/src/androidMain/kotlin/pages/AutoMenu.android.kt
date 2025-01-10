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
import com.bumble.appyx.components.backstack.operation.push
import composables.CheckBox
//import composables.AutoCheckboxesHorizontal
//import composables.AutoCheckboxesVertical
import composables.EnumerableValue
import defaultOnPrimary
import defaultSecondary
import exportScoutData
import keyboardAsState
import nodes.*

@Composable
actual fun AutoMenu(
    backStack: BackStack<AutoTeleSelectorNode.NavTarget>,
    mainMenuBackStack: BackStack<RootNode.NavTarget>,

    selectAuto: MutableState<Boolean>,

    match: MutableState<String>,
    team: MutableIntState,
    robotStartPosition: MutableIntState
) {

    val context = LocalContext.current
    fun bob() {
        mainMenuBackStack.pop()
        matchScoutArray.putIfAbsent(robotStartPosition.intValue, HashMap())
        matchScoutArray[robotStartPosition.intValue]?.set(
            Integer.parseInt(match.value),
            createOutput(team, robotStartPosition)
        )
        exportScoutData(context)
    }

    val scrollState = rememberScrollState(0)
    val isScrollEnabled = remember { mutableStateOf(true) }
    val isKeyboardOpen by keyboardAsState()
    if (!isKeyboardOpen) {
        isScrollEnabled.value = true
    }
//    val flippingAuto = remember { mutableStateOf(false)}
//    val rotateAuto = remember { mutableStateOf(false)}

    Column(){

        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Column (
                modifier = Modifier
                    .fillMaxHeight()
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                Row (
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {

                    CheckBox(
                        label = "Coral 3",
                        ifChecked = coral3Collected,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )

                    CheckBox(
                        label = "Algae 3",
                        ifChecked = algae3Collected,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )

                }

                Row (
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {

                    CheckBox(
                        label = "Coral 2",
                        ifChecked = coral2Collected,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )

                    CheckBox(
                        label = "Algae 2",
                        ifChecked = algae2Collected,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )

                }

                Row (
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {

                    CheckBox(
                        label = "Coral 1",
                        ifChecked = coral1Collected,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )

                    CheckBox(
                        label = "Algae 1",
                        ifChecked = algae1Collected,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )

                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

            }

        }

        OutlinedButton(
            border = BorderStroke(2.dp, color = Color.Yellow),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = defaultSecondary),
            onClick = {
                backStack.push(AutoTeleSelectorNode.NavTarget.TeleScouting)
                selectAuto.value = true
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Tele",
                color = Color.Yellow,
                fontSize = 35.sp
            )
        }

        Spacer(Modifier.height(25.dp))

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
            Text(scoutName.value)
    }
}