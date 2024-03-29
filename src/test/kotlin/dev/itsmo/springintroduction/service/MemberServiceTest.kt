package dev.itsmo.springintroduction.service

import dev.itsmo.springintroduction.domain.Member
import dev.itsmo.springintroduction.repository.MemoryMemberRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MemberServiceTest : DescribeSpec({
    val memberRepository = MemoryMemberRepository()
    val memberService = MemberService(memberRepository)

    afterEach {
        memberRepository.clearStore()
    }

    describe("join") {
        context("멤버를 주면") {
            it("회원가입이 완료된다") {
                val member = Member(name = "spring")
                val saveId = memberService.join(member)
                val findMember = memberService.findOne(saveId)

                member.name shouldBe findMember?.name
            }
        }

        context("중복 멤버를 주면") {
            it("회원가입이 거절된다") {
                val member1 = Member(name = "spring")
                val member2 = Member(name = "spring")
                memberService.join(member1)
                val e = shouldThrow<IllegalStateException> { memberService.join(member2) }
                e.message shouldBe "이미 존재하는 회원입니다."
            }
        }
    }

    describe("findMembers") { }

    describe("findOne") { }
})
