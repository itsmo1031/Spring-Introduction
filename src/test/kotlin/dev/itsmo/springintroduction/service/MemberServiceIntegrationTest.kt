package dev.itsmo.springintroduction.service

import dev.itsmo.springintroduction.domain.Member
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Commit
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest(
    @Autowired private val memberService: MemberService
) : DescribeSpec() {

    override fun extensions(): List<Extension> = listOf(SpringExtension)

    init {
        this.describe("join") {
            context("멤버를 주면") {
                it("회원가입이 완료된다") {
                    val member = Member(name = "Spring")
                    withContext(Dispatchers.IO) {
                        val saveId = memberService.join(member)
                        val findMember = memberService.findOne(saveId)
                        
                        member.name shouldBe findMember?.name
                    }
                }
            }

            context("중복 멤버를 주면") {
                it("회원가입이 거절된다") {
                    val member1 = Member(name = "spring")
                    val member2 = Member(name = "spring")
                    withContext(Dispatchers.IO) {
                        memberService.join(member1)
                        val e = shouldThrow<IllegalStateException> { memberService.join(member2) }
                        e.message shouldBe "이미 존재하는 회원입니다."
                    }
                }
            }
        }
    }
}
