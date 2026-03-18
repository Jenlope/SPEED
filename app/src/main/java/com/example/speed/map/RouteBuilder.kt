package com.example.speed.map

import com.mapbox.geojson.Point

fun addPoint(points: List<Point>, newPoint: Point): List<Point> {
    return points + newPoint
}

fun clearPoints(): List<Point> {
    return emptyList()
}