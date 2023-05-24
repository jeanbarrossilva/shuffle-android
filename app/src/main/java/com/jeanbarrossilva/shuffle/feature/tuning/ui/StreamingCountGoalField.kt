package com.jeanbarrossilva.shuffle.feature.tuning.ui

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme

@Composable
internal fun StreamingCountGoalField(
    streamingCountGoal: Int,
    onStreamingCountGoalChange: (streamingCountGoal: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        "$streamingCountGoal",
        onValueChange = {
            val newStreamingCountGoal = it.toInt()
            onStreamingCountGoalChange(newStreamingCountGoal)
        },
        modifier,
        label = { Text("Meta de streams") },
        leadingIcon = { Icon(ShuffleTheme.Icons.BarChart, contentDescription = "Meta") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun StreamingCountGoalFieldPreview() {
    ShuffleTheme {
        StreamingCountGoalField(streamingCountGoal = 1_000_000, onStreamingCountGoalChange = { })
    }
}
