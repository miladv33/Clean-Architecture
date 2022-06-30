package com.example.quotes.di.annotation

import javax.inject.Qualifier

/**
 * Retrofit quotable server
 *
 * @constructor Create empty Retrofit quotable server
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitQuotableServer

/**
 * Retrofit n b a server
 *
 * @constructor Create empty Retrofi t n b a server
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitNBAServer