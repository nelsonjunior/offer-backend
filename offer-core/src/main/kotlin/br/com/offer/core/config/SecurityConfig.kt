package br.com.offer.core.config

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class SecurityConfig {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.cors()
            .and().csrf()
            .disable()
            .authorizeRequests { expressionInterceptUrlRegistry ->
                expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.GET, "/offers/{id}").permitAll()
                    .antMatchers(HttpMethod.GET, "/offers/slug/**").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer().jwt()
        return http.build()
    }
}