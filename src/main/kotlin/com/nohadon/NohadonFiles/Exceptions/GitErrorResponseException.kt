package com.nohadon.NohadonFiles.Exceptions

class GitErrorResponseException (type : String, currentPath : String, statusError :String) : Exception("Error trying to get a $type, the API of Git returned an Error with the url: $currentPath \n \t Status Error Returned: $statusError") {
}