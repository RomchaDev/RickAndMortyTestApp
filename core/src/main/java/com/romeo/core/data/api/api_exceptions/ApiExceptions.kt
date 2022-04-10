package com.romeo.core.data.api.api_exceptions

import java.io.IOException

class InvalidPasswordException(message: String) : IOException(message)
class UserNotExistsException(message: String) : IOException(message)
class UserAlreadyExistsException(message: String) : IOException(message)
class PasswordTooShortException(message: String) : IOException(message)
class PasswordTooCommonException(message: String) : IOException(message)
class PasswordNumericOnlyException(message: String) : IOException(message)