package com.konrad.garagev3.repository;

import com.konrad.garagev3.model.dao.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    Template findByType(String type);
}
