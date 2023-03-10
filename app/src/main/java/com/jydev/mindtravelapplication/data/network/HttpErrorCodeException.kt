package com.jydev.mindtravelapplication.data.network

class HttpErrorCodeException(val code : Int, message : String) : RuntimeException(message)