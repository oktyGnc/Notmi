package com.oktaygenc.notmi.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.oktaygenc.notmi.R
import com.oktaygenc.notmi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
        * 9. UI GeliştirmeFragment ve Activity’leri Oluştur:AddNoteFragment.kt (not eklemek için)NoteListFragment.kt (not listesini göstermek için)
            NoteDetailFragment.kt (not detaylarını göstermek için NoteAdapter.kt Oluştur: Notların listeleneceği bir adapter oluştur.
            *
        * 10. Kullanıcı Arayüzü TasarımıXML Layout’ları Oluştur: Fragment ve activity'ler için gerekli layout dosyalarını tasarla.

        * 11. CRUD İşlemleri UygulaNot Ekleme, Silme, Düzeltme ve Arama Fonksiyonlarını Uygula.
         */
    }
}