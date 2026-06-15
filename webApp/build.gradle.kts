import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                configDirectory = layout.projectDirectory.dir("webpack.config.d").asFile
            }
        }
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                configDirectory = layout.projectDirectory.dir("webpack.config.d").asFile
            }
        }
        binaries.executable()
    }

    sourceSets {
        webMain.dependencies {
            implementation(projects.shared)
            implementation(projects.sqliteWasmWorker)
            implementation(libs.compose.ui)
            implementation(libs.compose.runtime)
        }
    }
}

val syncSqliteWorkerForWasm by tasks.registering(Sync::class) {
    dependsOn(":kotlinNpmInstall")

    from(rootProject.layout.buildDirectory.dir("js/node_modules/sqlite-wasm-worker"))
    into(rootProject.layout.buildDirectory.dir("wasm/node_modules/sqlite-wasm-worker"))
}

val syncSqliteRuntimeForWasm by tasks.registering(Sync::class) {
    dependsOn(":kotlinNpmInstall")

    from(rootProject.layout.buildDirectory.dir("js/node_modules/@sqlite.org/sqlite-wasm"))
    into(rootProject.layout.buildDirectory.dir("wasm/node_modules/@sqlite.org/sqlite-wasm"))
}

tasks.matching {
    it.name in setOf(
        "wasmJsBrowserDevelopmentWebpack",
        "wasmJsBrowserProductionWebpack",
    )
}.configureEach {
    dependsOn(syncSqliteWorkerForWasm, syncSqliteRuntimeForWasm)
}
