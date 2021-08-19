package com.zannardyapps.listacontatos.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class
DataContact(
    var contactName: String,
    var contactNumber: String,
    var contactPhoto: String,
): Parcelable
