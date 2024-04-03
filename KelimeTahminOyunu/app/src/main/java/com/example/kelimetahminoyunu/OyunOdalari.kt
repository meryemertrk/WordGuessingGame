package com.example.kelimetahminoyunu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class OyunOdalari : AppCompatActivity() {

    val database = Firebase.database
    val referans = database.getReference("odalar")

    private data class Odalar(
        val odaAdi: String,
        var oyuncu1Id: String,
        val oyuncu2Id: String,
        val oyuncuDurum: String
    )

    private val odaListesi: MutableList<Odalar> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_oyun_odalari)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





        referans.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                odaListesi.clear()
                for (odaSnapshot in snapshot.children) {
                    val odaAdi = odaSnapshot.child("odaAdi").getValue(String::class.java) ?: ""
                    val oyuncu1Id =
                        odaSnapshot.child("oyuncu1Id").getValue(String::class.java) ?: ""
                    val oyuncu2Id =
                        odaSnapshot.child("oyuncu2Id").getValue(String::class.java) ?: ""
                    val oyuncuDurum =
                        odaSnapshot.child("oyuncuDurum").getValue(String::class.java) ?: ""
                    val oda = Odalar(odaAdi, oyuncu1Id, oyuncu2Id, oyuncuDurum)
                    odaListesi.add(oda)
                }

            }



            override fun onCancelled(error: DatabaseError) {
                // Veri okuma iptal edilirse yapılacak işlemler
                Toast.makeText(this@OyunOdalari, "Oda verileri okunamadı", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        tumOyunOdalariEkle()


        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(this@OyunOdalari, HarfSabitsiz4Harfli::class.java)
            startActivity(intent)
        }

    }


    fun tumOyunOdalariEkle() {
        val database = FirebaseDatabase.getInstance().reference.child("odalar")

        // Örnek oyun odalarını oluştur
        val oyunOda1 = hashMapOf(
            "odaAdi" to "Harf Sabitsiz 4 Harfli",
            "oyuncu1Id" to "",
            "oyuncu2Id" to "",
            "oyuncuDurum" to "Boş"
        )
        val oyunOda2 = hashMapOf(
            "odaAdi" to "Harf Sabitsiz 5 Harfli",
            "oyuncu1Id" to "",
            "oyuncu2Id" to "",
            "oyuncuDurum" to "Boş"
        )
        val oyunOda3 = hashMapOf(
            "odaAdi" to "Harf Sabitsiz 6 Harfli",
            "oyuncu1Id" to "",
            "oyuncu2Id" to "",
            "oyuncuDurum" to "Boş"
        )
        val oyunOda4 = hashMapOf(
            "odaAdi" to "Harf Sabitsiz 7 Harfli",
            "oyuncu1Id" to "",
            "oyuncu2Id" to "",
            "oyuncuDurum" to "Boş"
        )
        val oyunOda5 = hashMapOf(
            "odaAdi" to "Harf Sabitli 4 Harfli",
            "oyuncu1Id" to "",
            "oyuncu2Id" to "",
            "oyuncuDurum" to "Boş"
        )
        val oyunOda6 = hashMapOf(
            "odaAdi" to "Harf Sabitli 5 Harfli",
            "oyuncu1Id" to "",
            "oyuncu2Id" to "",
            "oyuncuDurum" to "Boş"
        )
        val oyunOda7 = hashMapOf(
            "odaAdi" to "Harf Sabitli 6 Harfli",
            "oyuncu1Id" to "",
            "oyuncu2Id" to "",
            "oyuncuDurum" to "Boş"
        )
        val oyunOda8 = hashMapOf(
            "odaAdi" to "Harf Sabitli 7 Harfli",
            "oyuncu1Id" to "",
            "oyuncu2Id" to "",
            "oyuncuDurum" to "Boş"
        )

        // Oyun odalarını Firebase Realtime Database'e ekledik
        database.child("oda1").setValue(oyunOda1)
        database.child("oda2").setValue(oyunOda2)
        database.child("oda3").setValue(oyunOda3)
        database.child("oda4").setValue(oyunOda4)
        database.child("oda5").setValue(oyunOda5)
        database.child("oda6").setValue(oyunOda6)
        database.child("oda7").setValue(oyunOda7)
        database.child("oda8").setValue(oyunOda8)
    }
}