package edu.aku.hassannaqvi.blf_screening.ui.other

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import edu.aku.hassannaqvi.blf_screening.CONSTANTS
import edu.aku.hassannaqvi.blf_screening.R
import kotlinx.coroutines.*

/*
* @author Ali Azaz Alam dt. 12.16.20
* */
class SplashscreenActivity : AppCompatActivity() {
    private lateinit var activityScope: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        activityScope = launchSplashScope()

        /*
        * Show FullScreen
        * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) window.insetsController?.hide(WindowInsets.Type.statusBars())
        else window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onPause() {
        super.onPause()
        activityScope.cancel()
    }

    override fun onResume() {
        super.onResume()
        if (activityScope.isActive.not())
            launchSplashScope()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }

    private fun launchSplashScope() =
            CoroutineScope(Dispatchers.Main).launch {
                delay(SPLASH_TIME_OUT.toLong())
                finish()
                startActivity(Intent(this@SplashscreenActivity, LoginActivity::class.java))
            }

    companion object {
        private const val SPLASH_TIME_OUT = 1000
    }
}