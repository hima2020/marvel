package org.rawafedtech.marvelapp.ui.navigation

import org.rawafedtech.marvelapp.utils.Constants


sealed class NavigationScreens(var screenRoute: String) {
    data object Home : NavigationScreens(Constants.HOME_ROUTES)
}