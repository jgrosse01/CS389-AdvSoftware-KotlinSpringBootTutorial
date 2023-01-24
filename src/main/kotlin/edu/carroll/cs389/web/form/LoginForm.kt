package edu.carroll.cs389.web.form

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

class LoginForm() {
    @field:NotNull
    @field:Size(min = 6, message = "Username must be at least 6 characters")
    private var username: String? = null;

    @field:NotNull
    @field:Size(min = 8, message = "Password must be at least 8 characters")
    private var password: String? = null;

    constructor(username: String, password: String) : this() {
        this.username = username
        this.password = password
    }

    fun getUsername(): String? {
        return username;
    }

    fun setUsername(username: String) {
        this.username = username;
    }

    fun getPassword(): String? {
        return password;
    }

    fun setPassword(password: String) {
        this.password = password;
    }
}