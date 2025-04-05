package com.example.admin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admin.databinding.ActivityMainBinding
import com.example.admin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener{
            val referenceSchoolName = binding.referenceSchoolName.text.toString()
            val updateLanguages = binding.updateLanguages.text.toString()

            updateData(referenceSchoolName, updateLanguages)
        }
    }

    private fun updateData(schoolName: String, languages: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("School Information")
        val schoolData = mapOf("schoolName" to schoolName, "languages" to languages)
        databaseReference.child(schoolName).updateChildren(schoolData).addOnSuccessListener {
            binding.referenceSchoolName.text.clear()
            binding.updateLanguages.text.clear()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
        }
    }
}