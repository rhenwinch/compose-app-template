package com.example.template.data.home

import com.example.template.domain.home.HomeItemsRepository
import javax.inject.Inject

// All implementations are invisible to all modules excluding the DI
internal class HomeItemsRepositoryImpl @Inject constructor() : HomeItemsRepository