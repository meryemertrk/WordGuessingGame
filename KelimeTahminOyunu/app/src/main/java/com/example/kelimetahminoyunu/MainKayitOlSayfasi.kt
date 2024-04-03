package com.example.kelimetahminoyunu

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainKayitOlSayfasi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kayit_ol_sayfasi)
        supportActionBar?.hide()

        val database = Firebase.database
        val referans = database.getReference("kullanicibilgileri")

        data class User(val kayitKullaniciAdi: String, val kayitSifre: String)
        val kullaniciListesi: MutableList<User> = mutableListOf()

        //Veritabanından veri alma
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                kullaniciListesi.clear()
                for (user in dataSnapshot.children) {
                    val kayitKullaniciAdi = user.child("kayitKullaniciAdi").getValue(String::class.java)
                    val kayitSifre = user.child("kayitSifre").getValue(String::class.java)


                    println("$kayitKullaniciAdi, $kayitSifre")
                    kullaniciListesi.add(User(kayitKullaniciAdi.toString(),kayitSifre.toString()))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("hata olustu: $databaseError")
            }
        }
        referans.addListenerForSingleValueEvent(valueEventListener)

        val kayitKullaniciAdi = findViewById<EditText>(R.id.kayitKullaniciAdi)
        val kayitSifre = findViewById<EditText>(R.id.kayitSifre)




        //Kayıt ol butonuna basıldığında veritabanına kayıt ekleme
        findViewById<Button>(R.id.btnKaydet).setOnClickListener {
            val kullanici = object {
                val kayitKullaniciAdi = kayitKullaniciAdi.text.toString()
                val kayitSifre = kayitSifre.text.toString()
            }

            var isimKullanilmakta = false

            kullaniciListesi.forEach {
                if (it.kayitKullaniciAdi == kullanici.kayitKullaniciAdi) {
                    isimKullanilmakta = true
                }
            }

            if (!isimKullanilmakta and (kullanici.kayitSifre != "")) {
                val idReferans = referans.push()
                idReferans.setValue(kullanici)



                Toast.makeText(this, "Kullanıcı başarıyla kaydedildi", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, "Kullanıcı ismi daha önceden alınmıştır!", Toast.LENGTH_SHORT)
                .show()

            val intent = Intent(this@MainKayitOlSayfasi, MainActivity::class.java)
            startActivity(intent)



        }

    }
}