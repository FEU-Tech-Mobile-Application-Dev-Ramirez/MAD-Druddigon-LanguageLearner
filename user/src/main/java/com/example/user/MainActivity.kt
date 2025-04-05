package com.example.user

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.user.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener{
            val searchschoolName:String = binding.searchSchool.text.toString()
            if (searchschoolName.isNotEmpty()){
                readData(searchschoolName)
            } else {
                Toast.makeText(this, "Please enter school name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(schoolName: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("School Information")
        databaseReference.child(schoolName).get().addOnSuccessListener {
            if (it.exists()){
                val schoolName = it.child("schoolName").value
                val languagesOffered = it.child("languages").value
                Toast.makeText(this, "Results Found", Toast.LENGTH_SHORT).show()
                binding.searchSchool.text.clear()
                binding.readSchoolName.text= schoolName.toString()
                binding.readLanguagesOffered.text= languagesOffered.toString()
            } else {
                Toast.makeText(this, "School does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}