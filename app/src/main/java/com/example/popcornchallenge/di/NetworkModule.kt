package com.example.popcornchallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideKtorNetworkService(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        useArrayPolymorphism = true
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                    }
                )
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }

            install(DefaultRequest) {
                header(HttpHeaders.Authorization, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3M2MwNTA1MTQ1NWI5MTNmM2Q5ZmI5MDdhNzFhMDZkMyIsIm5iZiI6MTc0MzM4ODU1My4zMTAwMDAyLCJzdWIiOiI2N2U5ZmY4OTJjY2E2ZmM4ZmJjNmQ1NmYiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.QCiehtb8h_JCy03C0Qx4KfIeEbFTyqa6GPu8Qj4SIAk")
            }

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }
    }

    companion object {
        private const val TIME_OUT = 6000
    }
}