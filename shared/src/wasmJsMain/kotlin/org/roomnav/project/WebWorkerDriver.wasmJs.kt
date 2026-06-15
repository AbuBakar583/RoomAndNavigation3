package org.roomnav.project

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.web.WebWorkerSQLiteDriver
import org.w3c.dom.Worker
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.js

actual fun createWebWorkerDriver(): SQLiteDriver =
    WebWorkerSQLiteDriver(createWorker())

@OptIn(ExperimentalWasmJsInterop::class)
private fun createWorker(): Worker =
    js("""new Worker(new URL("sqlite-wasm-worker/worker.js", import.meta.url))""")
