package crud.daoImp;

import java.util.ArrayList;
import java.util.List;
import crud.dao.UnitDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UnitDaoImp implements UnitDao{

    @Autowired
    ConnectionDaoImp dao;
    
    @Override
    public List getUnitByDepartmentId(String department_id) {
        List listData = new ArrayList();
        try {
            String query = "SELECT * FROM Unit";
            if (!department_id.equals("") ) {
                query += " WHERE department_id = '" + department_id + "'"; 
            }
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
