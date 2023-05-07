package com.m3ns1.languageteacher.repo

import com.m3ns1.languageteacher.domain.Word
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WordRepository : CrudRepository<Word, String> {

    @Query
    override fun findAll(): Iterable<Word>
}