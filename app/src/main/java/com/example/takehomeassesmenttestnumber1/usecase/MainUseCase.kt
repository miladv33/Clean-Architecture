package com.example.takehomeassesmenttestnumber1.usecase

import com.example.takehomeassesmenttestnumber1.repository.RandomQuoteRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class MainUseCase @Inject constructor(
    private var randomQuoteRepository: RandomQuoteRepository
) {

    /**
     *  todo change this methode to return safeCall instead of flow
     * @return Flow<RandomQuote>
     */
    fun executeQuotesFlow() = flow {
        emit(randomQuoteRepository.getRandomFlow())
    }

}
