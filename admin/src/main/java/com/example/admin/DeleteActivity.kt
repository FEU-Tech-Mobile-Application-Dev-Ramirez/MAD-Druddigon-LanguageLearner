package com.example.admin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admin.databinding.ActivityDeleteBinding
import com.example.admin.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener{
            val schoolName = binding.deleteSchoolName.text.toString()
            if (schoolName.isNotEmpty()){
                deleteData(schoolName)
            } else {
                Toast.makeText(this, "Please enter valid school name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(schoolName: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("School Information")
        databaseReference.child(schoolName).removeValue().addOnSuccessListener {
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show()
        }
    }
}