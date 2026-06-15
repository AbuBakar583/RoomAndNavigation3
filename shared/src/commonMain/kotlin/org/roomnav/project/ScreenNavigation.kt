package org.roomnav.project



sealed interface ScreenNavigation{
    data object ScreenA: ScreenNavigation
    data object ScreenB: ScreenNavigation
}