package com.example.englishlearningapp.data.dagger

import com.example.englishlearningapp.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun computer(): Computer
}

@Module
class AppModule {
    @Provides
    fun providesComputer(
        processor: Processor,
        ram: RAM,
        motherboard: Motherboard,
    ):Computer {
        return Computer(
            processor = processor,
            ram = ram,
            motherboard = motherboard,
        )
    }
}