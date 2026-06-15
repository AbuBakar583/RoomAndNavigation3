@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    js {
        browser()
        useEsModules()
    }

    wasmJs {
        browser()
        useEsModules()
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.sqlite.web)
            implementation(libs.wrappers.browser)
            implementation(
                npm("sqlite-wasm-worker", layout.projectDirectory.dir("worker").asFile)
            )
        }
    }
}
