package dev.itsmo.springintroduction.repository

import dev.itsmo.springintroduction.domain.Member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import javax.sql.DataSource

class JdbcTemplateMemberRepository(
    @Autowired private val dataSource: DataSource,
    @Autowired private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)
) : MemberRepository {

    override fun save(member: Member): Member {
        val jdbcInsert = SimpleJdbcInsert(jdbcTemplate)
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id")

        val parameters = hashMapOf<String, Any>()
        parameters["name"] = member.name

        val key = jdbcInsert.executeAndReturnKey(MapSqlParameterSource(parameters))
        member.id = key.toLong()

        return member
    }

    override fun findById(id: Long): Member? {
        val result = jdbcTemplate.query(
            "SELECT * FROM member WHERE id=?",
            memberRowMapper,
            id
        )
        return result.firstOrNull()
    }

    override fun findByName(name: String): Member? {
        val result = jdbcTemplate.query(
            "SELECT * FROM member WHERE name=?",
            memberRowMapper,
            name
        )
        return result.firstOrNull()
    }

    override fun findAll(): List<Member> =
        jdbcTemplate.query("SELECT * FROM member", memberRowMapper)

    private val memberRowMapper = RowMapper<Member> { rs, _ ->
        Member(rs.getLong("id"), rs.getString("name"))
    }
}