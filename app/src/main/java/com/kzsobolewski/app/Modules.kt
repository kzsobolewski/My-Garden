package com.kzsobolewski.app

import com.kzsobolewski.data.di.dataModule
import com.kzsobolewski.mygarden.di.presentationModule

val appModule = presentationModule + dataModule


