package com.example.mad_assignment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fun animateCardWithObjectAnimator(cardView: MaterialCardView) {
            val animator = ObjectAnimator.ofFloat(cardView, "translationY", 0f, 15f)  // 15f can be adjusted
            animator.duration = 1000  // 1-second duration
            animator.interpolator = LinearInterpolator()
            animator.repeatMode = ObjectAnimator.REVERSE
            animator.repeatCount = ObjectAnimator.INFINITE
            animator.start()
        }

        fun animateCardWithViewPropertyAnimator(cardView: MaterialCardView) {
            // This alternative method uses ViewPropertyAnimator for up and down movement
            cardView.animate()
                .translationYBy(15f)  // Move down by 15 pixels
                .setDuration(1000)    // Duration for one direction
                .setInterpolator(LinearInterpolator())
                .withEndAction {
                    cardView.animate()
                        .translationYBy(-15f)  // Move back up by 15 pixels
                        .setDuration(1000)
                        .start()
                }
                .start()
        }
    }
    }
