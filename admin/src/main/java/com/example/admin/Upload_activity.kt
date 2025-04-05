package com.example.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Upload_activity : AppCompatActivity() {
    private lateinit var binding:ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener{
            val schoolName = binding.uploadName.text.toString()
            val languages = binding.uploadlanguage.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("School Information")
            val schoolData = SchoolData(schoolName,languages)
            databaseReference.child(schoolName).setValue(schoolData).addOnSuccessListener {
                binding.uploadName.text.clear()
                binding.uploadlanguage.text.clear()

                Toast.makeText(this,"SAVED", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Upload_activity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show()
            }
        }
    }
}