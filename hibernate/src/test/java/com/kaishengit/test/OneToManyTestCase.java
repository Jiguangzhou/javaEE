package com.kaishengit.test;

import com.kaishengit.pojo.Dept;
import com.kaishengit.pojo.Employee;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OneToManyTestCase {

    //最佳实践
    //1.先存一，再存多
    //2.让一得一端放弃关系维护
    //3.fecth = join用来解决N+1问题

    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Dept dept = new Dept();
        dept.setDeptname("人事部");

        Employee employee = new Employee();
        employee.setName("Lucy");
        employee.setDept(dept);

        Employee employee1 = new Employee();
        employee1.setName("Lily");
        employee1.setDept(dept);

        /*Set<Employee> employees = new HashSet<>();
        employees.add(employee);
        employees.add(employee1);

        dept.setEmployeeSet(employees);*/

        session.save(dept);
        session.save(employee);
        session.save(employee1);

        session.getTransaction().commit();
    }

    @Test
    public void testFindDept(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Dept dept = (Dept) session.get(Dept.class,2);
        System.out.println(dept.getDeptname());

        Set<Employee> employeeSet = dept.getEmployeeSet();
        for (Employee employee:employeeSet){
            System.out.println(employee.getName());
        }
        session.getTransaction().commit();
    }

    @Test
    public void testFindEmployee(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Employee employee = (Employee) session.get(Employee.class,3);
        System.out.println(employee.getName());

        Dept dept = employee.getDept();
        System.out.println(dept.getDeptname());

        session.getTransaction().commit();
    }

    @Test
    public void findEmployeeAll(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        //N+1问题 解决：fecth = join或lazy = false或使用二级缓存
        Criteria criteria = session.createCriteria(Employee.class);
        List<Employee> employeeList = criteria.list();

        for (Employee employee : employeeList){
            System.out.println(employee.getId()+":"+employee.getName()+":"+employee.getDept().getDeptname());
        }
        session.getTransaction().commit();
    }

    @Test
    public void testDelete(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        //级联删除
        Dept dept = (Dept) session.get(Dept.class,1);
        session.delete(dept);
        session.getTransaction().commit();
    }
}
