package org.roomnav.project

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.web.WebWorkerSQLiteDriver
import org.w3c.dom.Worker
import kotlin.js.js

actual fun createWebWorkerDriver(): SQLiteDriver =
    WebWorkerSQLiteDriver(createWorker())

private fun createWorker(): Worker =
    Worker(js("""new URL("sqlite-wasm-worker/worker.js", import.meta.url)"""))
