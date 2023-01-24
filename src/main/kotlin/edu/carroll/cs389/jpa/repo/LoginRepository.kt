package edu.carroll.cs389.jpa.repo

import edu.carroll.cs389.jpa.model.Login
import org.springframework.data.jpa.repository.JpaRepository

interface LoginRepository : JpaRepository<Login, Int> {
    // JPA doesn't like single elements so let's use a list
    fun findByUsernameIgnoreCase(username: String?): List<Login>
}