package pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
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
import composables.Cage
import composables.Comments
import defaultSecondary
import keyboardAsState
import nodes.*
import java.lang.Integer.parseInt


@Composable
actual fun EndGameMenu(
    backStack: BackStack<AutoTeleSelectorNode.NavTarget>,
    mainMenuBackStack: BackStack<RootNode.NavTarget>,
    selectAuto: MutableState<Boolean>,
    match: MutableState<String>,
    team: MutableIntState,
    robotStartPosition: MutableIntState
) {
    val scrollState = rememberScrollState(0)
    val isScrollEnabled = remember{ mutableStateOf(true) }
    val isKeyboardOpen by keyboardAsState()
    val context = LocalContext.current

//    fun bob() {
//        mainMenuBackStack.pop()
//        teamDataArray[robotStartPosition.intValue]?.set(parseInt(match.value), createOutput(team, robotStartPosition))
//        exportScoutData(context)
//    }

    if(!isKeyboardOpen){
        isScrollEnabled.value = true
    }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Row(Modifier.padding(bottom = 50.dp, top = 20.dp).align(Alignment.CenterHorizontally)) {
                Cage("Center Barge", aClimb, aDeep, bClimb, cClimb, Modifier.fillMaxSize())
                Cage("Middle", bClimb, bDeep, aClimb, cClimb, Modifier.fillMaxSize())
                Cage("Outer Edge", cClimb, cDeep, aClimb, bClimb, Modifier.fillMaxSize())
            }
            Comments(notes)
            Spacer(Modifier.height(4.dp))
            OutlinedButton(
                border = BorderStroke(3.dp, Color.Yellow),
                shape = RoundedCornerShape(25.dp),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = defaultSecondary),
                onClick = {
                    teamDataArray[TeamMatchKey(parseInt(match.value), team.intValue)] = createOutput(team, robotStartPosition)
                    println(teamDataArray[TeamMatchKey(parseInt(match.value), team.intValue)])
                    match.value = (parseInt(match.value) + 1).toString()
                    reset()
                    backStack.push(AutoTeleSelectorNode.NavTarget.AutoScouting)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 10.dp)
            ) {
                Text("Next Match", fontSize = 20.sp)
            }

            OutlinedButton(
                border = BorderStroke(2.dp, color = Color.Yellow),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = defaultSecondary),
                onClick = {
                    if(parseInt(match.value) != 1) {
                        match.value = (parseInt(match.value) - 1).toString()
                    }
                    loadData(parseInt(match.value), team, robotStartPosition)
                    backStack.pop()
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