package crud.daoImp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import crud.dao.ConnectionDao;
import crud.util.MySqlHibernate;
import static org.apache.commons.lang.StringUtils.trim;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ConnectionDaoImp implements ConnectionDao{
   
    
    public List getListDataFromDB(String strQuery) {
        
        List listData   = new ArrayList();
        Session session = MySqlHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        
        try {
            listData = session.createSQLQuery(strQuery).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
        } catch (Exception e) {
            listData.add("ERR");
            System.out.println("error : " + e.getMessage() + " , lokasi : " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        session.flush();
        session.close();
        return listData;
    }

    public String getStringFromJsonObj(JSONObject jsonObj, String strKey) {
        if (jsonObj.get(strKey) != null && jsonObj.get(strKey) != "") {
            return trim(jsonObj.get(strKey).toString());
        } else {
            return "";
        }
    }

    
    public String getSingleDataFromDB(String strQuery) {
        createLogSyntaxSQL(strQuery);
        String strData  = "";
        Session session = null;
        session         = MySqlHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query lsStrSQL = session.createSQLQuery(strQuery).setResultTransformer(Criteria.ROOT_ENTITY).setMaxResults(1);
            
            if (lsStrSQL.list().isEmpty()) {
                strData = "";
            } else {
                strData = lsStrSQL.uniqueResult().toString();
            }
        } catch (Exception e) {
            createLogErrorSQL(e.getMessage());
            strData = "ERR";
        }
        session.clear();
        session.close();
        return strData;
    }
    
    
    public int executeDB(String query) {
        createLogSyntaxSQL(query);
        int intReturn;
        Session session = MySqlHibernate.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            Query strsql = (Query) session.createSQLQuery(query);
            intReturn = strsql.executeUpdate();
            System.out.println(intReturn);
            session.getTransaction().commit();
//            intReturn = 1;
        } catch (Exception e) {
            e.printStackTrace();
            createLogErrorSQL(e.getMessage());
            session.getTransaction().rollback();
            intReturn = -1;
        }
        
        session.clear();
        session.close();
        return intReturn;
    }
    
    public int executeDBQuery(Query query) {
        int intReturn;
        Session session = MySqlHibernate.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            intReturn = query.executeUpdate();
            session.getTransaction().commit();
//            intReturn = 1;
        } catch (Exception e) {
            e.printStackTrace();
            createLogErrorSQL(e.getMessage());
            session.getTransaction().rollback();
            intReturn = 0;
        }

        session.clear();
        session.close();
        return intReturn;
    }
    
    public String getPropertiesFile(String strVar) {
        Properties prop         = new Properties();
        String propFileName     = "/crud/conf/log.properties";
        InputStream inputStream = getClass().getResourceAsStream(propFileName);
        try {
            prop.load(inputStream);
            inputStream.close();
            return prop.getProperty(strVar);
        } catch (IOException ex) {
            return "ERR";
        }
    }

    private String createDirectoryFile(String dirName, String dirAdd) {
        String strDirektori = getPropertiesFile("hddrive") + getPropertiesFile("maindir");
        File dirFirst       = new File(strDirektori);
        if (!dirFirst.exists()) {
            dirFirst.mkdir();
        }
        if (dirAdd == null) {
            strDirektori = strDirektori + getPropertiesFile(dirName);
            File dirSecond = new File(strDirektori);
            if (!dirSecond.exists()) {
                dirSecond.mkdir();
            }
        } else {
            strDirektori = strDirektori + getPropertiesFile(dirName) + dirAdd;
            File dirThird = new File(strDirektori);
            if (!dirThird.exists()) {
                dirThird.mkdirs();
            }
        }
        return strDirektori;
    }
    
    private void createLogSyntaxSQL(String strSQL) {
        String logDirektori = createDirectoryFile("logsqlx", null);
        String logFilename  = logDirektori + "SQL-" + new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".txt";
        try {
            Writer writer;
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFilename, true), "UTF-8"));
            writer.append(new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime()) + " : " + strSQL);
            writer.append(System.lineSeparator());
            writer.close();
        } catch (IOException ex) {
        }
    }

    public void createLogErrorSQL(String strError) {
        String logDirektori = createDirectoryFile("logsqlx", null);
        File folder = new File(logDirektori);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length - Integer.parseInt(getPropertiesFile("jmlhdir")); i++) {
            listOfFiles[i].delete();
        }
        String logFilename = logDirektori + "SQL-" + new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".txt";
        try {
            Writer writer;
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFilename, true), "UTF-8"));
            writer.append("Error -> " + strError);
            writer.append(System.lineSeparator());
            writer.append(System.lineSeparator());
            writer.close();
        } catch (IOException ex) {
        }
    }
}
