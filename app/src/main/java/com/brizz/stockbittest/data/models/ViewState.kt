package com.brizz.stockbittest.data.models

class ViewState(val status: Status, val msg: String) {

    companion object {
        var LOADED: ViewState? = null
        var LOADING: ViewState? = null

        init {
            LOADED = ViewState(Status.SUCCESS, "Success")
            LOADING = ViewState(Status.RUNNING, "Running")
        }
    }

    enum class Status {
        RUNNING, SUCCESS, FAILED
    }

}