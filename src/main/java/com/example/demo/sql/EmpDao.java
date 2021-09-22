package com.example.demo.sql;

import com.example.demo.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class EmpDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Collection<Emp> findAll(){
        List<Emp> empList=new ArrayList<>();
        empList=jdbcTemplate.query("select * from emp1", new RowMapper<Emp>() {

            @Override
            public Emp mapRow(ResultSet resultSet, int i) throws SQLException {
                Emp emp=new Emp();
                emp.setId(resultSet.getInt("emp_id"));
                emp.setEmp_name(resultSet.getString("emp_name"));
                emp.setSalary(resultSet.getString("salary"));
                return emp;
            }
        });
        return empList;
    }

    public Collection<Emp> findById(Integer id){
        List<Emp> empList=new ArrayList<>();
        empList=jdbcTemplate.query("select * from emp1 where emp_id= " + id, new RowMapper<Emp>() {

            @Override
            public Emp mapRow(ResultSet resultSet, int i) throws SQLException {
                Emp emp=new Emp();
                emp.setId(resultSet.getInt("emp_id"));
                emp.setEmp_name(resultSet.getString("emp_name"));
                emp.setSalary(resultSet.getString("salary"));
                return emp;
            }
        });
        return empList;
    }


    public boolean saveEmp(Emp emp){
        String insert="insert into emp1(emp_name,salary) values(?,?)";
        int result = jdbcTemplate.update(insert, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {

                preparedStatement.setString(1,emp.getEmp_name());
                preparedStatement.setString(2,emp.getSalary());
            }
        });
        if(result>0){
            return true;
        }
        return false;
    }


}
