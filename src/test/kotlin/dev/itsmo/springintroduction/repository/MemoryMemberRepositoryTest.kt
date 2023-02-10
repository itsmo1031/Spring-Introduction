package dev.itsmo.springintroduction.repository

import dev.itsmo.springintroduction.domain.Member
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class MemoryMemberRepositoryTest : DescribeSpec({
    val repository = MemoryMemberRepository()
    val member1 = Member(name = "spring1")
    val member2 = Member(name = "spring2")

    afterEach {
        repository.clearStore()
    }

    describe("save") {
        context("멤버가 주어지면") {
            it("멤버가 레포지토리에 저장된다") {
                repository.save(member1)
                val result = repository.findById(member1.id)

                result shouldBe member1
            }
        }
    }

    describe("findByName") {
        context("멤버 이름이 주어지면") {
            it("멤버 이름을 기반으로 레포지토리토리에서 찾는다") {
                repository.save(member1)
                repository.save(member2)

                val result = repository.findByName("spring1")

                result shouldBe member1
            }
        }
    }

    describe("findAll") {
        context("findAll 메소드를 사용하면") {
            it("모든 멤버의 리스트를 반환한다") {
                repository.save(member1)
                repository.save(member2)

                val result = repository.findAll()

                result.size shouldBe 2
            }
        }
    }
})