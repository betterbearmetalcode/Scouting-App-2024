package pages

import RootNode
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.push
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import composables.InternetErrorAlert
import defaultSecondary
import getCurrentTheme
import sync

class MainMenu(
    buildContext: BuildContext,
    private val backStack: BackStack<RootNode.NavTarget>,
    private val robotStartPosition: MutableIntState
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        var selectedPlacement by remember { mutableStateOf(false) }
        var openError = remember { mutableStateOf(false) }

        when {
            openError.value -> {
                InternetErrorAlert { openError.value = false }
            }
        }
        Column {
            Text(
                text = "Bear Metal Scout App",
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Divider(color = getCurrentTheme().onSurface, thickness = 2.dp)
            OutlinedButton(
                border = BorderStroke(3.dp, Color.Yellow),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = defaultSecondary),
                contentPadding = PaddingValues(horizontal = 60.dp, vertical = 5.dp),
                onClick = {
                    selectedPlacement = true
                    openError.value = !sync(false)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 50.dp, vertical = 50.dp),
            ) {
                Text(
                    text = "Match",
                    color = getCurrentTheme().primaryVariant,
                    fontSize = 35.sp
                )
            }

            DropdownMenu(
                expanded = selectedPlacement,
                onDismissRequest = {selectedPlacement = false},
                modifier = Modifier.size(100.dp, 166.dp).background(color = Color(0,0,0))
            ) {
                Row {
                    DropdownMenuItem(onClick = {robotStartPosition.value= 0; backStack.push(RootNode.NavTarget.QuanScouting);}, modifier = Modifier.size(50.dp,50.dp)) {Text("R1", fontSize = 11.sp)}
                    DropdownMenuItem(onClick = {robotStartPosition.value= 3; backStack.push(RootNode.NavTarget.QuanScouting);}, modifier = Modifier.size(50.dp,50.dp)) {Text("B1", fontSize = 11.sp)}
                }
                Row {
                    DropdownMenuItem(onClick = {robotStartPosition.value= 1; backStack.push(RootNode.NavTarget.QuanScouting);}, modifier = Modifier.size(50.dp,50.dp)) {Text("R2", fontSize = 11.sp)}
                    DropdownMenuItem(onClick = {robotStartPosition.value= 4; backStack.push(RootNode.NavTarget.QuanScouting);}, modifier = Modifier.size(50.dp,50.dp)) {Text("B2", fontSize = 11.sp)}
                }
                Row {
                    DropdownMenuItem(onClick = {robotStartPosition.value= 2; backStack.push(RootNode.NavTarget.QuanScouting);}, modifier = Modifier.size(50.dp,50.dp)) {Text("R3", fontSize = 11.sp)}
                    DropdownMenuItem(onClick = {robotStartPosition.value= 5; backStack.push(RootNode.NavTarget.QuanScouting);}, modifier = Modifier.size(50.dp,50.dp)) {Text("B3", fontSize = 11.sp)}
                }
            }

            OutlinedButton(
                border = BorderStroke(3.dp, Color.Yellow),
                shape = RoundedCornerShape(25.dp),
                contentPadding = PaddingValues(horizontal = 80.dp, vertical = 5.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = defaultSecondary),
                onClick = {
                    backStack.push(RootNode.NavTarget.PitsScouting)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 50.dp, vertical = 50.dp),

                ) {
                Text(
                    text = "Pits",
                    color = getCurrentTheme().primaryVariant,
                    fontSize = 35.sp
                )
            }

            OutlinedButton(
                border = BorderStroke(3.dp, Color.Yellow),
                shape = RoundedCornerShape(25.dp),
                contentPadding = PaddingValues(horizontal = 80.dp, vertical = 5.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = defaultSecondary),
                onClick = {
                    openError.value = !sync(false)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 50.dp, vertical = 50.dp),
            ) {
                Text(
                    text = "Sync",
                    color = getCurrentTheme().primaryVariant,
                    fontSize = 35.sp
                )
            }
        }
    }
}
