package com.example.eyeofsauron.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Исключение, если данные не найдены
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class EntityNotFoundException: RuntimeException()
