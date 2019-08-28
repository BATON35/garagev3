package com.konrad.garagev3.configuration;

//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration{

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private SimpleAuthenticationSuccessHandler successHandler;
//
//    @Value("${spring.queries.users-query}")
//    private String usersQuery;
//
//    @Value("${spring.queries.roles-query}")
//    private String rolesQuery;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.
//                jdbcAuthentication()
//                .usersByUsernameQuery(usersQuery)
//                .authoritiesByUsernameQuery(rolesQuery)
//                .dataSource(dataSource)
//                .passwordEncoder(bCryptPasswordEncoder);
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.
//                authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/about").permitAll()
//                .antMatchers("/contact").permitAll()
//                .antMatchers("/services").permitAll()
//                .antMatchers("/registration").permitAll()
//                .antMatchers("/employee").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")
//                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN").anyRequest()
//                .authenticated().and().csrf().disable().formLogin().successHandler(successHandler)
//                .loginPage("/login").permitAll()
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/").and().exceptionHandling()
//                .accessDeniedPage("/access-denied");
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/vector/**");
//    }

}
