package com.example.mad_assignment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class splash1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val appname = findViewById<TextView>(R.id.name)

        appname.alpha = 0f

        val fadeInAnimatior = ObjectAnimator.ofFloat(appname, "alpha", 0f, 1f).apply {
            duration = 2000
            startDelay = 300
        }
        fadeInAnimatior.start()

        fadeInAnimatior.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val intent = Intent(this@splash1, splash2::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}


//        Handler(Looper.getMainLooper()).postDelayed(300){
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent);
//            finish();
//        }