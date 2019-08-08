/*
 * This file is generated by jOOQ.
 */
package db.tables;


import db.Indexes;
import db.Keys;
import db.Public;
import db.tables.records.WaybillRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Waybill extends TableImpl<WaybillRecord> {

    private static final long serialVersionUID = 536927546;

    /**
     * The reference instance of <code>public.waybill</code>
     */
    public static final Waybill WAYBILL = new Waybill();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WaybillRecord> getRecordType() {
        return WaybillRecord.class;
    }

    /**
     * The column <code>public.waybill.id</code>.
     */
    public final TableField<WaybillRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.waybill.date</code>.
     */
    public final TableField<WaybillRecord, Date> DATE = createField("date", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>public.waybill.organization</code>.
     */
    public final TableField<WaybillRecord, String> ORGANIZATION = createField("organization", org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * Create a <code>public.waybill</code> table reference
     */
    public Waybill() {
        this(DSL.name("waybill"), null);
    }

    /**
     * Create an aliased <code>public.waybill</code> table reference
     */
    public Waybill(String alias) {
        this(DSL.name(alias), WAYBILL);
    }

    /**
     * Create an aliased <code>public.waybill</code> table reference
     */
    public Waybill(Name alias) {
        this(alias, WAYBILL);
    }

    private Waybill(Name alias, Table<WaybillRecord> aliased) {
        this(alias, aliased, null);
    }

    private Waybill(Name alias, Table<WaybillRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Waybill(Table<O> child, ForeignKey<O, WaybillRecord> key) {
        super(child, key, WAYBILL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.WAYBILL_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<WaybillRecord> getPrimaryKey() {
        return Keys.WAYBILL_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<WaybillRecord>> getKeys() {
        return Arrays.<UniqueKey<WaybillRecord>>asList(Keys.WAYBILL_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<WaybillRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<WaybillRecord, ?>>asList(Keys.WAYBILL__WAYBILL_ORGANIZATION_FKEY);
    }

    public Organizations organizations() {
        return new Organizations(this, Keys.WAYBILL__WAYBILL_ORGANIZATION_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Waybill as(String alias) {
        return new Waybill(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Waybill as(Name alias) {
        return new Waybill(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Waybill rename(String name) {
        return new Waybill(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Waybill rename(Name name) {
        return new Waybill(name, null);
    }
}