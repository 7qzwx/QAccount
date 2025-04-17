package qzwx.app.qaccount.navigation

object NavRoutes {
    // 主底部导航的页面
    const val HomePage = "HomePage"
    const val MyPage = "MyPage"
    const val AssetPage = "AssetPage"
    const val StatisticsPage = "StatisticsPage"

    // 不需要底部导航的页面
    const val AiAccount = "AiAccount"


    // 需要显示底部导航的路径集合
    val bottomNavScreens = setOf(
        HomePage,
        MyPage,
        AssetPage,
        StatisticsPage
    )
}