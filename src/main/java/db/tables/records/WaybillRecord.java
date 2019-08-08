/*
 * This file is generated by jOOQ.
 */
package db.tables.records;


import db.tables.Waybill;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WaybillRecord extends UpdatableRecordImpl<WaybillRecord> implements Record3<Integer, Date, String> {

    private static final long serialVersionUID = -1896376325;

    /**
     * Setter for <code>public.waybill.id</code>.
     */
    public WaybillRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.waybill.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.waybill.date</code>.
     */
    public WaybillRecord setDate(Date value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.waybill.date</code>.
     */
    public Date getDate() {
        return (Date) get(1);
    }

    /**
     * Setter for <code>public.waybill.organization</code>.
     */
    public WaybillRecord setOrganization(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.waybill.organization</code>.
     */
    public String getOrganization() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, Date, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, Date, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Waybill.WAYBILL.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field2() {
        return Waybill.WAYBILL.DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Waybill.WAYBILL.ORGANIZATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date component2() {
        return getDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getOrganization();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value2() {
        return getDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getOrganization();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaybillRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaybillRecord value2(Date value) {
        setDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaybillRecord value3(String value) {
        setOrganization(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaybillRecord values(Integer value1, Date value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached WaybillRecord
     */
    public WaybillRecord() {
        super(Waybill.WAYBILL);
    }

    /**
     * Create a detached, initialised WaybillRecord
     */
    public WaybillRecord(Integer id, Date date, String organization) {
        super(Waybill.WAYBILL);

        set(0, id);
        set(1, date);
        set(2, organization);
    }
}