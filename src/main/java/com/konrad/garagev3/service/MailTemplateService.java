package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Template;
import com.konrad.garagev3.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class MailTemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    public Template save(Template template) {
        return templateRepository.save(template);
    }

    public Template update(Template template) {
        return templateRepository.findById(template.getId()).map(mailTemplateDB -> {
            if (!template.getType().equals(mailTemplateDB.getType())) {
                mailTemplateDB.setType(template.getType());
            }
            if (!template.getBody().equals(mailTemplateDB.getBody())) {
                mailTemplateDB.setBody(template.getBody());
            }
            return templateRepository.save(mailTemplateDB);
        }).orElseThrow(() -> new EntityNotFoundException("Template with id " + template.getId() + " doesn't exist"));
    }

    public Page<Template> findAll(PageRequest page) {
        return templateRepository.findAll(page);
    }

    public void delete(Long id) {
        templateRepository.deleteById(id);
    }
}
