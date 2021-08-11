package crud.daoImp;

import java.util.ArrayList;
import java.util.List;
import crud.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentDaoImp implements DepartmentDao
{
    @Autowired
    ConnectionDaoImp dao;
    
    @Override
    public List getDepartment() {
        List listData = new ArrayList();
        try {
            String query = "SELECT * FROM Department";
            System.out.println(query);
            dao = new ConnectionDaoImp();
            listData = dao.getListDataFromDB(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
        return listData;
    }
}
