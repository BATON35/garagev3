package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.MailTemplate;
import com.konrad.garagev3.repository.MailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class MailTemplateService {
    @Autowired
    private MailTemplateRepository mailTemplateRepository;

    public MailTemplate save(MailTemplate mailTemplate) {
        return mailTemplateRepository.save(mailTemplate);
    }

    public MailTemplate update(MailTemplate mailTemplate) {
        return mailTemplateRepository.findById(mailTemplate.getId()).map(mailTemplateDB -> {
            if (!mailTemplate.getType().equals(mailTemplateDB.getType())) {
                mailTemplateDB.setType(mailTemplate.getType());
            }
            if (!mailTemplate.getTemplate().equals(mailTemplateDB.getTemplate())) {
                mailTemplateDB.setTemplate(mailTemplate.getTemplate());
            }
            return mailTemplateRepository.save(mailTemplateDB);
        }).orElseThrow(() -> new EntityNotFoundException("MailTemplate with id " + mailTemplate.getId() + " doesn't exist"));
    }

    public Page<MailTemplate> findAll(PageRequest page) {
        return mailTemplateRepository.findAll(page);
    }

    public void delete(Long id) {
        mailTemplateRepository.deleteById(id);
    }
}
