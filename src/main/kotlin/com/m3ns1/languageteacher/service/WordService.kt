package com.m3ns1.languageteacher.service

import com.m3ns1.languageteacher.domain.Word
import com.m3ns1.languageteacher.repo.WordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime
import java.util.function.Predicate

@Service
class WordService(
    private val wordRepository: WordRepository,
    @Autowired(required = false) private val clock: Clock = Clock.systemDefaultZone()
) {
    fun list(filter: Predicate<Word>? = null): Iterable<Word> {
        return if (filter != null) {
            wordRepository.findAll().filter { filter.test(it) }
        } else {
            wordRepository.findAll()
        }
    }

    fun sameMonthFilter(): Predicate<Word> {
        return Predicate<Word> {
            val now = LocalDateTime.now(clock)
            it.version!!.toLocalDateTime().let { version ->
                version.month == now.month && version.year == now.year
            }
        }
    }

    fun getById(id: String) = wordRepository.findById(id).orElseThrow { IllegalArgumentException("Word $id not found") }

    fun delete(id: String) = wordRepository.deleteById(id)
    fun save(word: Word): Word {
        return wordRepository.save(word)
    }
}