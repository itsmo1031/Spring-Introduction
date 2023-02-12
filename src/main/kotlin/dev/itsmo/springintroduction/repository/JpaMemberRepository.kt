package dev.itsmo.springintroduction.repository

import dev.itsmo.springintroduction.domain.Member
import jakarta.persistence.EntityManager

class JpaMemberRepository(
    private val em: EntityManager
) : MemberRepository {
    override fun save(member: Member): Member {
        em.persist(member)
        return member
    }

    override fun findById(id: Long): Member? {
        return em.find(Member::class.java, id)
    }

    override fun findByName(name: String): Member? {
        return em.createQuery("SELECT m from Member m where m.name=:name", Member::class.java)
            .setParameter("name", name)
            .resultList.firstOrNull()
    }

    override fun findAll(): List<Member> {
        return em.createQuery("SELECT m from Member m", Member::class.java).resultList
    }
}