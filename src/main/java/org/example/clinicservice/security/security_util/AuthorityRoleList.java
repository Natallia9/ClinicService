package org.example.clinicservice.security.security_util;

public class AuthorityRoleList {

    public static final String[] SPECIALIST_LIST = {

            "/lab-reports/**",
            "/medical-records/**",
            "/patients/**",
            "/prescriptions/**",
            "/schedule/**",
            "/patient-visits/**",
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**"
    };

    public static final String[] PATIENT_LIST = {
            "/patients/**",
            "/patient-visits/**",
            "/medical-records/**",
            "/payments/**",
            "/prescriptions/**",
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**"
    };

    public static final String[] ADMIN_LIST = {
            "/users/**",
            "/roles/**",
            "/appointments/**",
            "/financial-transactions/**",
            "/schedule/**",
            "/system-owner/**",
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/admin/**"
    };
}

