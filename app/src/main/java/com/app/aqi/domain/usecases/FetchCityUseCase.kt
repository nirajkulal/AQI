package com.app.aqi.domain.usecases

import com.app.aqi.domain.repository.AQIDataRepository
import javax.inject.Inject

class FetchCityUseCase  @Inject constructor(
    private val aAQIDataRepository: AQIDataRepository
) {

    fun invoke(city: String) =
        aAQIDataRepository.fetchCiy(city)


}