package pages

import android.graphics.Paint.Align
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
import composables.EnumerableValueAuto
import defaultOnPrimary
import defaultSecondary
import exportScoutData
import keyboardAsState
import nodes.*
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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

    Column(

    ){

        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Column (
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {

                EnumerableValueAuto(
                    label = "Feeder",
                    value = autoFeederCollection,
                    alignment = Alignment.Center,
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
                        label = "C3",
                        ifChecked = coral3Collected,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )

                    CheckBox(
                        label = "A3",
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
                        label = "C2",
                        ifChecked = coral2Collected,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )

                    CheckBox(
                        label = "A2",
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
                        label = "C1",
                        ifChecked = coral1Collected,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )

                    CheckBox(
                        label = "A1",
                        ifChecked = algae1Collected,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )

                }

                EnumerableValueAuto(
                    label = "Feeder",
                    value = autoFeederCollection,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

            }

            Column (
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {

                EnumerableValueAuto(
                    label = "Algae Processed",
                    value = algaeProcessed,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                EnumerableValueAuto(
                    label = "Score L4",
                    value = autoCoralLevel4Scored,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                EnumerableValueAuto(
                    label = "Score L3",
                    value = autoCoralLevel3Scored,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                EnumerableValueAuto(
                    label = "Score L2",
                    value = autoCoralLevel2Scored,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                EnumerableValueAuto(
                    label = "Score L1",
                    value = autoCoralLevel1Scored,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

            }

            Column (
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {

                EnumerableValueAuto(
                    label = "Algae Removed",
                    value = algaeRemoved,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                EnumerableValueAuto(
                    label = "Miss L4",
                    value = autoCoralLevel4Missed,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                EnumerableValueAuto(
                    label = "Miss L3",
                    value = autoCoralLevel3Missed,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                EnumerableValueAuto(
                    label = "Miss L2",
                    value = autoCoralLevel2Missed,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                EnumerableValueAuto(
                    label = "Miss L1",
                    value = autoCoralLevel1Missed,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

            }

            Column (
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
            ) {

                EnumerableValueAuto(
                    label = "Net Missed",
                    value = autoNetMissed,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                EnumerableValueAuto(
                    label = "Net Scored",
                    value = autoNetScored,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

            }

        }

    }
}