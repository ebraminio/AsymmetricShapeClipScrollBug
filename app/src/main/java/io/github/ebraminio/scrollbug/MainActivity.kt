package io.github.ebraminio.scrollbug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import io.github.ebraminio.scrollbug.ui.theme.ScrollBugTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScrollBugTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    Box(Modifier.padding(paddingValues = paddingValues)) {
                        Content()
                    }
                }
            }
        }
    }
}

@Composable
fun Content() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        var bug by remember { mutableStateOf(true) }
        Row { Text("Bug: "); Switch(bug, { bug = it }) }
        Column(
            Modifier.clip(
                RoundedCornerShape(160.dp, 160.dp, 160.dp, if (bug) 0.dp else 160.dp)
            )
        ) {
            repeat(20) {
                var toggle by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(if (toggle) Color.Red else Color.Blue)
                        .clickable { toggle = !toggle },
                ) { Text(LoremIpsum(30).values.joinToString(" ")) }
            }
        }
    }
}
