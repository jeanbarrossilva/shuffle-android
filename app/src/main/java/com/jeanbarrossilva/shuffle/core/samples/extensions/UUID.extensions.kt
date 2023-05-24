package com.jeanbarrossilva.shuffle.core.samples.extensions

import java.util.UUID

internal fun uuid(): String {
    return UUID.randomUUID().toString()
}
