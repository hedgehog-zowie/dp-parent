package com.iuni.dp.persist.common.utils;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import oracle.sql.CLOB;
import oracle.sql.DATE;
import oracle.sql.TIMESTAMP;

import com.ibatis.sqlmap.engine.type.BaseTypeHandler;
import com.ibatis.sqlmap.engine.type.TypeHandler;

public class OracleObjectTypeHandler extends BaseTypeHandler implements TypeHandler {

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter,
			String jdbcType) throws SQLException {
		ps.setObject(i, parameter);
	}

	@Override
	public Object getResult(ResultSet rs, String columnName)
			throws SQLException {
		Object object = rs.getObject(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return fix(object);
        }
	}

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		Object object = rs.getObject(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return fix(object);
        }
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		Object object = cs.getObject(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return fix(object);
        }
	}

	@Override
	public Object valueOf(String s) {
		return s;
	}
	
	protected Object fix(Object obj) {
        try {
            if (obj instanceof TIMESTAMP) {
                return ((TIMESTAMP) obj).timestampValue();
            } else if (obj instanceof DATE) {
                return new Date(((DATE) obj).dateValue().getTime());
            } else if(obj instanceof CLOB) {
            	Reader reader = null;  
        	    StringBuffer sb = new StringBuffer();  
        	    try {  
        	        reader = ((CLOB)obj).getCharacterStream();  
        	        BufferedReader br = new BufferedReader(reader);  
        	        String temp = null;  
        	        while ((temp=br.readLine()) != null) {  
        	            sb.append(temp);  
        	        }  
        	    } catch (Exception e) {  
        	    	return obj;
        	    }     
        	    return sb.toString(); 
            }
            else {
                return obj;
            }
        } catch (Exception e) {
            return obj;
        }
    }

}
