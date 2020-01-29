package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dao.Template;
import com.konrad.garagev3.service.MailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail-template")
public class MailTemplateControllerRest {
    @Autowired
    private MailTemplateService mailTemplateService;

    @PostMapping
    public Template saveMailTemplate(@RequestBody Template template) {
       return mailTemplateService.save(template);
    }

    @PutMapping
    public Template updateMailTemplate(@RequestBody Template template) {
        return mailTemplateService.update(template);
    }
    @GetMapping("/{page}/[size}")
    public Page<Template> getList(@PathVariable Integer page, @PathVariable Integer size) {
        return mailTemplateService.findAll(PageRequest.of(page, size));
    }

    @DeleteMapping("/{id}")
    public void deleteMailTemplate(@PathVariable Long id) {
        mailTemplateService.delete(id);
    }
}
