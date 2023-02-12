package dev.itsmo.springintroduction

import dev.itsmo.springintroduction.repository.*
import dev.itsmo.springintroduction.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig(@Autowired private val memberRepository: MemberRepository) {

    @Bean
    fun memberService(): MemberService = MemberService(memberRepository)

//    @Bean
//    fun memberRepository(): MemberRepository {
//        // return MemoryMemberRepository()
//        // return JdbcMemberRepository(dataSource)
//        // return JdbcTemplateMemberRepository(dataSource)
//        // return JpaMemberRepository(em)
//    }
}