package pages

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.pop
import composables.EnumerableValue
import composables.Comments
import defaultSecondary
import exportScoutData
import keyboardAsState
import nodes.matchScoutArray
import nodes.*
import nodes.twtyTwtyFour.*
import java.io.File
import java.lang.Integer.parseInt

@Composable
actual fun TeleMenu (
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
    var qrCodeBytes by remember{ mutableStateOf(File("src/commonMain/resources/Empty Qr Code.png").readBytes())}

    fun bob() {
        mainMenuBackStack.pop()
        matchScoutArray.putIfAbsent(robotStartPosition.intValue, HashMap())
        matchScoutArray[robotStartPosition.intValue]?.set(parseInt(match.value), createOutput(team, robotStartPosition))
        exportScoutData()
    }

    if(!isKeyboardOpen){
        isScrollEnabled.value = true
    }

    Column(
        Modifier
            .verticalScroll(state = scrollState, enabled = isScrollEnabled.value)
            .padding(20.dp)) {

        EnumerableValue(label = "Speaker" , value = teleSpeakerNum)
        EnumerableValue(label = "Amp" , value = teleAmpNum)
        EnumerableValue(label = "Shuttled", value = telePassed)
        EnumerableValue(label = "Trap" , value = teleTrapNum)
        Spacer(modifier = Modifier.height(30.dp))
        EnumerableValue(label = "S Missed", value = teleSMissed)
        EnumerableValue(label = "A Missed", value = teleAMissed)

        Row {
            Text("Lost Comms?")
            Checkbox(
                when(lostComms.intValue) {0 -> false; 1 -> true; else -> false},
                onCheckedChange = { when(it) {true -> lostComms.intValue = 1; false -> lostComms.intValue = 0} })
        }

        Divider(color = Color.Black, thickness = 4.dp)

        Comments(teleNotes, isScrollEnabled)

//        OutlinedButton(
//            border = BorderStroke(3.dp, Color.Yellow),
//            shape = RoundedCornerShape(25.dp),
//            colors = ButtonDefaults.buttonColors(backgroundColor = defaultSecondary),
//            onClick = {
//                val outputString = createOutput(team, robotStartPosition)
//
//                val qrCode = QRCode.ofSquares()
//                    .withSize(12)
//                    .withBackgroundColor(Colors.GOLD)
//                    .withColor(Colors.BLACK)
//                    .build(outputString)
//
//                val pngBytes = qrCode.render()
//
//                qrCodeBytes = pngBytes.getBytes()
//            }
//        ) {
//            Text("Export to QR code")
//        }
//
//        Image(
//            painter = BitmapPainter(org.jetbrains.skia.Image.makeFromEncoded(qrCodeBytes).toComposeImageBitmap()),
//            contentDescription = "QR Code",
//            contentScale = ContentScale.Fit,
//            modifier = Modifier
//                .fillMaxSize(1.25f)
//        )

        Spacer(Modifier.height(15.dp))

        OutlinedButton(
            border = BorderStroke(3.dp, Color.Yellow),
            shape = RoundedCornerShape(25.dp),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = defaultSecondary),
            onClick = {
                matchScoutArray.putIfAbsent(robotStartPosition.intValue, HashMap())
                matchScoutArray[robotStartPosition.intValue]?.set(parseInt(match.value),
                    createOutput(team, robotStartPosition)
                )
                match.value = (parseInt(match.value) + 1).toString()
                reset()
                teleNotes.value = ""
                selectAuto.value = false
                exportScoutData()
                loadData(parseInt(match.value), team, robotStartPosition)
                backStack.pop()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Next Match", fontSize = 20.sp)
        }

        OutlinedButton(
            border = BorderStroke(2.dp, color = Color.Yellow),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = defaultSecondary),
            onClick = {
                exportScoutData()
                bob()
            }
        ) {
            Text(
                text = "Back",
                color = Color.Yellow
            )
        }
    }
}