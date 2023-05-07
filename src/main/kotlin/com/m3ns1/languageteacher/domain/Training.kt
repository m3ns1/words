package com.m3ns1.languageteacher.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("session")
data class Training(@Autowired(required = false) var words: MutableList<Word> = mutableListOf()) {
    var position: Int = 0
    fun next() = words[position++]
    fun get() = words[position]
    fun hasMore() = position < words.size
}