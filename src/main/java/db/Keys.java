/*
 * This file is generated by jOOQ.
 */
package db;


import db.tables.FlywaySchemaHistory;
import db.tables.Nomenclatures;
import db.tables.Organizations;
import db.tables.Waybill;
import db.tables.Waybillpositions;
import db.tables.records.FlywaySchemaHistoryRecord;
import db.tables.records.NomenclaturesRecord;
import db.tables.records.OrganizationsRecord;
import db.tables.records.WaybillRecord;
import db.tables.records.WaybillpositionsRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = UniqueKeys0.FLYWAY_SCHEMA_HISTORY_PK;
    public static final UniqueKey<NomenclaturesRecord> NOMENCLATURES_PKEY = UniqueKeys0.NOMENCLATURES_PKEY;
    public static final UniqueKey<OrganizationsRecord> ORGANIZATIONS_PKEY = UniqueKeys0.ORGANIZATIONS_PKEY;
    public static final UniqueKey<WaybillRecord> WAYBILL_PKEY = UniqueKeys0.WAYBILL_PKEY;
    public static final UniqueKey<WaybillpositionsRecord> WAYBILLPOSITIONS_PKEY = UniqueKeys0.WAYBILLPOSITIONS_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<WaybillRecord, OrganizationsRecord> WAYBILL__WAYBILL_ORGANIZATION_FKEY = ForeignKeys0.WAYBILL__WAYBILL_ORGANIZATION_FKEY;
    public static final ForeignKey<WaybillpositionsRecord, NomenclaturesRecord> WAYBILLPOSITIONS__WAYBILLPOSITIONS_NOMENCLATURE_FKEY = ForeignKeys0.WAYBILLPOSITIONS__WAYBILLPOSITIONS_NOMENCLATURE_FKEY;
    public static final ForeignKey<WaybillpositionsRecord, WaybillRecord> WAYBILLPOSITIONS__WAYBILLPOSITIONS_WAYBILLID_FKEY = ForeignKeys0.WAYBILLPOSITIONS__WAYBILLPOSITIONS_WAYBILLID_FKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "flyway_schema_history_pk", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
        public static final UniqueKey<NomenclaturesRecord> NOMENCLATURES_PKEY = Internal.createUniqueKey(Nomenclatures.NOMENCLATURES, "nomenclatures_pkey", Nomenclatures.NOMENCLATURES.NAME);
        public static final UniqueKey<OrganizationsRecord> ORGANIZATIONS_PKEY = Internal.createUniqueKey(Organizations.ORGANIZATIONS, "organizations_pkey", Organizations.ORGANIZATIONS.ORGANIZATION);
        public static final UniqueKey<WaybillRecord> WAYBILL_PKEY = Internal.createUniqueKey(Waybill.WAYBILL, "waybill_pkey", Waybill.WAYBILL.ID);
        public static final UniqueKey<WaybillpositionsRecord> WAYBILLPOSITIONS_PKEY = Internal.createUniqueKey(Waybillpositions.WAYBILLPOSITIONS, "waybillpositions_pkey", Waybillpositions.WAYBILLPOSITIONS.ID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<WaybillRecord, OrganizationsRecord> WAYBILL__WAYBILL_ORGANIZATION_FKEY = Internal.createForeignKey(db.Keys.ORGANIZATIONS_PKEY, Waybill.WAYBILL, "waybill__waybill_organization_fkey", Waybill.WAYBILL.ORGANIZATION);
        public static final ForeignKey<WaybillpositionsRecord, NomenclaturesRecord> WAYBILLPOSITIONS__WAYBILLPOSITIONS_NOMENCLATURE_FKEY = Internal.createForeignKey(db.Keys.NOMENCLATURES_PKEY, Waybillpositions.WAYBILLPOSITIONS, "waybillpositions__waybillpositions_nomenclature_fkey", Waybillpositions.WAYBILLPOSITIONS.NOMENCLATURE);
        public static final ForeignKey<WaybillpositionsRecord, WaybillRecord> WAYBILLPOSITIONS__WAYBILLPOSITIONS_WAYBILLID_FKEY = Internal.createForeignKey(db.Keys.WAYBILL_PKEY, Waybillpositions.WAYBILLPOSITIONS, "waybillpositions__waybillpositions_waybillid_fkey", Waybillpositions.WAYBILLPOSITIONS.WAYBILLID);
    }
}