package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
        private String id;
        private String username;
        private String password;
        private String nickname;
        private int score;
        private int bits;
        private int money;
        private String avatar;
     private String phone;
        @Override
        public String toString() {
                return "User{" +
                        "id='" + id + '\'' +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", score=" + score +
                        ", bits=" + bits +
                        ", money=" + money +
                        ", phone=" + phone +
                        ", avatar='" + avatar + '\'' +
                        '}';
        }
}
