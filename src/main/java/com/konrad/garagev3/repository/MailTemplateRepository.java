package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailTemplateRepository extends JpaRepository<MailTemplate, Long> {
    MailTemplate findByType(String type);
}
