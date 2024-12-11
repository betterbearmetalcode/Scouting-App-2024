package pages.twtyTwtyFour

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import nodes.RootNode
import androidx.compose.runtime.MutableState
import com.bumble.appyx.components.backstack.BackStack
import pageStructures.twtyTwtyFour.RootNode

expect class PitsScoutMenu(
    buildContext: BuildContext,
    backStack: BackStack<RootNode.NavTarget>,
    pitsPerson: MutableState<String>,
    scoutName: MutableState<String>
) : Node {
    @Composable
    override fun View(modifier: Modifier)

}
