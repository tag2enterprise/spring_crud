package crud.daoImp;

import crud.dao.EmployeeDao;
import java.util.ArrayList;
import java.util.List;
import crud.util.MySqlHibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDaoImp implements EmployeeDao {

    @Autowired
    ConnectionDaoImp dao;

    public List getEmployee() {
        List listData = new ArrayList();
        try {
            String query = "SELECT * FROM employee";
            System.out.println(query);
            dao = new ConnectionDaoImp();
            listData = dao.getListDataFromDB(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
        return listData;
    }

    @Override
    public int insertEmployee(String employee_id, String employee_name, String department_id, String unit_id) {
        int status;
        try {
            String query = "INSERT INTO employee (employee_id, employee_name, department_id,unit_id)\n"
                            + "VALUES ('"+employee_id +"', '"+employee_name+"', '"+department_id+"', '"+unit_id+"')";
            System.out.println(query);
            dao = new ConnectionDaoImp();
            status = dao.executeDB(query);
        } catch (Exception e) {
            status = -1;
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
        return status;
    }

    @Override
    public int deleteEmployeeByEmployeeId(String employee_id) {
        int numRowAffected;
        try {
            String query = "DELETE FROM employee WHERE employee_id = '" + employee_id + "'";
            System.out.println(query);
            dao = new ConnectionDaoImp();
            numRowAffected = dao.executeDB(query);
        } catch (Exception e) {
            numRowAffected = -1;
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
        return numRowAffected;
    }

    @Override
    public int editEmployeeByEmployeeId(String employee_id, String employee_name , String department_id, String unit_id) {
        int numRowAffected;
        try {
            String query = "UPDATE employee SET "
                            + "\n employee_name='"+employee_name+"', "
                            + "\n department_id='"+department_id+"', "
                            + "\n unit_id='"+unit_id+"'"
                            + "\n WHERE employee_id ='"+employee_id+"'";
            System.out.println(query);
            dao = new ConnectionDaoImp();
            numRowAffected = dao.executeDB(query);
        } catch (Exception e) {
            numRowAffected = -1;
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
        return numRowAffected;
    }
}
