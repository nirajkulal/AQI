package com.app.aqi.domain.usecases

import com.app.aqi.domain.repository.AQIDataRepository
import javax.inject.Inject

class StopAQIUseCase @Inject constructor(
    private val aAQIDataRepository: AQIDataRepository
) {

    fun invoke() =
        aAQIDataRepository.closeWH()


}