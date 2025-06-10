package org.example.healthcare.user;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
    public class RegisterRequest{
        private String username;
        private String email;
        private String password;

        public CharSequence Password() {
            return password;
        }
    }




