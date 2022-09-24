package com.ariefrahmanfajri.heartbeatanimationapp

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.ariefrahmanfajri.heartbeatanimationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var heartAnim: ObjectAnimator  = ObjectAnimator.ofPropertyValuesHolder(
            binding.ivHeart,
            PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f)
        )

        binding.btnBpm.setOnClickListener {
            if (binding.etBpm.text.toString().isNotEmpty()) {
                val bpm = binding.etBpm.text.toString().toLong()
                heartAnim.apply {
                    duration = bpm * 10L
                    repeatCount = ValueAnimator.INFINITE
                    repeatMode = ValueAnimator.REVERSE
                }
                heartAnim.start()
                if (heartAnim.isPaused) {
                    heartAnim.resume()
                }
            }
        }

        binding.btnStop.setOnClickListener {
            if (heartAnim.isStarted) {
                heartAnim.pause()
            }
        }
    }
}