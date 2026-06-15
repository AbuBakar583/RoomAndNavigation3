package org.roomnav.project


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import org.roomnav.NotesScreenModel

@Composable
fun App(model: NotesScreenModel) {
    val backStack = remember { mutableStateListOf<ScreenNavigation>(ScreenNavigation.ScreenA) }
    MaterialTheme {
        NavDisplay(backStack = backStack, entryProvider = entryProvider {
            entry<ScreenNavigation.ScreenA> {
                ScreenA(model = model,goToScreenB = {

                    backStack.add(ScreenNavigation.ScreenB)
                })
            }

            entry<ScreenNavigation.ScreenB> {
                ScreenB(goToScreenA = {
                    backStack.clear()
                    backStack.add(ScreenNavigation.ScreenA)
                })
            }


        })
    }
}