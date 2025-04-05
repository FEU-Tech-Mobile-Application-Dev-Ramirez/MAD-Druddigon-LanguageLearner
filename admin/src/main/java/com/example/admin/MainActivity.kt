package com.example.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainupload.setOnClickListener{
            val intent = Intent(this@MainActivity, Upload_activity::class.java)
            startActivity(intent)
            finish()
        }
        binding.mainupdate.setOnClickListener{
            val intent = Intent(this@MainActivity, UpdateActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.maindelete.setOnClickListener{
            val intent = Intent(this@MainActivity, DeleteActivity::class.java)
            startActivity(intent)
            finish()
        }


        }
    }
