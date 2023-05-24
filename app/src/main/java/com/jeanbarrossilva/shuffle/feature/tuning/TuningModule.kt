package com.jeanbarrossilva.shuffle.feature.tuning

import com.jeanbarrossilva.shuffle.feature.tuning.scope.search.album.AlbumsSelectorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("FunctionName")
fun TuningModule(): Module {
    return module {
        viewModel {
            AlbumsSelectorViewModel(repository = get())
        }
    }
}
