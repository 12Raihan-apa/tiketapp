package com.example.tiketapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicketContent(
    modifier: Modifier = Modifier,

    hargaTiket: Int,
    jumlahTiket: Int,
    namaPembeli: String,

    onNamaChange: (String) -> Unit,
    onTambahTiket: () -> Unit,
    onKurangTiket: () -> Unit,
    onPesanTiket: () -> Unit,

    status: String,
    sedangMemesan: Boolean
) {

    val totalHarga = hargaTiket * jumlahTiket

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Pesan Tiket",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Nama Pembeli
        Text(
            text = "Nama Pembeli",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = namaPembeli,
            onValueChange = onNamaChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Masukkan nama Anda")
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Harga Tiket
        Text(
            text = "Harga Tiket",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(6.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Rp ${String.format("%,d", hargaTiket).replace(',', '.')}",
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Jumlah Tiket
        Text(
            text = "Jumlah Tiket",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = onKurangTiket,
                enabled = !sedangMemesan
            ) {
                Text("-")
            }

            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = jumlahTiket.toString(),
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = onTambahTiket,
                enabled = !sedangMemesan
            ) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Total Harga
        Text(
            text = "Total Harga",
            style = MaterialTheme.typography.labelLarge
        )

        Text(
            text = "Rp ${String.format("%,d", totalHarga).replace(',', '.')}",
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Pesan
        Button(
            onClick = onPesanTiket,
            modifier = Modifier.fillMaxWidth(),
            enabled = !sedangMemesan
        ) {
            Text(
                text = if (sedangMemesan) {
                    "Memproses..."
                } else {
                    "Pesan Tiket"
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Status
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Status: $status",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}