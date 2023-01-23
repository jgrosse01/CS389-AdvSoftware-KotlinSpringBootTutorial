package edu.carroll.cs389.web.form

import jakarta.validation.constraints.Size;

class LoginForm {
    @Size(min = 6, message = "Username must be at least 6 characters")
    private lateinit var username: String;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private lateinit var password: String;

    fun getUsername(): String {
        return username;
    }

    fun setUsername(username: String) {
        this.username = username;
    }

    fun getPassword(): String {
        return password;
    }

    fun setPassword(password: String) {
        this.password = password;
    }
}