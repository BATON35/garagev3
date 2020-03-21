package com.konrad.garagev3.configuration;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {
    @Bean
    public Config config(){
        return new Config()
                .setInstanceName("Hazelcast1")
                .addMapConfig(
                        new MapConfig()
                        .setName("Client")
                        .setEvictionConfig(new EvictionConfig().setSize(10_000).setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE).setEvictionPolicy(EvictionPolicy.LFU))
                );
    }
}
