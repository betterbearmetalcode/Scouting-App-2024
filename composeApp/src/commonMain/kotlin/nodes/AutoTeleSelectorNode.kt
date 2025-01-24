package nodes

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize
import pages.AutoTeleSelectorMenu

class AutoTeleSelectorNode(
    buildContext: BuildContext,
    private var robotStartPosition: MutableIntState,
    private val team: MutableIntState,
    private val mainMenuBackStack: BackStack<RootNode.NavTarget>,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = NavTarget.AutoScouting,
            savedStateMap = buildContext.savedStateMap
        ),
        visualisation = { BackStackFader(it) }
    )
) : ParentNode<AutoTeleSelectorNode.NavTarget>(
    appyxComponent = backStack,
    buildContext = buildContext
) {
    private val selectAuto = mutableStateOf(false)

    sealed class NavTarget : Parcelable {
        @Parcelize
        data object AutoScouting : NavTarget()

        @Parcelize
        data object TeleScouting : NavTarget()

        @Parcelize
        data object EndGameScouting : NavTarget()
    }

    override fun resolve(interactionTarget: NavTarget, buildContext: BuildContext): Node =
        when (interactionTarget) {
            NavTarget.AutoScouting -> AutoNode(buildContext, backStack, mainMenuBackStack, selectAuto, match, team, robotStartPosition)
            NavTarget.TeleScouting -> TeleNode(buildContext, backStack, mainMenuBackStack, selectAuto, match, team, robotStartPosition)
            NavTarget.EndGameScouting -> EndgameNode(buildContext,backStack, mainMenuBackStack, selectAuto, match, team, robotStartPosition )
        }

    @Composable
    override fun View(modifier: Modifier) {
        Column {
            AutoTeleSelectorMenu(match, team, robotStartPosition, selectAuto, backStack, mainMenuBackStack)
            AppyxComponent(
                appyxComponent = backStack,
                modifier = Modifier.weight(0.9f)
            )
        }
    }
}



val match = mutableStateOf("1")

//CHECKED
var autoFeederCollection = mutableIntStateOf(0)
var coral3Collected = mutableStateOf(ToggleableState.Off)
var coral2Collected = mutableStateOf(ToggleableState.Off)
var coral1Collected = mutableStateOf(ToggleableState.Off)
var algae3Collected = mutableStateOf(ToggleableState.Off)
var algae2Collected = mutableStateOf(ToggleableState.Off)
var algae1Collected = mutableStateOf(ToggleableState.Off)
var algaeProcessed = mutableIntStateOf(0)
var algaeRemoved = mutableIntStateOf(0)
var autoCoralLevel4Scored = mutableIntStateOf(0)
var autoCoralLevel3Scored = mutableIntStateOf(0)
var autoCoralLevel2Scored = mutableIntStateOf(0)
var autoCoralLevel1Scored = mutableIntStateOf(0)
var autoCoralLevel4Missed = mutableIntStateOf(0)
var autoCoralLevel3Missed = mutableIntStateOf(0)
var autoCoralLevel2Missed = mutableIntStateOf(0)
var autoCoralLevel1Missed = mutableIntStateOf(0)
var autoNetScored = mutableIntStateOf(0)
var autoNetMissed = mutableIntStateOf(0)
val autoStop = mutableIntStateOf(0) /*TODO*/

//CHECKED
val teleNet = mutableIntStateOf(0)
val teleNetMissed = mutableIntStateOf(0)
val teleLFour = mutableIntStateOf(0)
val teleLThree = mutableIntStateOf(0)
val teleLThreeAlgae = mutableIntStateOf(0)
val teleLTwo = mutableIntStateOf(0)
val teleLTwoAlgae = mutableIntStateOf(0)
val teleLOne = mutableIntStateOf(0)
val teleProcessed = mutableIntStateOf(0)
val teleLFourMissed = mutableIntStateOf(0)
val teleLThreeMissed = mutableIntStateOf(0)
val teleLTwoMissed = mutableIntStateOf(0)
val teleLOneMissed = mutableIntStateOf(0)
var lostComms = mutableIntStateOf(0) /*TODO*/
var playedDefense = mutableStateOf(false) /*TODO*/

//CHECKED
var aDeep = mutableStateOf(false)
var bDeep = mutableStateOf(false)
var cDeep = mutableStateOf(false)
var aClimb = mutableStateOf(ToggleableState(false))
var bClimb = mutableStateOf(ToggleableState(false))
var cClimb = mutableStateOf(ToggleableState(false))
var notes = mutableStateOf("")


