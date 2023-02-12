package dev.itsmo.springintroduction

import dev.itsmo.springintroduction.repository.*
import dev.itsmo.springintroduction.service.MemberService
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class SpringConfig(val em: EntityManager) {

    @Bean
    fun memberService(): MemberService = MemberService(memberRepository())

    @Bean
    fun memberRepository(): MemberRepository {
        // return MemoryMemberRepository()
        // return JdbcMemberRepository(dataSource)
        // return JdbcTemplateMemberRepository(dataSource)
        return JpaMemberRepository(em)
    }
}