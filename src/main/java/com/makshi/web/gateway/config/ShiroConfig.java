package com.makshi.web.gateway.config;

import com.makshi.web.gateway.security.auth.RestfulServiceAuthenticationFilter;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.bouncycastle.asn1.x500.style.RFC4519Style;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "user");
        filterChainDefinitionMap.put("/login", "authc");
        filterChainDefinitionMap.put("/service/**", "authc");
        filterChainDefinitionMap.put("/**", "user");

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");

        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("sauthc",authenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);

        return shiroFilterFactoryBean;
    }

    public AuthenticationFilter authenticationFilter() {
        RestfulServiceAuthenticationFilter authenticationFilter = new RestfulServiceAuthenticationFilter();
        authenticationFilter.setServiceLoginFailUrl("/service/loginfail");
        authenticationFilter.setServiceLoginUrl("/service/login");

        return authenticationFilter;
    }


    @Bean
    public BaseShiroRealm baseShiroRealm(){
        BaseShiroRealm baseShiroRealm = new BaseShiroRealm();
        return baseShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(baseShiroRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }

//    @Bean
//    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
//        Properties mappings = new Properties();
//        mappings.setProperty("DatabaseException", "databaseError");
//        mappings.setProperty("UnauthorizedException", "403");
//        r.setExceptionMappings(mappings);
//        r.setDefaultErrorView("error");
//        r.setExceptionAttribute("ex");
//
//        return  r;
//    }
}
