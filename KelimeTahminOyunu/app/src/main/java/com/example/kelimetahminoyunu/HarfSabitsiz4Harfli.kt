package com.example.kelimetahminoyunu

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class HarfSabitsiz4Harfli : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_harf_sabitsiz4_harfli)

        val editTextText = findViewById<EditText>(R.id.editTextText)
        val editTextText2 = findViewById<EditText>(R.id.editTextText2)
        val editTextText3 = findViewById<EditText>(R.id.editTextText3)
        val editTextText4 = findViewById<EditText>(R.id.editTextText4)
        val buttonOnayla = findViewById<Button>(R.id.buttonOnayla)

        // Büyük harfler ve Türkçe karakterleri desteklemek için inputType özelliğini güncelleme
        editTextText.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        editTextText2.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        editTextText3.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        editTextText4.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS

        editTextText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    editTextText.requestFocus()
                } else {
                    editTextText2.requestFocus()
                }
            }
        })

        editTextText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    editTextText.requestFocus()
                } else {
                    editTextText3.requestFocus()
                }
            }
        })

        editTextText3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    editTextText2.requestFocus()
                } else {
                    editTextText4.requestFocus()
                }
            }
        })

        editTextText4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    editTextText3.requestFocus()
                } else {
                    buttonOnayla.requestFocus()
                }
            }
        })

        //onayla butonuna basıldığında oyun ekranı açılacak.
        findViewById<Button>(R.id.buttonOnayla).setOnClickListener {
            val intent = Intent(this@HarfSabitsiz4Harfli, OyunEkrani::class.java)
            startActivity(intent)
        }
    }


}