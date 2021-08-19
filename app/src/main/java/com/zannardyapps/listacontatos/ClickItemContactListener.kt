package com.zannardyapps.listacontatos

import com.zannardyapps.listacontatos.models.DataContact

interface ClickItemContactListener {
    fun itemClickedAction(contact: DataContact)
}