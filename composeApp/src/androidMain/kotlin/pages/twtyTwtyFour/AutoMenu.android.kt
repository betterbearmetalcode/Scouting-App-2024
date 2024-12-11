package pages.twtyTwtyFour

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
import composables.EnumerableValue
import defaultOnPrimary
import defaultSecondary
import exportScoutData
import keyboardAsState
import nodes.twtyTwtyFour.*
import pageStructures.twtyTwtyFour.RootNode
import pageStructures.twtyTwtyFour.matchScoutArray
import pageStructures.twtyTwtyFour.scoutName

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
        matchScoutArray[robotStartPosition.intValue]?.set(Integer.parseInt(match.value),
            createOutput(team, robotStartPosition)
        )
        exportScoutData(context)
    }

    val scrollState = rememberScrollState(0)
    val isScrollEnabled = remember{ mutableStateOf(true) }
    val isKeyboardOpen by keyboardAsState()
    if(!isKeyboardOpen){
        isScrollEnabled.value = true
    }
//    val flippingAuto = remember { mutableStateOf(false)}
//    val rotateAuto = remember { mutableStateOf(false)}


    Column(
        Modifier
            .verticalScroll(state = scrollState, enabled = isScrollEnabled.value)
            .padding(20.dp)
    ) {

        EnumerableValue(label = "Speaker", value = autoSpeakerNum)
        EnumerableValue(label = "Amp", value = autoAmpNum)


        Spacer(modifier = Modifier.height(10.dp))

        EnumerableValue(label = "S Missed", value = autoSMissed)
        EnumerableValue(label = "A Missed", value = autoAMissed)

//        if(rotateAuto.value){
//            AutoCheckboxesVertical(flippingAuto)
//        }else{
//            AutoCheckboxesHorizontal(flippingAuto)
//        }
//
//        Column {
//            OutlinedButton(onClick = { flippingAuto.value = !flippingAuto.value }) {
//                Text(text = "Flip Auto Boxes", color = Color.White)
//            }
//            OutlinedButton(onClick = { rotateAuto.value = !rotateAuto.value }) {
//                Text(text = "Rotate Auto Boxes", color = Color.White)
//            }
//        }

        OutlinedTextField(
            value = autos.value,
            onValueChange ={ autos.value = it},
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Cyan, unfocusedBorderColor = Color.Yellow,focusedContainerColor = Color(6,9,13), unfocusedContainerColor = Color(6,9,13) ,focusedTextColor = defaultOnPrimary, unfocusedTextColor = defaultOnPrimary, cursorColor = defaultOnPrimary),
            shape = RoundedCornerShape(15.dp),
            placeholder ={Text("AUTOS", color = Color.White)},
            modifier = Modifier
                .fillMaxWidth(9f / 10f)
                .align(Alignment.CenterHorizontally)
                .height(70.dp)
        )

        Row(){
            Text(text = "Auto Stop ⚠\uFE0F",
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Checkbox(
                when(autoStop.intValue) {0 -> false; 1 -> true; else -> false},
                colors = CheckboxDefaults.colors(checkedColor = Color.Cyan),
                onCheckedChange = { when(it) {true -> autoStop.intValue = 1; false -> autoStop.intValue = 0}}
            )
        }

        Spacer(Modifier.height(5.dp))

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