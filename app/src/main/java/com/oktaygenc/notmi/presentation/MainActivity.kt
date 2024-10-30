package com.oktaygenc.notmi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.oktaygenc.notmi.R
import com.oktaygenc.notmi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Notlarım"


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

    }/*
        * 9. UI GeliştirmeFragment ve Activity’leri Oluştur:AddNoteFragment.kt (not eklemek için)NoteListFragment.kt (not listesini göstermek için)
            NoteDetailFragment.kt (not detaylarını göstermek için NoteAdapter.kt Oluştur: Notların listeleneceği bir adapter oluştur.
            *
        * 10. Kullanıcı Arayüzü TasarımıXML Layout’ları Oluştur: Fragment ve activity'ler için gerekli layout dosyalarını tasarla.

        * 11. CRUD İşlemleri UygulaNot Ekleme, Silme, Düzeltme ve Arama Fonksiyonlarını Uygula.
         */
}
