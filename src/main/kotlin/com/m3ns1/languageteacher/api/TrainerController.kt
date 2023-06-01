package com.m3ns1.languageteacher.api

import com.m3ns1.languageteacher.domain.Training
import com.m3ns1.languageteacher.service.WordService
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@Scope("request")
class TrainerController(
    private val wordService: WordService,
    private val training: Training
) {

    @GetMapping("/start")
    fun start(
        model: Model,
        @RequestParam(value = "mode", required = false, defaultValue = "ende") mode: String
    ): String {
        training.words.clear()
        training.position = 0
        training.mode = mode
        training.words.addAll(wordService.list(wordService.lastMonthFilter()).shuffled())
        return train(model)
    }

    @GetMapping("/next")
    fun train(model: Model): String {
        return if (training.hasMore()) {
            model.addAttribute("word", training.getWord())
            model.addAttribute("context", training.get().context)
            model.addAttribute("reveal", false)
            "train"
        } else {
            return "redirect:/"
        }
    }

    @GetMapping("/reveal")
    fun reveal(model: Model): String {
        model.addAttribute("word", training.getWord())
        model.addAttribute("context", training.get().context)
        model.addAttribute("revealed", training.getRevealed())
        model.addAttribute("reveal", true)
        training.moveNext()
        return "train"
    }

}