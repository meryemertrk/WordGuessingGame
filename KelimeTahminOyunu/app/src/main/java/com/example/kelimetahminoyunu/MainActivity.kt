package com.example.kelimetahminoyunu

import android.content.Intent
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
import java.sql.DriverManager.println

class MainActivity : AppCompatActivity() {

    val database = Firebase.database
    val referans = database.getReference("kullanicibilgileri")


    private data class Kullanici(
        val kullaniciAdi: String,
        val sifre: String
    )
      private val kullaniciListesi: MutableList<Kullanici> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val kullaniciAdi = findViewById<EditText>(R.id.kullaniciAdi)
        val sifre = findViewById<EditText>(R.id.sifre)



        //Giriş Yap butonuna basıldığında oyun odası seçimi sayfasına yönlendirme
        findViewById<Button>(R.id.btnGirisYap).setOnClickListener {
            val user = object {
                val kullaniciAdi = kullaniciAdi.text.toString()
                val sifre = sifre.text.toString()
            }

            var bilgilerDogru = false

            kullaniciListesi.forEach{
                if((it.kullaniciAdi == user.kullaniciAdi) and (it.sifre == user.sifre)){
                    bilgilerDogru = true
                }
            }

            if(bilgilerDogru) {
                Toast.makeText(this, "Giriş Başarılı", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MainActivity, OyunOdalari::class.java)
                startActivity(intent)

            }


            else Toast.makeText(this, "Hatalı Giriş", Toast.LENGTH_SHORT).show()

        }

        //Kayıt Ol butonuna basıldığında kayıt olma sayfasına yönlendirme
        findViewById<Button>(R.id.btnKayitOl).setOnClickListener {
            val intent = Intent(this@MainActivity, MainKayitOlSayfasi::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        val kullaniciListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                kullaniciListesi.clear()
                for (user in dataSnapshot.children) {
                    val kayitKullaniciAdi = user.child("kayitKullaniciAdi").getValue(String::class.java)
                    val kayitSifre = user.child("kayitSifre").getValue(String::class.java)

                    println("$kayitKullaniciAdi, $kayitSifre")
                    kullaniciListesi.add(Kullanici(kayitKullaniciAdi.toString(), kayitSifre.toString()))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("hata olustu: $databaseError")
            }
        }
        referans.addListenerForSingleValueEvent(kullaniciListener)
        println("updated user list in MainActivity page!")
    }
}