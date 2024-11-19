package dev.modena.listen

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.modena.listen.ui.theme.ListenTheme

class MainActivity : ComponentActivity(), Application.ActivityLifecycleCallbacks {

    companion object {
        const val TAG = "MyLifeCycle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LayoutMainActivity(
                        text = "Activity for receiver on lifecycles in MainActivity2",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        this.application.registerActivityLifecycleCallbacks(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.application.unregisterActivityLifecycleCallbacks(this)
    }

    override fun onActivityStarted(activity: Activity) {
        if (activity is MainActivity2) Log.i(TAG,"onActivityStarted")
    }

    override fun onActivityStopped(activity: Activity) {
        if (activity is MainActivity2) Log.i(TAG,"onActivityStopped")
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity is MainActivity2) Log.i(TAG,"onActivityCreated")
    }

    override fun onActivityResumed(activity: Activity) {
        if (activity is MainActivity2) Log.i(TAG,"onActivityResumed")
    }

    override fun onActivityPaused(activity: Activity) {

        if (activity is MainActivity2) Log.i(TAG,"onActivityPaused")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        if (activity is MainActivity2) Log.i(TAG,"onActivitySaveInstanceState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity is MainActivity2) Log.i(TAG,"onActivityDestroyed")
    }
}

@Composable
fun LayoutMainActivity(text: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                context.startActivity(Intent(context, MainActivity2::class.java))
            }
        ) {
            Text(text = "Go to Activity 2")
        }
    }
}
