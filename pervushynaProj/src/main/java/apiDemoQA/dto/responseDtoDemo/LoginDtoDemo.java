package apiDemoQA.dto.responseDtoDemo;

import lombok.*;

    @Data
    @ToString
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public class LoginDtoDemo {
        private String userId;
        private String username;
        private String password;
        private String token;
        private String expires;
        private String created_date;
        private boolean isActive;
    }

