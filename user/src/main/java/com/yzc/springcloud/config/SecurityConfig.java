package com.yzc.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
        String pwd = bc.encode("123");   //傻逼版本 1
        System.out.println(pwd);
        auth.inMemoryAuthentication().withUser("yzc").password(pwd).roles("admin");

        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.exceptionHandling().accessDeniedPage("/out.html");
        http.formLogin() //自定义登录页面
                .loginPage("/login.html") //设置登录页面
                .loginProcessingUrl("/user/login")//登录访问路径
                .defaultSuccessUrl("/send.html").permitAll()  //登录成功之后跳转路径
                .and().authorizeRequests()  //设置那些需要认证或者不需要认证
                .antMatchers("/", "/user/test", "/user/send").permitAll()//不需要认证
                //.antMatchers("/user/info").hasAuthority("admin") //必须是admin才可以访问
                //.antMatchers("/user/info").hasAnyAuthority("admin,teacher") //或者是admin才可以访问
                //.antMatchers("/user/info").hasRole("admin") //传入地方必须带ROLE_前缀
                .anyRequest().authenticated() //所有都可以被访问
                .and().csrf().disable();//关闭csrf防护


    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }
}
