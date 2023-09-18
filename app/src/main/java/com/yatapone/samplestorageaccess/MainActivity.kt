package com.yatapone.samplestorageaccess

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.yatapone.samplestorageaccess.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener { createFile() }
        binding.buttonOpen.setOnClickListener { openFile() }
    }

    private fun createFile() {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        // intent.setType("*/*");
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TITLE, "example.txt")

        startActivityForResultCreate.launch(intent)
    }

    private val startActivityForResultCreate = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d(TAG, "startActivityForResultCreate: result=$result")
        if (result.resultCode == RESULT_OK && result.data != null) {
            val uri = result.data!!.data
            binding.textViewSave.text = uri.toString()
        }
    }

    private fun openFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        // intent.setType("*/*");
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TITLE, "example.txt")

        startActivityForResultOpen.launch(intent)
    }

    private val startActivityForResultOpen = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d(TAG, "startActivityForResultOpen: result=$result")
        if (result.resultCode == RESULT_OK && result.data != null) {
            val uri = result.data!!.data
            binding.textViewOpen.text = uri.toString()
        }
    }
}