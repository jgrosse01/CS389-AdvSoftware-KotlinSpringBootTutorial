package edu.carroll.cs389.jpa.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="login")
class Login() {
    companion object {
        val serialVersionID: Long = 1L
        val EOL: String = System.lineSeparator()
        val TAB: String = "\t"
    }

    constructor(username: String, rawPassword: String) : this() {
        this.username = username
        setRawPassword(rawPassword)
    }

    @Id
    @GeneratedValue
    private var id: Int? = null

    @Column(name = "username", nullable = false, unique = true)
    private var username: String? = null

    @Column(name = "password", nullable = false)
    private var hashedPassword: String? = null

    fun getId(): Int? {
        return id
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun getHashedPassword(): String? {
        return hashedPassword
    }

    fun setHashedPassword(hashedPassword: String) {
        this.hashedPassword = hashedPassword
    }

    private fun setRawPassword(rawPassword: String) {
        this.hashedPassword = rawPassword.hashCode().toString()
    }

    override fun toString(): String {
        return "Login @ ${super.toString()} [ $EOL" +
                "$TAB username=$username $EOL" +
                "$TAB hashedPassword=**** $EOL" +
                "]"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || other::class != this::class) {
            return false;
        }
        val login: Login = other as Login
        return username.equals(login.username) && hashedPassword.equals(login.hashedPassword)
    }

    override fun hashCode(): Int {
        return Objects.hash(username, hashedPassword)
    }
}