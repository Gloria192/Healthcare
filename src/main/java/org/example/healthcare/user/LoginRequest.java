package org.example.healthcare.user;


import lombok.Getter;
import lombok.Setter;

    @Getter
    @Setter
    public class  LoginRequest{
        private String username;
        private String password;

        public CharSequence password() {
            return password;
        }
    }





