package com.liuss.model.security;

import com.liuss.model.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthorizedService authorizedService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/authorized/**").authenticated()
                .and().headers().frameOptions().sameOrigin()
                .and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").failureUrl("/login?error").permitAll().successHandler(authorizedService.getAuthenticationSuccessHandler("/authorized/main"))
                .and().logout().addLogoutHandler(authorizedService.getLogoutHandler()).logoutSuccessUrl("/login").permitAll();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authorizedService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return EncryptUtil.Md5((String)charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(EncryptUtil.Md5((String)charSequence));
            }
        });
    }
}
