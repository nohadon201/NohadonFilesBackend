package com.nohadon.NohadonFiles.web

import com.nohadon.NohadonFiles.web.controllers.ProjectsController
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class WebConstants {
    companion object {
        const val CORS_HEADER : String = "Access-Control-Allow-Origin"
        const val CORS_HEADER_VALUE : String = "*"
        const val ERR_MSG_HEADER : String = "Error-Message"
    }
}