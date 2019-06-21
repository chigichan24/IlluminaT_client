package net.chigita.illuminat.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.chigita.illuminat.db.ColorDao
import net.chigita.illuminat.db.IlluminatDb
import net.chigita.illuminat.db.PatternDao
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
}