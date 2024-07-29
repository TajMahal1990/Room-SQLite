package com.example.roomsqlmvvm

import android.app.Activity
import android.content.ClipData
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.roomsqlmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDataBase.getDataBase(this)

        db.getDao().getAllItem().asLiveData().observe(this) { list ->
            binding.tvList.text = ""
            list.forEach {
                val text = "ID: ${it.id} Name: ${it.name} Price: ${it.phoneNumber}\n"
                binding.tvList.append(text)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        binding.btnSave.setOnClickListener {
            val item = Item(null,
                binding.editTextName.text.toString(),
                binding.editTextSurname.text.toString(),
                binding.editTextPhoneNumber.text.toString() )

            Thread {
                db.getDao().insertItem(item)
            }.start()

            binding.editTextName.text.clear()
            binding.editTextSurname.text.clear()
            binding.editTextPhoneNumber.text.clear()
        }


    }
}