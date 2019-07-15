package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.AnonymousUserQuestion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class MailServiceTest {

    @Mock
    MailService mockMailService;

    // TODO: 11.07.2019 to fix
    @Test
    public void sendEmail() {
        Mockito.when(mockMailService.sendEmail(any(AnonymousUserQuestion.class))).thenReturn("success");

        AnonymousUserQuestion anonymousUserQuestion = new AnonymousUserQuestion("test", "test", "test@mail");

        String result = mockMailService.sendEmail(anonymousUserQuestion);

        Mockito.verify(mockMailService, times(1))
                .sendEmail(anonymousUserQuestion);

        Assert.assertEquals("success", result);
    }
}