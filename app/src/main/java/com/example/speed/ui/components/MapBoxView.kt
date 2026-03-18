package com.example.speed.ui.components

import com.example.speed.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.geojson.Point
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.mapbox.api.directions.v5.models.RouteOptions
import com.mapbox.navigation.base.extensions.applyDefaultNavigationOptions
import com.mapbox.navigation.base.extensions.applyLanguageAndVoiceUnitOptions
import com.mapbox.navigation.base.route.NavigationRoute
import com.mapbox.navigation.base.route.NavigationRouterCallback
import com.mapbox.navigation.base.route.RouterFailure
import com.mapbox.navigation.core.lifecycle.MapboxNavigationApp
import com.mapbox.navigation.ui.maps.route.line.api.MapboxRouteLineApi
import com.mapbox.navigation.ui.maps.route.line.api.MapboxRouteLineView
import com.mapbox.navigation.ui.maps.route.line.model.MapboxRouteLineApiOptions
import com.mapbox.navigation.ui.maps.route.line.model.MapboxRouteLineViewOptions

@Composable
fun MapBoxView(
    modifier: Modifier = Modifier,
    onPointsChanged: (List<Point>) -> Unit
) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val selectedPoints = remember { mutableListOf<Point>() }
    val routeLineApi = remember {
        MapboxRouteLineApi(
            MapboxRouteLineApiOptions.Builder().build()
        )
    }
    val routeLineView = remember {
        MapboxRouteLineView(
            MapboxRouteLineViewOptions.Builder(context).build()
        )
    }

    //"listens" for user to click on the map
    //and ads the point to the list of points
    DisposableEffect(Unit) {
        mapView.mapboxMap.loadStyle(
            Style.STANDARD
        ) {

            val pointAnnotationManager: PointAnnotationManager =
                mapView.annotations.createPointAnnotationManager()

            mapView.mapboxMap.addOnMapClickListener { point ->
                selectedPoints.add(point)
                onPointsChanged(selectedPoints.toList())

                // create bitmap from drawable
                val drawable =
                    ContextCompat.getDrawable(context, R.drawable.ic_point_marker)
                val bitmap = drawable!!.toBitmap()

                if (bitmap != null) {
                    pointAnnotationManager.create(
                        PointAnnotationOptions()
                            .withPoint(point)
                            .withIconImage(bitmap)
                            .withIconSize(0.8)
                    )
                }

                //this is the part that will create the route
                //from the list of selected points
                val mapboxNavigation = MapboxNavigationApp.current()

                if (mapboxNavigation != null && selectedPoints.size >= 2) {

                    val routeOptions = RouteOptions.builder()
                        .applyDefaultNavigationOptions()
                        .applyLanguageAndVoiceUnitOptions(context)
                        .coordinatesList(selectedPoints.toList())
                        .profile("walking")
                        .build()

                    mapboxNavigation.requestRoutes(
                        routeOptions,
                        object : NavigationRouterCallback {

                            //draws the route
                            override fun onRoutesReady(
                                routes: List<NavigationRoute>,
                                routerOrigin: String
                            ) {
                                Log.d("Route", "Routes ready: ${routes.size}")

                                routeLineApi.setNavigationRoutes(routes) { value ->
                                    mapView.mapboxMap.getStyle()?.let { style ->
                                        routeLineView.renderRouteDrawData(style, value)
                                    }
                                }
                            }

                            override fun onFailure(
                                reasons: List<RouterFailure>,
                                routeOptions: RouteOptions
                            ) {
                                Log.e("Route", "Failed: $reasons")
                            }

                            override fun onCanceled(
                                routeOptions: RouteOptions,
                                routerOrigin: String
                            ) {
                                Log.d("Route", "Route canceled")
                            }
                        }
                    )
                }
                true
            }
        }
        onDispose {
            routeLineApi.cancel()
        }

    }
    AndroidView(
        modifier = modifier,
        factory = { mapView }

    )
}



