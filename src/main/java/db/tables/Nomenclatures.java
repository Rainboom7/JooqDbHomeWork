/*
 * This file is generated by jOOQ.
 */
package db.tables;


import db.Indexes;
import db.Keys;
import db.Public;
import db.tables.records.NomenclaturesRecord;

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
public class Nomenclatures extends TableImpl<NomenclaturesRecord> {

    private static final long serialVersionUID = 1197379142;

    /**
     * The reference instance of <code>public.nomenclatures</code>
     */
    public static final Nomenclatures NOMENCLATURES = new Nomenclatures();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<NomenclaturesRecord> getRecordType() {
        return NomenclaturesRecord.class;
    }

    /**
     * The column <code>public.nomenclatures.name</code>.
     */
    public final TableField<NomenclaturesRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.nomenclatures.code</code>.
     */
    public final TableField<NomenclaturesRecord, Integer> CODE = createField("code", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>public.nomenclatures</code> table reference
     */
    public Nomenclatures() {
        this(DSL.name("nomenclatures"), null);
    }

    /**
     * Create an aliased <code>public.nomenclatures</code> table reference
     */
    public Nomenclatures(String alias) {
        this(DSL.name(alias), NOMENCLATURES);
    }

    /**
     * Create an aliased <code>public.nomenclatures</code> table reference
     */
    public Nomenclatures(Name alias) {
        this(alias, NOMENCLATURES);
    }

    private Nomenclatures(Name alias, Table<NomenclaturesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Nomenclatures(Name alias, Table<NomenclaturesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Nomenclatures(Table<O> child, ForeignKey<O, NomenclaturesRecord> key) {
        super(child, key, NOMENCLATURES);
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
        return Arrays.<Index>asList(Indexes.NOMENCLATURES_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<NomenclaturesRecord> getPrimaryKey() {
        return Keys.NOMENCLATURES_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<NomenclaturesRecord>> getKeys() {
        return Arrays.<UniqueKey<NomenclaturesRecord>>asList(Keys.NOMENCLATURES_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Nomenclatures as(String alias) {
        return new Nomenclatures(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Nomenclatures as(Name alias) {
        return new Nomenclatures(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Nomenclatures rename(String name) {
        return new Nomenclatures(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Nomenclatures rename(Name name) {
        return new Nomenclatures(name, null);
    }
}
