package com.nohadon.NohadonFiles.Exceptions

class NullBodyResponseException(type : String, urlPath : String) : Exception("Error trying to get a $type with the path/url: $urlPath. The status of the response is not a redirect and is not an error, but the object response is null.") {
}