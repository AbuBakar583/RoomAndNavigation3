import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    id("androidx.room3")

}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    jvm()

    js {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    android {
        namespace = "org.roomnav.project.shared"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
//            implementation(libs.androidx.room.runtime)
//            implementation(libs.androidx.sqlite.bundled)
            implementation(libs.sqlite.bundled)

        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.jetbrains.material3.adaptiveNavigation3)
            implementation(libs.jetbrains.lifecycle.viewmodelNavigation3)
//            implementation(libs.room3.runtime)
            api(libs.room3.runtime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
//            implementation(libs.androidx.room.runtime)
//            implementation(libs.androidx.sqlite.bundled)
            implementation(libs.sqlite.bundled)

        }
        jsMain.dependencies {
            implementation(libs.wrappers.browser)
        }
        webMain.dependencies {
            implementation(libs.sqlite.web)
            implementation(project(":sqliteWasmWorker"))
        }
        jvmMain.dependencies {
//            implementation(libs.androidx.room.runtime)
//            implementation(libs.androidx.sqlite.bundled)
            implementation(libs.sqlite.bundled)

        }
    }
}
room3 {
    schemaDirectory(layout.projectDirectory.dir("schemas"))
}
dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
    add("kspAndroid", libs.room3.compiler)
    add("kspJvm", libs.room3.compiler)
    add("kspIosSimulatorArm64", libs.room3.compiler)
    add("kspIosArm64", libs.room3.compiler)
    add("kspJs", libs.room3.compiler)
    add("kspWasmJs", libs.room3.compiler)
}
