package com.xebia.port.adapter.persistence;

import com.google.common.base.Objects;
import com.xebia.domain.echeance.CreditDecimal;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BigDecimalType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditDecimalType implements UserType {
    public static final CreditDecimalType INSTANCE = new CreditDecimalType();

    @Override
    public int[] sqlTypes() {
        return new int[]{
                BigDecimalType.INSTANCE.sqlType(),
        };
    }

    @Override
    public Class returnedClass() {
        return CreditDecimal.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return Objects.equal(x, y);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return Objects.hashCode(o);
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        assert names.length == 2;
        BigDecimal amount = (BigDecimal) BigDecimalType.INSTANCE.get(resultSet, names[0], session);
        return amount == null ? null : new CreditDecimal(amount);
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            preparedStatement.setNull(index, BigDecimalType.INSTANCE.sqlType());
        } else {
            final CreditDecimal creditDecimal = (CreditDecimal) value;
            preparedStatement.setBigDecimal(index, creditDecimal.getValue());
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) {
            return null;
        }

        return new CreditDecimal((BigDecimal) value);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable serializable, Object value) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object value, Object value2, Object value3) throws HibernateException {
        return value;
    }
}
