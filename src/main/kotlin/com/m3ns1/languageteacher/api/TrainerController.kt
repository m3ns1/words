package com.m3ns1.languageteacher.api

import com.m3ns1.languageteacher.domain.Training
import com.m3ns1.languageteacher.service.WordService
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
@Scope("request")
class TrainerController(
    private val wordService: WordService,
    private val training: Training
) {

    @GetMapping("/start")
    fun start(model: Model): String {
        training.words.clear()
        training.position = 0
        training.words.addAll(wordService.list(wordService.sameMonthFilter()).shuffled())
        return train(model)
    }

    @GetMapping("/next")
    fun train(model: Model): String {
        return if (training.hasMore()) {
            model.addAttribute("word", training.next())
            model.addAttribute("reveal", false)
            "train"
        } else {
            return "redirect:/"
        }
    }

    @GetMapping("/reveal")
    fun reveal(model: Model): String {
        val actual = training.words[training.position - 1]
        model.addAttribute("word", actual)
        model.addAttribute("reveal", true)
        return "train"
    }

}