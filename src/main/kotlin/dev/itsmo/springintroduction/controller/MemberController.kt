package dev.itsmo.springintroduction.controller

import dev.itsmo.springintroduction.domain.Member
import dev.itsmo.springintroduction.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MemberController(@Autowired private val memberService: MemberService) {
    init {
        println(memberService::class)
    }

    @GetMapping("/members/new")
    fun createForm(): String {
        return "members/createMemberForm"
    }

    @PostMapping("/members/new")
    fun create(form: MemberForm): String {
        val member = Member(name = form.name)
        memberService.join(member)

        return "redirect:/"
    }

    @GetMapping("/members")
    fun list(model: Model): String {
        val members = memberService.findMembers()
        model["members"] = members

        return "members/memberList"
    }
}