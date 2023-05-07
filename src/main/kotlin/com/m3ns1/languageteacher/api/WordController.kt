package com.m3ns1.languageteacher.api

import com.m3ns1.languageteacher.domain.Word
import com.m3ns1.languageteacher.service.WordService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class WordController(private val wordService: WordService) {

    @GetMapping("/")
    fun listWords(model: Model): String {
        val words = wordService.list()
        model.addAttribute("words", words)
        return "list"
    }

    @GetMapping("/add")
    fun addWordForm(model: Model): String {
        model.addAttribute("word", Word())
        return "form"
    }

    @GetMapping("/edit")
    fun addWordForm(model: Model, @RequestParam id: String): String {
        model.addAttribute("word", wordService.getById(id))
        return "form"
    }

    @GetMapping("/delete")
    fun removeWorkd(model: Model, @RequestParam id: String): String {
        wordService.delete(id)
        return "redirect:/"
    }

    @PostMapping("/save")
    fun saveWordSubmit(model: Model, @ModelAttribute word: Word): String {
        wordService.save(word)
        model.addAttribute("word", Word())
        return "form"
    }

}