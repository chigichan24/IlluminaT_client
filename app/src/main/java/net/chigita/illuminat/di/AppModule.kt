package net.chigita.illuminat.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import net.chigita.illuminat.api.IlluminatService
import net.chigita.illuminat.db.ColorDao
import net.chigita.illuminat.db.IlluminatDb
import net.chigita.illuminat.db.PatternDao
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by chigichan24 on 2019-06-12.
 */
@Module(includes = [ViewModelModule::class])
class AppModule {
  @Singleton
  @Provides
  fun provideDb(app: Application): IlluminatDb {
    return Room
        .databaseBuilder(app, IlluminatDb::class.java, "illuminat.db")
        .fallbackToDestructiveMigration()
        .build()
  }

  @Singleton
  @Provides
  fun provideColorDao(db: IlluminatDb): ColorDao {
    return db.colorDao()
  }

  @Singleton
  @Provides
  fun providePatternDao(db: IlluminatDb): PatternDao {
    return db.patternDao()
  }

  @Singleton
  @Provides
  fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
  }

  @Singleton
  @Provides
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
  }

  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
  }

  @Singleton
  @Provides
  fun provideIlluminatService(retrofit: Retrofit): IlluminatService {
    return retrofit.create(IlluminatService::class.java)
  }

  companion object {
    private const val BASE_URL = "http://192.168.11.136:5000/"
  }
}