package edu.carroll.cs389.service

import edu.carroll.cs389.jpa.model.Login
import edu.carroll.cs389.jpa.repo.LoginRepository
import edu.carroll.cs389.web.form.LoginForm
import org.springframework.stereotype.Service

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
class LoginServiceImpl(private val loginRepo: LoginRepository) : LoginService {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(LoginServiceImpl::class.java)
    }
    /**
     * Given a loginForm, determine if the information provided is valid, and the user exists in the system.
     *
     * @param form - Data containing user login information, such as username and password.
     * @return true if data exists and matches what's on record, false otherwise
     */
    override fun validateUser(form: LoginForm): Boolean {
        log.info("validateUser: user ${form.getUsername()} attempted login")
        val users: List<Login> = loginRepo.findByUsernameIgnoreCase(form.getUsername())
        if (users.size != 1) {
            log.debug("validateUser: found ${users.size} users")
            return false
        }
        val u: Login = users[0]
        // XXX - Using Java's hashCode is wrong on SO many levels, but is good enough for demonstration purposes.
        // NEVER EVER do this in production code!

        val userProvidedHash: String = form.getPassword().hashCode().toString()
        if (!u.getHashedPassword().equals(userProvidedHash)) {
            log.debug("validateUser: password !match")
            return false
        }

        // user exists and input a valid password
        log.debug("validateUser: successful login")
        return true
    }
}