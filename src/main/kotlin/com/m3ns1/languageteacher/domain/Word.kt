package com.m3ns1.languageteacher.domain

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.sql.Timestamp
import java.time.Instant
import java.util.*
import javax.persistence.*


@Entity
@SQLDelete(sql = "UPDATE word SET deleted = true WHERE id=? and version=?")
@Where(clause = "deleted=false")
@NamedQuery(name = "Word.findAll", query = "select w from Word w order by w.version desc")
data class Word @JvmOverloads constructor(
    @Id
    val id: String = UUID.randomUUID().toString(),
) {
    @Column(nullable = false)
    var english: String? = null

    @Column(nullable = false)
    var german: String? = null

    var context: String? = null

    var deleted: Boolean = false

    @Version
    @Column(nullable = false)
    var version: Timestamp? = Timestamp.from(Instant.now())

}
