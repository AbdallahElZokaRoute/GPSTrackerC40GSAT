package com.route.gpstrackerc40gsat

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

//                       // AppCompatActivity           ->      ComponentActivity
//                        //   setContent(layout_id or View)           setContent{
//
//                                                 }
class FirstComposeActivity : ComponentActivity() {
    // XML -> Views and ViewGroups
    // Compose -> Composable Functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FacebookPostItem()
        }
    }
}

@Composable
fun FacebookPostItem(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        val (profilePictureImage, name, date, postContent, postImage, like, comment, share) = createRefs()
        val horizontalChain = createHorizontalChain(like, comment, share)
        Image(
            painter = painterResource(id = R.drawable.ic_airplane_mode),
            contentDescription = stringResource(
                R.string.profile_image
            ),
            modifier = Modifier.constrainAs(profilePictureImage) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })
        Text(text = "Ahmed Mohamed", modifier = Modifier.constrainAs(name) {
            top.linkTo(profilePictureImage.top)
            start.linkTo(profilePictureImage.end)
        })
        Text(text = LoremIpsum(20).values.iterator().next(),
            modifier = Modifier.constrainAs(postContent) {
                top.linkTo(profilePictureImage.bottom)
                start.linkTo(parent.start)
            }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Post image", modifier = Modifier
                .constrainAs(postImage) {
                    top.linkTo(postContent.bottom)

                }
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        FacebookReactionButton("Like", modifier = Modifier.constrainAs(like) {
            top.linkTo(postImage.bottom)
            start.linkTo(parent.start)
        })
        FacebookReactionButton("Comment", modifier = Modifier.constrainAs(comment) {
            top.linkTo(postImage.bottom)
            start.linkTo(like.end)
        })
        FacebookReactionButton("Share", modifier = Modifier.constrainAs(share) {
            top.linkTo(postImage.bottom)
            end.linkTo(parent.end)
            start.linkTo(comment.end)
        })
    }
}

@Composable
fun FacebookReactionButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun FacebookReactionButtonPreview() {
    FacebookReactionButton("Like")
}

@Preview(showBackground = true)
@Composable
private fun FacebookPostItemPreview() {
    FacebookPostItem()
}

@Composable
fun SettingsList(modifier: Modifier = Modifier) {
    LazyColumn {
        val settings = Settings(
            R.drawable.ic_accessibility,
            "Accessibility And Other settings",
            description = "accessibility description"
        )
        items(500) {
            SettingsItem(settings = settings)
        }
    }
}

@Composable
fun SettingsItem(settings: Settings, modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(vertical = 2.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .background(Color.Gray, RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(
                id = settings.drawableId ?: R.drawable.ic_accessibility
            ),
            contentDescription = stringResource(R.string.icon_accessibility),
            modifier = Modifier.padding(4.dp)
        )
        Column {
            Text(text = settings.title ?: "")
            Text(text = settings.description ?: "")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsItemPreview() {
    val settings = Settings(
        R.drawable.ic_accessibility,
        "Accessibility And Other settings",
        description = "accessibility description"
    )
    SettingsItem(settings)
}

@Composable
fun GreetingNamesList(modifier: Modifier = Modifier) {
    // Containers ( Column)
    //            (Row)
    //            (Box)
    Row(modifier = modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround) {
        GreetingUser(name = "Ahmed")
        GreetingUser(name = "Mohamed")
        GreetingUser(name = "Aya")
    }
    // width or height or background or
    // padding
    // shape
    // android:layout_height=
    // android:background=

}

@Preview(showSystemUi = true)
@Composable
private fun GreetingNamesListPreview() {
    GreetingNamesList()
}

@Composable
fun GreetingUser(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(text = "Hello, $name !", modifier = modifier)
}

@Preview(
    showBackground = true
)
@Composable
fun GreetingUserPreview() {
    GreetingUser("Ahmed")
}
