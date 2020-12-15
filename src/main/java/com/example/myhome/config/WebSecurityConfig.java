package com.example.myhome.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration //내부에서 bean객체 사용 가능
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource; //application.properties에 설정된 datasource를 사용할 수 있도록 인스턴스 생성

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//테스트 용도로 disable
                .authorizeRequests()
                    .antMatchers("/", "/account/register", "/css/**", "/api/**").permitAll() //모든 사용자 접근 허용할 경로
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/account/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) //사용자 인증 처리
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder()) //비밀번호 암호화 진행
                .usersByUsernameQuery("select username,password,enabled "
                        + "from user "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select u.username, r.name "
                        + "from user_role ur inner join user u on ur.user_id = u.id "
                        + "inner join role r on ur.role_id = r.id  "
                        + "where u.username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //암호화
    }
}
