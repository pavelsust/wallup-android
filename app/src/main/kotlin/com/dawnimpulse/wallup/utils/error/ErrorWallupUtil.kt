/*
ISC License

Copyright 2018-2019, Saksham (DawnImpulse)

Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
provided that the above copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE
OR PERFORMANCE OF THIS SOFTWARE.*/
package com.dawnimpulse.wallup.utils.error

import com.dawnimpulse.wallup.network.RetroApiClient
import retrofit2.Response

/**
 * @author Saksham
 *
 * @note Last Branch Update - master
 * @note Created on 2019-06-11 by Saksham
 *
 * @note Updates :
 */
object ErrorWallupUtil {

    // parse unsplash errors
    fun parseError(response: Response<*>): ErrorWallupObject {
        val converter = RetroApiClient.getWallupRetro().responseBodyConverter<ErrorWallupObject>(ErrorWallupObject::class.java, arrayOfNulls<Annotation>(0))
        val error: ErrorWallupObject

        try {
            error = converter.convert(response.errorBody()!!)!!
        } catch (e: Exception) {
            return ErrorWallupObject()
        }

        return error
    }
}