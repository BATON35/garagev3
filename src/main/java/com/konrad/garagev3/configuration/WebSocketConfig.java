package com.konrad.garagev3.configuration;

import com.konrad.garagev3.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Optional;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/queue", "/user");
        registry.setUserDestinationPrefix("/user");
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
                        UsernamePasswordAuthenticationToken user = usernamePasswordAuthenticationToken.get();
                        headerAccessor.setUser(user);
                        headerAccessor.setLeaveMutable(true);
                        //   webSocketUserProperty.putSesionId(user.getName(), headerAccessor.getSessionId());
                        return MessageBuilder.createMessage(message.getPayload(), headerAccessor.getMessageHeaders());
                    }
                }
                return MessageBuilder.createMessage("error", headerAccessor.getMessageHeaders());
            }
        });
    }
}
