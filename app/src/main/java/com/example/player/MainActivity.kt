package com.example.player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.player.ui.theme.PlayerTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.player.ui.theme.NavGraph

class MainActivity : ComponentActivity() {

    private val songs = listOf(
        Triple("São Paolo", "The Weeknd", R.drawable.theweeknd),
        Triple("Roman's Beat", "Nicholas Britell", R.drawable.britell),
        Triple("Fire", "Tõnu Naisoo Trio", R.drawable.naisoo)
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    NavGraph(navController = rememberNavController())
                }
            }
        }
    }
}

@Composable
fun PlayerLayout(navController: NavController){

    var result by remember { mutableStateOf(1) }

    val imageResource = when (result){
        1 -> R.drawable.theweeknd
        2 -> R.drawable.britell
        else -> R.drawable.naisoo
    }

    val songName = "São Paolo"
    val artistName = "The Weeknd"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString(),
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = songName,
            fontSize = 25.sp,
            color = Color.White,
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    navController.navigate("lyrics/$songName")
                }
        )
        Text(
            text = artistName,
            fontSize = 20.sp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable{
                    navController.navigate("artist/$artistName")
                }
        )

        Spacer(modifier = Modifier.height(80.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            ButtonsBar()
        }
    }


}

@Composable
fun ButtonsBar(modifier: Modifier = Modifier)
{

    var result by remember { mutableStateOf(1) }

    val imageResource = when (result){
        1 -> R.drawable.theweeknd
        2 -> R.drawable.britell
        else -> R.drawable.naisoo
    }

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically

    ){
        Buttons(
            icons = R.drawable.back,
            contentDescription = "back",
            onClick = { }
        )
        Buttons(
            icons = R.drawable.play,
            contentDescription = "play",
            onClick = {  }
        )
        Buttons(
            icons = R.drawable.next,
            contentDescription = "next",
            onClick = {  }
        )
        Buttons(
            icons = R.drawable.shuffle,
            contentDescription = "shuffle",
            onClick = { result =(1..3).random() }
        )


    }
}


@Composable
fun Buttons(
    icons: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(68.dp)
            .padding(6.dp)
            .border(2.dp,Color.LightGray, CircleShape)
    ) {
        Icon(
            painter = painterResource(id = icons),
            contentDescription = contentDescription,
            modifier = Modifier.size(32.dp),
            tint = Color.LightGray
        )
    }

}

@Composable
fun LyricsScreen(songName: String, navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Lyrics for $songName",
            color = Color.White,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(40.dp))


        Text(
            text = "Bota na boca, bota na cara, bota onde quiser\n" +
                    "Bota na boca, bota na cara, bota onde quiser\n" +
                    "O novinho me olhou e quis comer minha pepequinha\n" +
                    "Hoje eu vou dar pro novinho, fode, fode a Larissinha\n" +
                    "O novinho me olhou e quis comer minha pepequinha\n" +
                    "Hoje eu vou dar pro novinho, fode, fode a Larissinha\n" +
                    "Every time I try to run, you put your curse all over me\n" +
                    "I surrender at your feet, baby, put it all on me\n" +
                    "Every time I try to pray a way, you got me on my knees\n" +
                    "I surrender at your feet, baby, put it all on me",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {navController.popBackStack()}){
            Text(text = "Back", color = Color.White)
        }



    }
}

@Composable
fun ArtistInfoScreen(artistName: String?, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Artist Info: $artistName",
            color = Color.White,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Nobody makes feeling bad sound as good as The Weeknd.\n " +
                    "\nEven the singer’s sunniest tracks (“Can’t Feel My Face”, “Starboy”) " +
                    "feel anchored by darkness—the sense that pleasure is pain and beauty decays " +
                    "and you can’t have the night without the morning after.\n \nThe brainchild of Toronto " +
                    "singer Abel Tesfaye, the project took off in 2011 with a string of mixtapes (later " +
                    "collected as 2012’s Trilogy) that forged cavernous, falsetto-driven R&B with narratives " +
                    "drenched in drugs, sex and other regrettable decisions—a sound both sensuous and detached, " +
                    "featherlight and dead heavy.",
            color = Color.LightGray,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {navController.popBackStack()}){
            Text(text = "Back", color = Color.White)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, device ="id:pixel_7a")
@Composable
fun PlayerLayoutPreview() {
    PlayerTheme {
        //PlayerLayout()
    }
}

