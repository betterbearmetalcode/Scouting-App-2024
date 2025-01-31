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
import java.lang.Integer.parseInt
import compKey

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

class teamMatchKey(
    match: Int,
    team: Int
) {
    var match = match
    var team = team
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
                "comp":"${compKey}",
                "scoutName":"${scoutName.value}",
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
    )
}

fun loadData(match: Int, team: MutableIntState, robotStartPosition: MutableIntState) {

    fun intToState(i: Int) = when (i) {
        0 -> ToggleableState.Off
        1 -> ToggleableState.Indeterminate
        2 -> ToggleableState.On
        else -> ToggleableState.Off
    }

    //Null possibility will most likely never happen.
    val list = (teamDataArray[teamMatchKey(match, team.value)]?.split("\n"))?.toMutableList()?: createOutput(team, robotStartPosition).split("\n").toMutableList()

    println(list)

    list.withIndex().forEach { (index, it) ->
        var firstIndex: Int
        for ((letterIndex, letter) in it.withIndex()) {
            if (letter == ':') {
                firstIndex = letterIndex + 1
                list[index] = it.substring(firstIndex, it.length - 1)
            } else if(index == list.lastIndex-1 && letterIndex == it.lastIndex) {
                list[index] += "\""
            }
        }
    }
    list.removeAt(0)
    if(list.lastIndex == 47) { //TODO: IMPROVE THIS
        list.removeAt(list.lastIndex)
    }

    println(list)

    if(teamDataArray[teamMatchKey(match, team.value)]?.isEmpty() == false) {

        team.intValue = parseInt(list[2])
        compKey = list[3]
        scoutName.value = list[4]
        robotStartPosition.intValue = parseInt(list[5])
        autoFeederCollection.intValue = parseInt(list[6])
        coral3Collected.value = intToState(parseInt(list[7]))
        coral2Collected.value = intToState(parseInt(list[8]))
        coral1Collected.value = intToState(parseInt(list[9]))
        algae3Collected.value = intToState(parseInt(list[10]))
        algae2Collected.value = intToState(parseInt(list[11]))
        algae1Collected.value = intToState(parseInt(list[12]))
        algaeProcessed.intValue = parseInt(list[13])
        algaeRemoved.intValue = parseInt(list[14])
        autoCoralLevel4Scored.intValue = parseInt(list[15])
        autoCoralLevel3Scored.intValue = parseInt(list[16])
        autoCoralLevel2Scored.intValue = parseInt(list[17])
        autoCoralLevel1Scored.intValue = parseInt(list[18])
        autoCoralLevel4Missed.intValue = parseInt(list[19])
        autoCoralLevel3Missed.intValue = parseInt(list[20])
        autoCoralLevel2Missed.intValue = parseInt(list[21])
        autoCoralLevel1Missed.intValue = parseInt(list[22])
        autoNetScored.intValue = parseInt(list[23])
        autoNetMissed.intValue = parseInt(list[24])
        autoStop.intValue = parseInt(list[25])
        teleNet.intValue = parseInt(list[26])
        teleNetMissed.intValue = parseInt(list[27])
        teleLFour.intValue = parseInt(list[28])
        teleLThree.intValue = parseInt(list[29])
        teleLThreeAlgae.intValue = parseInt(list[30])
        teleLTwo.intValue = parseInt(list[31])
        teleLTwoAlgae.intValue = parseInt(list[32])
        teleLOne.intValue = parseInt(list[33])
        teleProcessed.intValue = parseInt(list[34])
        teleLFourMissed.intValue = parseInt(list[35])
        teleLThreeMissed.intValue = parseInt(list[36])
        teleLTwoMissed.intValue = parseInt(list[37])
        teleLOneMissed.intValue = parseInt(list[38])
        lostComms.intValue = parseInt(list[39])
        playedDefense.value = list[40].toBoolean()
        aDeep.value = list[41].toBoolean()
        bDeep.value = list[42].toBoolean()
        cDeep.value = list[43].toBoolean()
        aClimb.value = intToState(parseInt(list[44]))
        bClimb.value = intToState(parseInt(list[45]))
        cClimb.value = intToState(parseInt(list[46]))
        notes.value = list[47]

    }
}

fun reset(){

    compKey = ""
    scoutName.value = ""
    autoFeederCollection.intValue = 0
    coral3Collected.value = ToggleableState.Off
    coral2Collected.value = ToggleableState.Off
    coral1Collected.value = ToggleableState.Off
    algae3Collected.value = ToggleableState.Off
    algae2Collected.value = ToggleableState.Off
    algae1Collected.value = ToggleableState.Off
    algaeProcessed.intValue = 0
    algaeRemoved.intValue = 0
    autoCoralLevel4Scored.intValue = 0
    autoCoralLevel3Scored.intValue = 0
    autoCoralLevel2Scored.intValue = 0
    autoCoralLevel1Scored.intValue = 0
    autoCoralLevel4Missed.intValue = 0
    autoCoralLevel3Missed.intValue = 0
    autoCoralLevel2Missed.intValue = 0
    autoCoralLevel1Missed.intValue = 0
    autoNetScored.intValue = 0
    autoNetMissed.intValue = 0
    autoStop.intValue = 0
    teleNet.intValue = 0
    teleNetMissed.intValue = 0
    teleLFour.intValue = 0
    teleLThree.intValue = 0
    teleLThreeAlgae.intValue = 0
    teleLTwo.intValue = 0
    teleLTwoAlgae.intValue = 0
    teleLOne.intValue = 0
    teleProcessed.intValue = 0
    teleLFourMissed.intValue = 0
    teleLThreeMissed.intValue = 0
    teleLTwoMissed.intValue = 0
    teleLOneMissed.intValue = 0
    lostComms.intValue = 0
    playedDefense.value = false
    aDeep.value = false
    bDeep.value = false
    cDeep.value = false
    aClimb.value = ToggleableState.Off
    bClimb.value = ToggleableState.Off
    cClimb.value = ToggleableState.Off
    notes.value = ""

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