fun createOutput(team: MutableIntState, robotStartPosition: MutableIntState): String {

    fun stateToInt(state: ToggleableState) = when (state) {
        ToggleableState.Off -> 0
        ToggleableState.Indeterminate -> 1
        ToggleableState.On -> 2
    }

    if (notes.value.isEmpty()){ notes.value = "No Comments"}

    notes.value = notes.value.replace(":","")
    return(
            """
                {
                "match":${match.value},
                "team":${team.intValue.toString()},
                "robotStartPosition":${robotStartPosition.intValue.toString()},
                "autoFeederCollection":${autoFeederCollection.intValue.toString()},
                "coral3Collected":${stateToInt(coral3Collected.value).toString()},
                "coral2Collected":${stateToInt(coral2Collected.value).toString()},
                "coral1Collected":${stateToInt(coral1Collected.value).toString()},
                "algae3Collected":${stateToInt(algae3Collected.value).toString()},
                "algae2Collected":${stateToInt(algae2Collected.value).toString()},
                "algae1Collected":${stateToInt(algae1Collected.value).toString()},
                "algaeProcessed":${algaeProcessed.intValue.toString()},
                "algaeRemoved":${algaeRemoved.intValue.toString()},
                "autoCoralLevel4Scored":${autoCoralLevel4Scored.intValue.toString()},
                "autoCoralLevel3Scored":${autoCoralLevel3Scored.intValue.toString()},
                "autoCoralLevel2Scored":${autoCoralLevel2Scored.intValue.toString()},
                "autoCoralLevel1Scored":${autoCoralLevel1Scored.intValue.toString()},
                "autoCoralLevel4Missed":${autoCoralLevel4Missed.intValue.toString()},
                "autoCoralLevel3Missed":${autoCoralLevel3Missed.intValue.toString()},
                "autoCoralLevel2Missed":${autoCoralLevel2Missed.intValue.toString()},
                "autoCoralLevel1Missed":${autoCoralLevel1Missed.intValue.toString()},
                "autoNetScored":${autoNetScored.intValue.toString()},
                "autoNetMissed":${autoNetMissed.intValue.toString()},
                "autoStop":${autoStop.intValue.toString()},
                "teleNet":${teleNet.intValue.toString()},
                "teleNetMissed":${teleNetMissed.intValue.toString()},
                "teleLFour":${teleLFour.intValue.toString()},
                "teleLThree":${teleLThree.intValue.toString()},
                "teleLThreeAlgae":${teleLThreeAlgae.intValue.toString()},
                "teleLTwo":${teleLTwo.intValue.toString()},
                "teleLTwoAlgae":${teleLTwoAlgae.intValue.toString()},
                "teleLOne":${teleLOne.intValue.toString()},
                "teleProcessed":${teleProcessed.intValue.toString()},
                "teleLFourMissed":${teleLFourMissed.intValue.toString()},
                "teleLThreeMissed":${teleLThreeMissed.intValue.toString()},
                "teleLTwoMissed":${teleLTwoMissed.intValue.toString()},
                "teleLOneMissed":${teleLOneMissed.intValue.toString()},
                "lostComms":${lostComms.intValue.toString()},
                "playedDefense":${playedDefense.value.toString()},
                "aDeep":${aDeep.value.toString()},
                "bDeep":${bDeep.value.toString()},
                "cDeep":${cDeep.value.toString()},
                "aClimb":${stateToInt(aClimb.value).toString()},
                "bClimb":${stateToInt(bClimb.value).toString()},
                "cClimb":${stateToInt(cClimb.value).toString()},
                "notes":"${notes.value}"
                }
            """.trimIndent()

        //WAS PREVIOUSLY USED
        //    val teleNotesFinal = "autopath:${autos.value}:${teleNotes.value}:${scoutName.value}"
    )
}

