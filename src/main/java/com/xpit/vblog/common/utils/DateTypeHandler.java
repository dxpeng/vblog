package com.xpit.vblog.common.utils;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * 日期格式化
 */
@MappedJdbcTypes(JdbcType.DATE)
@MappedTypes(String.class)
public class DateTypeHandler implements TypeHandler<String> {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddd");

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public String getResult(ResultSet resultSet, String columnName) throws SQLException {
        return sdf.format(resultSet.getDate(columnName));
    }

    @Override
    public String getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return sdf.format(resultSet.getDate(columnIndex));
    }

    @Override
    public String getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return sdf.format(callableStatement.getDate(columnIndex));
    }
}
