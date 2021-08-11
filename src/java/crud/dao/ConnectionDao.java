package crud.dao;

import java.util.List;
import org.hibernate.Query;
import org.json.simple.JSONObject;

public interface ConnectionDao {
    public List getListDataFromDB(String strQuery);
    public String getSingleDataFromDB(String strQuery);
    public String getStringFromJsonObj(JSONObject jsonObj, String strKey);
    public int executeDB(String query);
    public int executeDBQuery(Query query);
}
