package com.example.navigateur

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigateur.ui.theme.NavigateurWebTheme
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bienvenue(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val urlText = remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = urlText.value,
            onValueChange = { urlText.value = it },
            label = { Text("Entrer URL") },
            keyboardActions = KeyboardActions.Default
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (URLUtil.isValidUrl(urlText.value)) {
                val intent = Intent(context, NavigateurActivity::class.java)
                intent.putExtra("url", urlText.value)
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Stp entrer une URL valid", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Ouvrir In-App navigateur")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (URLUtil.isValidUrl(urlText.value)) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(urlText.value)
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Stp entrer une URL valid", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Ouvrir dans un navigateur exterieur")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigateurWebTheme {
        Bienvenue()
    }
}


