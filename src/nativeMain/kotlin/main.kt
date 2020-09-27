import kotlinx.cinterop.cValue
import raylib.*

@ExperimentalUnsignedTypes
fun main() {
    val screenWidth = 800
    val screenHeight = 450
    InitWindow(screenWidth, screenHeight, "Hello from Kotlin/Native!")
    SetTargetFPS(60)
    while (!WindowShouldClose()) {
        BeginDrawing()
        ClearBackground(cValue {
            r = 5u
            g = 5u
            b = 5u
        })
        DrawText("Hello!!!!", 0, 0, 12, cValue {
            r = 255u
            g = 255u
            b = 255u
            a = 255u
        })
        EndDrawing()
    }

    CloseWindow()
}