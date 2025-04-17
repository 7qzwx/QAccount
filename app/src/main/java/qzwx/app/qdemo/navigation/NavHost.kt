package qzwx.app.qdemo.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.*
import androidx.navigation.compose.*
import qzwx.app.qdemo.page.aiaccount.*
import qzwx.app.qdemo.page.assetpage.*
import qzwx.app.qdemo.page.homepage.*
import qzwx.app.qdemo.page.mypage.*
import qzwx.app.qdemo.page.statkstkcspage.*

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