fun loadData(match: Int, team: MutableIntState, robotStartPosition: MutableIntState) {
    reset()

    fun intToState(i: Int) = when (i) {
        0 -> ToggleableState.Off
        1 -> ToggleableState.Indeterminate
        2 -> ToggleableState.On
        else -> ToggleableState.Off
    }

    fun isNumber(string : String) : Boolean {
        try {
            string.toInt()
        } catch (e : Exception) {
            return false
        }
        return true
    }

    val array : Array<String> = createOutput(team, robotStartPosition).split("\n").toTypedArray()

    array.withIndex().forEach { (index, it) ->
        var firstIndex : Int
        for (letter in it) {
            if (letter == ':') {
                firstIndex = index+1
                array[index] = array[index].substring(firstIndex, it.length-1)
            }
        }
    }



//    if(matchScoutArray[robotStartPosition.intValue]?.get(match)?.isEmpty() == false) {

//        val help = matchScoutArray[robotStartPosition.intValue]?.get(match)?.split('\n') ?: createOutput(team, robotStartPosition).split('\n')
//        team.intValue = parseInt(help[1])
//
//        autoSpeakerNum.intValue = parseInt(help[3])
//        autoAmpNum.intValue = parseInt(help[4])
//        autoSMissed.intValue = parseInt(help[5])
//        autoAMissed.intValue = parseInt(help[6])
//        autoStop.intValue = parseInt(help[7])
//        telePassed.intValue = parseInt(help[8])
//        teleSpeakerNum.intValue = parseInt(help[9])
//        teleAmpNum.intValue = parseInt(help[10])
//        teleTrapNum.intValue = parseInt(help[11])
//        teleSMissed.intValue = parseInt(help[12])
//        teleAMissed.intValue = parseInt(help[13])
//        teleSReceived.intValue = parseInt(help[14])
//        teleAReceived.intValue = parseInt(help[15])
//        lostComms.intValue = parseInt(help[16])
//
//        val teleCommentsSplit = help[17].split(':')
//        autos.value = teleCommentsSplit[1]
//        teleNotes.value = teleCommentsSplit[2]
//        scoutName.value = teleCommentsSplit[3]
//        println(autos)

//    }
}

fun reset(){
//    auto.intValue = 0
//    autoAmpNum.intValue = 0
//    collected.intValue = 0
//    autoSMissed.intValue = 0
//    autoAMissed.intValue = 0
//    autos.value = ""
//    lostComms.intValue = 0
//    teleSpeakerNum.intValue = 0
//    teleAmpNum.intValue = 0
//    teleTrapNum.intValue = 0
//    teleSMissed.intValue = 0
//    teleAMissed.intValue = 0
//    teleSReceived.intValue = 0
//    teleAReceived.intValue = 0
//    autoStop.intValue = 0
//    telePassed.intValue = 0
//    teleNotes.value = ""
}

//private fun delimString(vararg inputs: Any) : String {
//    val endString = StringBuilder("{\n")
//    inputs.withIndex().forEach { (index, it) ->
//        if(isNumber(it)) {
//            endString.append ("\"${array[index]}\":" + it + "\n")
//        } else {
//            endString.append ("\"${array[index]}\":\"" + it + "\"\n")
//        }
//    }
//    endString.deleteAt(endString.lastIndex)
//    endString.append("}")
//    return endString.toString()
//}
//
//private fun isNumber(input : Any) : Boolean {
//    try {
//        input.toString().toInt()
//    } catch (e : Exception) {
//
//        return false
//    }
//    return true
//}
//
//val array = arrayOf(
//    "match",
//    "team",
//    "robotStartPosition",
//    "autoFeederCollection",
//    "coral3Collected",
//    "coral2Collected",
//    "coral1Collected",
//    "algae3Collected",
//    "algae2Collected",
//    "algae1Collected",
//    "algaeProcessed",
//    "algaeRemoved",
//    "autoCoralLevel4Scored",
//    "autoCoralLevel3Scored",
//    "autoCoralLevel2Scored",
//    "autoCoralLevel1Scored",
//    "autoCoralLevel4Missed",
//    "autoCoralLevel3Missed",
//    "autoCoralLevel2Missed",
//    "autoCoralLevel1Missed",
//    "autoNetScored",
//    "autoNetMissed",
//    "autoStop",
//    "teleNet",
//    "teleNetMissed",
//    "teleLFour",
//    "teleLThree",
//    "teleLThreeAlgae",
//    "teleLTwo",
//    "teleLTwoAlgae",
//    "teleLOne",
//    "teleProcessed",
//    "teleLFourMissed",
//    "teleLThreeMissed",
//    "teleLTwoMissed",
//    "teleLOneMissed",
//    "lostComms",
//    "playedDefense",
//    "aDeep",
//    "bDeep",
//    "cDeep",
//    "aClimb",
//    "bClimb",
//    "cClimb",
//    "notes"
//)