package dev.itsmo.springintroduction

import dev.itsmo.springintroduction.repository.JdbcMemberRepository
import dev.itsmo.springintroduction.repository.JdbcTemplateMemberRepository
import dev.itsmo.springintroduction.repository.MemberRepository
import dev.itsmo.springintroduction.repository.MemoryMemberRepository
import dev.itsmo.springintroduction.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class SpringConfig(private val dataSource: DataSource) {

    @Bean
    fun memberService(): MemberService = MemberService(memberRepository())

    @Bean
    fun memberRepository(): MemberRepository {
        // return MemoryMemberRepository()
        // return JdbcMemberRepository(dataSource)
        return JdbcTemplateMemberRepository(dataSource)
    }
}