
package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tas.bean.Course;
import com.tas.bean.PaperType;
import com.tas.dao.PaperTypeDao;
import com.tas.db.DBPool;


public class PaperTypeDaoImpl implements PaperTypeDao {

    
    @Override
    public List<PaperType> getPaperTypes() {
        List<PaperType> papertypeList = new ArrayList<PaperType>();
        DBPool dbpool = new DBPool();
           String sql="select [paperTypeId],[paperTypeName] from T_PaperType";
           ResultSet rs=null; 
           PaperType papertype=null;
           try {
               rs=dbpool.doQueryRS(sql, new Object[]{ });
               while (rs.next()) {
                   papertype = new PaperType();
                   papertype.setPaperTypeId(rs.getInt("paperTypeId"));
                   papertype.setPaperTypeName(rs.getString("paperTypeName"));
                   papertypeList.add(papertype);
               } 
           } catch (SQLException e) {
               e.printStackTrace();
           }finally{
               dbpool.close();
           }
           return papertypeList;
    }

}
