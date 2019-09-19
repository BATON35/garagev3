package com.konrad.garagev3.controller;

import com.konrad.garagev3.model.dao.MailTemplate;
import com.konrad.garagev3.service.MailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController("/api/mail-template")
public class MailTemplateControlerRest {
    @Autowired
    private MailTemplateService mailTemplateService;

    @PostMapping
    public MailTemplate save(@RequestBody MailTemplate mailTemplate) {
       return mailTemplateService.save(mailTemplate);
    }

    @PutMapping
    public MailTemplate update(@RequestBody MailTemplate mailTemplate) {
        return mailTemplateService.update(mailTemplate);
    }
    @GetMapping("/{page}/[size}")
    public Page<MailTemplate> getList(@PathVariable Integer page, @PathVariable Integer size) {
//        return userService.findAll(PageRequest.of(page, size)).map(userMapper::toUserDto);
        return mailTemplateService.findAll(PageRequest.of(page, size));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mailTemplateService.delete(id);
    }
}
