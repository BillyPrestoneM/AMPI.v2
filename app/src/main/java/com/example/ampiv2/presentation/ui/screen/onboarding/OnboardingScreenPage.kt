package com.example.ampiv2.presentation.ui.screen.onboarding

import androidx.annotation.DrawableRes
import com.example.ampiv2.R

sealed class OnboardingScreenPage (
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    data object First : OnboardingScreenPage(
        image = R.drawable.dompet,
        title = "Kelola Keuangan Lebih Terkontrol",
        description = "Catat pemasukan dan pengeluaranmu dengan mudah.\u2028 Pahami ke mana uangmu pergi setiap hari."

    )
    data object Second : OnboardingScreenPage(
        image = R.drawable.kalender,
        title = "Waktu Lebih Teratur, Hidup Lebih Tenang",
        description = "Atur jadwal, tugas, dan prioritasmu.\u2028 Fokus pada hal yang benar-benar penting."
    )
    data object Third : OnboardingScreenPage(
        image = R.drawable.riwayat,
        title = "Seimbang antara Finansial & Produktivitas",
        description = "Kelola keuangan dan waktumu dalam satu aplikasi.\u2028 Bangun kebiasaan baik untuk masa depanmu."
    )
}