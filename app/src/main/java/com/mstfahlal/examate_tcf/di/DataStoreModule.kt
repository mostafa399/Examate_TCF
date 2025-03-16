package com.mstfahlal.examate_tcf.di

import android.content.Context
import com.mstfahlal.examate_tcf.data.PreferenceDataSourceImpl
import com.mstfahlal.examate_tcf.data.PreferenceRepositoryImpl
import com.mstfahlal.examate_tcf.domain.repository.PreferenceDataSource
import com.mstfahlal.examate_tcf.domain.repository.PreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context


    @Provides
    @Singleton
    fun providePreferencesDataSource(@ApplicationContext context: Context): PreferenceDataSource =
        PreferenceDataSourceImpl(context)

    @Provides
    @Singleton
    fun providePreferencesRepository(preferencesDataSource: PreferenceDataSource): PreferenceRepository =
        PreferenceRepositoryImpl(preferencesDataSource)



}