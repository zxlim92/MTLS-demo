package com.app.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final Set<String> uniqueClients = new HashSet<>();
    private final AtomicInteger clientCount = new AtomicInteger();
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(channel -> channel
                .requestMatchers("/mtlsData").authenticated()
                .anyRequest().permitAll()
        )
                .x509(x509 -> x509
                        .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
                        .userDetailsService(userDetailsService())
                );
        return http.build();


    }
    private UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                X509Certificate[] certs = (X509Certificate[])
                        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                                .getRequest().getAttribute("jakarta.servlet.request.X509Certificate");
                if (certs != null && certs.length > 0) {
                    String uniqueClientId = certs[0].getSerialNumber().toString(); // Use serial number or another unique attribute

                    if (uniqueClients.add(uniqueClientId)) {
                        int currentCount = clientCount.incrementAndGet();
                        System.out.println("Count of unique clients: " + currentCount);
                    }
                } else {
                    throw new UsernameNotFoundException("Client certificate not found");
                }
                return new User(username, "", AuthorityUtils.commaSeparatedStringToAuthorityList("CLIENT"));
            }
        };
    }
    public int getUniqueClientCount() {
        return clientCount.get();
    }
}
