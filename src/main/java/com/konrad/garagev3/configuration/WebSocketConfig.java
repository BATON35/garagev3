package com.konrad.garagev3.configuration;

import com.konrad.garagev3.util.SecurityUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Optional;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("app").enableSimpleBroker("queue", "test");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
                String token = headerAccessor.getFirstNativeHeader("Authorization");
                StompCommand command = headerAccessor.getCommand();
                if (token != null && (command.equals(StompCommand.CONNECT) || command.equals(StompCommand.MESSAGE) || command.equals(StompCommand.SEND) || command.equals(StompCommand.SUBSCRIBE))) {
                    Optional<UsernamePasswordAuthenticationToken> usernamePasswordAuthenticationToken = SecurityUtil.parsToken(token);
                    if (usernamePasswordAuthenticationToken.isPresent()) {
                        headerAccessor.setUser(usernamePasswordAuthenticationToken.get());
                        headerAccessor.setLeaveMutable(true);
                        return MessageBuilder.createMessage(message.getPayload(), headerAccessor.getMessageHeaders());
                    }
                }
                return MessageBuilder.createMessage("error", headerAccessor.getMessageHeaders());
            }
        });
    }
}
