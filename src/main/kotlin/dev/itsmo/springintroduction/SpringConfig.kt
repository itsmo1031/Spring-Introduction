package dev.itsmo.springintroduction

import dev.itsmo.springintroduction.repository.MemberRepository
import dev.itsmo.springintroduction.repository.MemoryMemberRepository
import dev.itsmo.springintroduction.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig {

    @Bean
    fun memberService(): MemberService = MemberService(memberRepository())

    @Bean
    fun memberRepository(): MemberRepository = MemoryMemberRepository()
}