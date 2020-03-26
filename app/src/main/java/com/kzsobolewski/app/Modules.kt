package com.kzsobolewski.app

import com.kzsobolewski.data.di.repositoryModule
import com.kzsobolewski.mygarden.di.presentationModule

val appModule = presentationModule + repositoryModule


