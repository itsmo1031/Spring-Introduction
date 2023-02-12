package dev.itsmo.springintroduction.repository

import dev.itsmo.springintroduction.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataJpaMemberRepository : JpaRepository<Member, Long>, MemberRepository {
    override fun findByName(name: String): Member?
}