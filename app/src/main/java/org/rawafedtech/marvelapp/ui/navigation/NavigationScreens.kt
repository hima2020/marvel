package org.rawafedtech.marvelapp.ui.navigation

import org.rawafedtech.marvelapp.utils.Constants


sealed class NavigationScreens(var screenRoute: String) {
    data object Home : NavigationScreens(Constants.HOME_ROUTE)
    data object Details : NavigationScreens(Constants.DETAILS_ROUTE)
}