package com.konrad.garagev3.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konrad.garagev3.model.dao.MailTemplate;
import com.konrad.garagev3.model.dao.Vehicle;
import com.konrad.garagev3.repository.MailTemplateRepository;
import com.konrad.garagev3.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
@Slf4j
public class VehicleMailer {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private MailTemplateRepository mailTemplateRepository;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private ObjectMapper objectMapper;

    // @Scheduled(cron = "0 0 9 1/1 * ? *")
    @Scheduled(fixedRate = 1000)
    public void sendMail() {
        MailTemplate vehicle_overview_remaider = mailTemplateRepository.findByType("vehicle_overview_Remaider");
        List<Vehicle> vehicles = vehicleRepository.findByOverviewDate(LocalDate.now());
        vehicles.forEach(vehicle -> {
            String email = vehicle.getClient().getEmail();
            Context context = new Context(Locale.forLanguageTag("pl"), objectMapper.convertValue(vehicle, Map.class));
            String mailMessage = templateEngine.process(vehicle_overview_remaider.getTemplate(), context);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            try {
                mimeMessageHelper.setTo(email);
                mimeMessageHelper.setText(mailMessage);
                mimeMessageHelper.setSubject("do zmiany");
                javaMailSender.send(mimeMessage);
            } catch (MessagingException e) {
                log.error(e.getMessage(), e);
            }
        });
    }

}
