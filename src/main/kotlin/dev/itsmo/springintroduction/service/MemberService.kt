package dev.itsmo.springintroduction.service

import dev.itsmo.springintroduction.domain.Member
import dev.itsmo.springintroduction.repository.MemberRepository

class MemberService(private val memberRepository: MemberRepository) {
    /*
     * 회원 가입
     */
    fun join(member: Member): Long {
        // 같은 이름이 있는 중복 회원은 받지 않는다
        validateDuplicatedMember(member)
        memberRepository.save(member)

        return member.id
    }

    /*
     * 전체 회원 조회
     */
    fun findMembers(): List<Member> = memberRepository.findAll()

    fun findOne(memberId: Long): Member? = memberRepository.findById(memberId)

    private fun validateDuplicatedMember(member: Member) {
        if (memberRepository.findByName(member.name) != null) {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        }
    }
}