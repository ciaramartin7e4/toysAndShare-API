package cat.itb.m13.toysandsahre;

import cat.itb.m13.toysandsahre.model.serveis.ElMeuUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Component
public class ConfiguracioSeguretat extends WebSecurityConfigurerAdapter {
    private final ElMeuUserDetailsService elmeuUserDetailsService;
    private final PasswordEncoder xifrat;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(elmeuUserDetailsService).passwordEncoder(xifrat);
    }
}
