package com.jeanbarrossilva.shuffle.app.extensions

import com.jeanbarrossilva.shuffle.app.destination.destinations.Destination

internal operator fun Destination.contains(other: Destination?): Boolean {
    return other != null && (this == other || other.route.startsWith("$route/"))
}
