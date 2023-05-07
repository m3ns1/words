package com.m3ns1.languageteacher.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver


@Configuration
class ThymeleafConfiguration {
    @Bean
    fun secondaryTemplateResolver(): ClassLoaderTemplateResolver? {
        val secondaryTemplateResolver = ClassLoaderTemplateResolver()
        secondaryTemplateResolver.prefix = "classpath:/templates/"
        secondaryTemplateResolver.suffix = ".html"
        secondaryTemplateResolver.templateMode = TemplateMode.HTML
        secondaryTemplateResolver.characterEncoding = "UTF-8"
        secondaryTemplateResolver.order = 1
        secondaryTemplateResolver.checkExistence = true
        return secondaryTemplateResolver
    }
}