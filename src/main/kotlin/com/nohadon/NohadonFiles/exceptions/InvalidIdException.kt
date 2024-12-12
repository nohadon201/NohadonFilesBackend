package com.nohadon.NohadonFiles.exceptions

class InvalidIdException (id : Long) : Exception("The ID provided is not valid. There is no ID in the DB that has the value: $id")