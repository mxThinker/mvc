package com.github.catstiger.mvc.resovler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.catstiger.mvc.annotation.API;
import com.github.catstiger.mvc.annotation.Domain;
import com.github.catstiger.mvc.annotation.Param;
import com.github.catstiger.mvc.converter.Corp;
import com.github.catstiger.mvc.converter.Department;
import com.github.catstiger.mvc.converter.Employee;
import com.github.catstiger.mvc.converter.TestValueConverter;

@Domain @Service
public class SpringTestService {
  @API @Transactional
  public String testPrimitive(@Param("age") int age, @Param("score") double score, @Param("id") long id,
        @Param("birth") Date birth, @Param("isActive") boolean isActive) {
    Employee emp = new Employee();
    emp.setAge(age);
    emp.setScore(score);
    emp.setId(id);
    emp.setBirth(birth);
    emp.setIsActive(isActive);
    
    return JSON.toJSONString(emp, true);
  }
  
  @API @Transactional
  public String testSingleBean(Employee employee) {
    return "";
  }
  
  @API @Transactional
  public String testAny(@Param("emp") Employee emp, @Param("dept") Department dept, @Param("corpId") Long corpId) {
    emp.setDept(dept);
    dept.setCorp(new Corp());
    dept.getCorp().setId(corpId);
    
    return "";
  }
  
  @API @Transactional
  public String testSingleValue(@Param("data") Double value) {
    return String.valueOf(value);
  }
  
  @API @Transactional
  public String testSinglePrimitiveArray(@Param("dbl") double [] dbl) {
    return JSON.toJSONString(dbl);
  }
  
  @API @Transactional
  public String testSingleDateArray(@Param("dates") Date[] date) {
    return JSON.toJSONString(date, SerializerFeature.WriteDateUseDateFormat);
  }
  
  
  @API @Transactional
  public void testHttpAndOther(@Param("emp") Employee emp, @Param("dept") Department dept, HttpServletRequest request) {
    if(emp == null) {
      throw new RuntimeException("Employee is null.");
    }
    
    if(request == null) {
      throw new RuntimeException("HttpServletRequest is null.");
    }
  }
  
  @API
  public void testCustomerConverter(@Param(value = "param", converter = TestValueConverter.class) String name) {
    if(!"catstiger@gmail.com".equals(name)) {
      throw new RuntimeException(name);
    }
  }
}
