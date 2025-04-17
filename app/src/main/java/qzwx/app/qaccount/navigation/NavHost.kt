package qzwx.app.qaccount.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.*
import androidx.navigation.compose.*
import qzwx.app.qaccount.page.aiaccount.*
import qzwx.app.qaccount.page.assetpage.*
import qzwx.app.qaccount.page.homepage.*
import qzwx.app.qaccount.page.mypage.*
import qzwx.app.qaccount.page.statkstkcspage.*

@Composable
fun Q_NavHost(navController : NavHostController,modifier : Modifier) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = NavRoutes.HomePage
    ) {
        composable(NavRoutes.HomePage) { HomePage() }
        composable(NavRoutes.MyPage) { MyPage() }
        composable(NavRoutes.AssetPage) { AssetPage() }
        composable(NavRoutes.StatisticsPage) { StatisticsPage() }
        composable(NavRoutes.AiAccount) { AiAccount() }
    }
}

