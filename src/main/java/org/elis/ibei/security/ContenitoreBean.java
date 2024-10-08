package org.elis.ibei.security;

import lombok.RequiredArgsConstructor;
import org.elis.ibei.repository.UtenteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class ContenitoreBean {

    private  final UtenteRepository repo;

    @Bean
    protected UserDetailsService getDetailsService() {
        return (u)->repo.findByEmail(u).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Bean
    protected PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager getManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    protected AuthenticationProvider getAuthenticationProvider()  {
        DaoAuthenticationProvider dap=new DaoAuthenticationProvider();
        dap.setUserDetailsService(getDetailsService());
        dap.setPasswordEncoder(getPasswordEncoder());
        return dap;
    }
}
