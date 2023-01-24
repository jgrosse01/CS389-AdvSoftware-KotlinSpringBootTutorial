package edu.carroll.cs389.service

import org.springframework.test.util.AssertionErrors.assertFalse
import org.springframework.test.util.AssertionErrors.assertNotNull
import org.springframework.test.util.AssertionErrors.assertTrue
import edu.carroll.cs389.jpa.model.Login
import edu.carroll.cs389.jpa.repo.LoginRepository
import edu.carroll.cs389.web.form.LoginForm
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LoginServiceImplTest {
    companion object {
        private val username: String = "testuser"
        private val password: String = "testpass"
    }

    @Autowired
    private lateinit var loginService: LoginService

    @Autowired
    private lateinit var loginRepository: LoginRepository

    private val fakeUser: Login = Login(username, password)

    @BeforeEach
    fun beforeTest() {
        assertNotNull("loginRepository must be injected", loginRepository)
        assertNotNull("LoginService must be injected", loginService)

        // place dummy DB record
        val users: List<Login> = loginRepository.findByUsernameIgnoreCase(username)
        if (users.isEmpty())
            loginRepository.save(fakeUser)
    }

    @Test
    fun validateUserSuccesssTest() {
        val form: LoginForm = LoginForm(username, password)
        assertTrue("validateUserSuccessTest: should succeed using proper login info", loginService.validateUser(form))
    }

    @Test
    fun validateUserExistingUserInvalidPasswordTest() {
        val form: LoginForm = LoginForm(username, password + "extra Characters")
        assertFalse("validateUserExistingUserInvalidPasswordTest: should fail with invalid password", loginService.validateUser(form))
    }

    @Test
    fun validateUserInvalidUserValidPassTest() {
        val form: LoginForm = LoginForm(username + "whyAmIDoingThis", password)
        assertFalse("validateUserInvalidUserValidPassTest: should fail with invalid username", loginService.validateUser(form))
    }

    @Test
    fun validateUserInvalidUserInvalidPassTest() {
        val form: LoginForm = LoginForm(username + "notActuallySeriousIKnowWhyWeDoThis", password + "more extra charaCTeRs")
        assertFalse("validateUserInvalidUserInvalidPassTest: should fail with invalid user and pass", loginService.validateUser(form))
    }
}