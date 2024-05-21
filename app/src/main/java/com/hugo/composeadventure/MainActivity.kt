package com.hugo.composeadventure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hugo.composeadventure.ui.theme.ComposeAdventureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAdventureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        colors = if (expanded) {
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            )
        } else {
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        },
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
    ) {
        CardContent(name = name, expanded = expanded, onExpandChange = {expanded = it})
    }
}

@Composable
private fun CardContent(name: String, expanded: Boolean, onExpandChange: (Boolean) -> Unit) {
    Row(
        modifier =
        Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow,
                ),
            ),
    ) {
        Column(
            modifier =
            Modifier
                .weight(1f)
                .padding(12.dp),
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style =
                MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
            )
            if (expanded) {
                Text(
                    text =
                    ("Composem ipsum color sit lazy, " +
                                    "padding theme elit, sed do bouncy. "
                            ).repeat(4),
                )
            }
        }

        IconButton(onClick = { onExpandChange(!expanded) }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.Clear else Icons.Filled.MoreVert,
                contentDescription =
                if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                },
            )
        }
    }
}

@Composable
fun AsyncImage(model: Any, contentDescription: String, modifier: Any) {

}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Welcome to App")
        ElevatedButton(
            modifier = modifier.padding(vertical = 24.dp),
            onClick = { onContinueClicked() },
        ) {
            Text(text = "Continue")
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" },
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
                .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "HomeScreen Title", style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
            Icon(
                imageVector = Icons.Outlined.Android,
                contentDescription = "An Android icon"
            )
        }

        Greetings()
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
    ) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = !shouldShowOnboarding })
        } else {
            HomeScreen()
        }
    }
}

@Preview(showBackground = true, name = "Text preview", widthDp = 410, heightDp = 900)
@Composable
fun GreetingPreview() {
    ComposeAdventureTheme {
        MyApp(Modifier.fillMaxSize())
    }
}
