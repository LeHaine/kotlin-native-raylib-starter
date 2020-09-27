plugins {
    kotlin("multiplatform") version "1.4.10"
}
group = "raylib.project"
version = "1.0-SNAPSHOT"
val raylibPath = project.file("src/raylibInterop/cinterop")

repositories {
    mavenCentral()
}
kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }
    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }

        compilations["main"].cinterops {
            val raylibCinterop by creating {
                defFile(raylibPath.resolve("raylib.def"))
                packageName("raylib")
                includeDirs(raylibPath)
            }
        }
    }
    sourceSets {
        val nativeMain by getting
        val nativeTest by getting
    }
}