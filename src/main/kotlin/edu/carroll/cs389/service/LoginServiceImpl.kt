package edu.carroll.cs389.service

import edu.carroll.cs389.jpa.model.Login
import edu.carroll.cs389.jpa.repo.LoginRepository
import edu.carroll.cs389.web.form.LoginForm
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl(private val loginRepo: LoginRepository) : LoginService {
    /**
     * Given a loginForm, determine if the information provided is valid, and the user exists in the system.
     *
     * @param form - Data containing user login information, such as username and password.
     * @return true if data exists and matches what's on record, false otherwise
     */
    override fun validateUser(form: LoginForm): Boolean {
        val users: List<Login> = loginRepo.findByUsernameIgnoreCase(form.getUsername())
        if (users.size != 1) {
            return false
        }
        val u: Login = users[0]
        // XXX - Using Java's hashCode is wrong on SO many levels, but is good enough for demonstration purposes.
        // NEVER EVER do this in production code!

        val userProvidedHash: String = form.getPassword().hashCode().toString()
        if (!u.getHashedPassword().equals(userProvidedHash)) {
            return false
        }

        // user exists and input a valid password
        return true
    }
}