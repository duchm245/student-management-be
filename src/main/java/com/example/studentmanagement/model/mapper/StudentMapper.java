package com.example.studentmanagement.model.mapper;

import com.example.studentmanagement.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        var student = new Student();
        student.setId(rs.getInt("id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        // bo sung them
        return student;
    }
}
