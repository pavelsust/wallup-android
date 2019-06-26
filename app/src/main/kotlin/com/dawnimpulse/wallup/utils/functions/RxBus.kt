/**
 * ISC License
 *
 * Copyright 2018-2019, Saksham (DawnImpulse)
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
 * provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
 * WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE
 * OR PERFORMANCE OF THIS SOFTWARE.
 **/
package com.dawnimpulse.wallup.utils.functions

import com.jakewharton.rxrelay2.BehaviorRelay

/**
 * @info -
 *
 * @author - Saksham
 * @note Last Branch Update - master
 *
 * @note Created on 2019-06-10 by Saksham
 * @note Updates :
 */

val RxBus by lazy { BehaviorRelay.createDefault<String>("") }
val RxObjectBus by lazy { BehaviorRelay.createDefault<RxBusObject>(RxBusObject()) }
val RxErrorBus by lazy { BehaviorRelay.createDefault<RxErrorBusObject>(RxErrorBusObject()) }
val RxBusTime by lazy { BehaviorRelay.createDefault(0) }

// normal bus
data class RxBusObject(
        var type: String = "",
        var data: Any? = null
)

// error bus
data class RxErrorBusObject(
        var type: String = "",
        var error: Any? = null
)