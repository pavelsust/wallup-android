/*
ISC License

Copyright 2018, Saksham (DawnImpulse)

Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
provided that the above copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE
OR PERFORMANCE OF THIS SOFTWARE.*/
package com.dawnimpulse.wallup.handlers

import android.app.DownloadManager
import android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import androidx.core.net.toUri
import com.dawnimpulse.wallup.utils.Arrays
import com.dawnimpulse.wallup.utils.toFileUri
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.Progress
import kotlinx.coroutines.experimental.launch


/**
 * @author Saksham
 *
 * @note Last Branch Update - master
 * @note Created on 2018-07-22 by Saksham
 *
 * @note Updates :
 *  2018 08 03 - recent - Saksham - using android default download manager
 *  2018 12 16 - master - Saksham - using dynamic path
 */
object DownloadHandler {

    // download image using system download manager
    fun downloadData(context: Context, url: String, id: String, path: String, isWallpaper: Boolean = false) {
        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(url.toUri())
        val uri = ("$path/$id.jpg").toFileUri()

        launch {
            request
                    .setTitle("$id.jpg")
                    .setDescription("Downloading image from WallUp.")
                    .setDestinationUri(uri)
                    .setVisibleInDownloadsUi(true)
                    .setNotificationVisibility(VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .allowScanningByMediaScanner()

            val did = downloadManager.enqueue(request)
            Arrays.downloadIds.add(did)
            Arrays.downloadWalls.add(isWallpaper)
            Arrays.downloadUris.add(uri)
        }
    }

    //external download
    fun externalDownload(url: String, path: String, name: String, progress: (Progress) -> Unit, callback: (Boolean) -> Unit) {
        PRDownloader.download(url, path, name)
                .build()
                .setOnProgressListener {
                    progress(it)
                }
                .start(object : OnDownloadListener {
                    override fun onDownloadComplete() {
                        callback(true)
                    }

                    override fun onError(error: Error?) {
                        callback(false)
                    }

                })
    }
}