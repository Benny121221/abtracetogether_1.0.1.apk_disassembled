package androidx.navigation.ui;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.ui.AppBarConfiguration.Builder;
import androidx.navigation.ui.AppBarConfiguration.OnNavigateUpListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"setupWithNavController", "", "Lcom/google/android/material/appbar/CollapsingToolbarLayout;", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "navController", "Landroidx/navigation/NavController;", "drawerLayout", "Landroidx/drawerlayout/widget/DrawerLayout;", "configuration", "Landroidx/navigation/ui/AppBarConfiguration;", "navigation-ui-ktx_release"}, k = 2, mv = {1, 1, 15})
/* compiled from: CollapsingToolbarLayout.kt */
public final class CollapsingToolbarLayoutKt {
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r0v5, types: [androidx.navigation.ui.AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    public static final void setupWithNavController(CollapsingToolbarLayout collapsingToolbarLayout, Toolbar toolbar, NavController navController, DrawerLayout drawerLayout) {
        Intrinsics.checkParameterIsNotNull(collapsingToolbarLayout, "$this$setupWithNavController");
        Intrinsics.checkParameterIsNotNull(toolbar, "toolbar");
        Intrinsics.checkParameterIsNotNull(navController, "navController");
        NavGraph graph = navController.getGraph();
        Intrinsics.checkExpressionValueIsNotNull(graph, "navController.graph");
        Function0 function0 = AppBarConfigurationKt$AppBarConfiguration$1.INSTANCE;
        Builder drawerLayout2 = new Builder(graph).setDrawerLayout(drawerLayout);
        if (function0 != 0) {
            function0 = new AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(function0);
        }
        AppBarConfiguration build = drawerLayout2.setFallbackOnNavigateUpListener((OnNavigateUpListener) function0).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        NavigationUI.setupWithNavController(collapsingToolbarLayout, toolbar, navController, build);
    }

    /* JADX WARNING: type inference failed for: r6v3 */
    /* JADX WARNING: type inference failed for: r5v6, types: [androidx.navigation.ui.AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    public static /* synthetic */ void setupWithNavController$default(CollapsingToolbarLayout collapsingToolbarLayout, Toolbar toolbar, NavController navController, AppBarConfiguration appBarConfiguration, int i, Object obj) {
        if ((i & 4) != 0) {
            NavGraph graph = navController.getGraph();
            Intrinsics.checkExpressionValueIsNotNull(graph, "navController.graph");
            DrawerLayout drawerLayout = null;
            Function0 function0 = AppBarConfigurationKt$AppBarConfiguration$1.INSTANCE;
            Builder drawerLayout2 = new Builder(graph).setDrawerLayout(drawerLayout);
            if (function0 != 0) {
                function0 = new AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(function0);
            }
            appBarConfiguration = drawerLayout2.setFallbackOnNavigateUpListener((OnNavigateUpListener) function0).build();
            Intrinsics.checkExpressionValueIsNotNull(appBarConfiguration, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        }
        setupWithNavController(collapsingToolbarLayout, toolbar, navController, appBarConfiguration);
    }

    public static final void setupWithNavController(CollapsingToolbarLayout collapsingToolbarLayout, Toolbar toolbar, NavController navController, AppBarConfiguration appBarConfiguration) {
        Intrinsics.checkParameterIsNotNull(collapsingToolbarLayout, "$this$setupWithNavController");
        Intrinsics.checkParameterIsNotNull(toolbar, "toolbar");
        Intrinsics.checkParameterIsNotNull(navController, "navController");
        Intrinsics.checkParameterIsNotNull(appBarConfiguration, "configuration");
        NavigationUI.setupWithNavController(collapsingToolbarLayout, toolbar, navController, appBarConfiguration);
    }
}
