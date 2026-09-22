package com.example.tiketapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen() {

    // State dikelola oleh Parent
    var hargaTiket by remember {
        mutableStateOf(50000)
    }

    var jumlahTiket by remember {
        mutableStateOf(1)
    }

    var namaPembeli by remember {
        mutableStateOf("")
    }

    var status by remember {
        mutableStateOf("Silakan pesan tiket")
    }

    var sedangMemesan by remember {
        mutableStateOf(false)
    }

    // LaunchedEffect
    LaunchedEffect(sedangMemesan) {

        if (sedangMemesan) {

            status = "Memproses pesanan..."

            delay(5000)

            status = "Tiket telah dipesan"

            sedangMemesan = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pemesanan Tiket")
                }
            )
        }
    ) { paddingValues ->

        TicketContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),

            hargaTiket = hargaTiket,
            jumlahTiket = jumlahTiket,
            namaPembeli = namaPembeli,

            onNamaChange = {
                namaPembeli = it
            },

            onTambahTiket = {
                jumlahTiket++
            },

            onKurangTiket = {
                if (jumlahTiket > 1) {
                    jumlahTiket--
                }
            },

            onPesanTiket = {

                if (namaPembeli.isBlank()) {
                    status = "Nama harus diisi"
                } else {
                    sedangMemesan = true
                }
            },

            status = status,
            sedangMemesan = sedangMemesan
        )
    }
}