package com.nohadon.NohadonFiles.Exceptions

class NullReturnException (type : String, currentPath : String) : Exception("The current $type is null on the current path: $currentPath ") {
}