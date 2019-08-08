
import db.tables.records.NomenclaturesRecord;
import db.tables.records.OrganizationsRecord;
import db.tables.records.WaybillRecord;
import db.tables.records.WaybillpositionsRecord;
import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import static db.tables.Nomenclatures.NOMENCLATURES;
import static db.tables.Organizations.*;
import static db.tables.Waybill.WAYBILL;
import static db.tables.Waybillpositions.WAYBILLPOSITIONS;
import static org.junit.Assert.assertNotEquals;

@SuppressWarnings({"Duplicates", "NullableProblems", "deprecation"})
public final class TableRecordTest {
    @NotNull
    private static final String ORG_1 = "org1";
    @NotNull
    private static final Integer INT = 123;
    @NotNull
    private static final String NOMEN_1 = "nomen1";
    @NotNull
    private static final String ORGAN_1 = "organ1";
    @NotNull
    private static final Date DATE = new Date( 2000, 1, 1 );
    @NotNull
    private static final String NOM_1 = "nom1";
    @NotNull
    private static final Date DATE1 = new Date( 2000, 12, 1 );
    @NotNull
    private
    DSLContext create;
    @NotNull
    private
    NomenclaturesRecord nomenclaturesRecord;
    @NotNull
    private
    OrganizationsRecord organizationsRecord;
    @NotNull
    private
    WaybillRecord waybillRecord;
    @NotNull
    private
    WaybillpositionsRecord waybillpositionsRecord;
    @Before
    public void setUpTest(){
        Flyway flyway=Flyway.configure().dataSource("jdbc:postgresql://localhost:3600/test","postgres","rainbow7" ).load();
        flyway.migrate();

        try{
            Connection connection= DriverManager.getConnection( "jdbc:postgresql://localhost:3600/test","postgres","rainbow7" );
            create = DSL.using(connection, SQLDialect.POSTGRES_10 );
            nomenclaturesRecord = create.newRecord( NOMENCLATURES );
            organizationsRecord=create.newRecord( ORGANIZATIONS );
            waybillRecord = create.newRecord( WAYBILL );
            waybillpositionsRecord=create.newRecord( WAYBILLPOSITIONS );
            organizationsRecord.delete();
            for (int i=1;i<=5;i++){
                organizationsRecord.setOrganization( "org"+i );
                organizationsRecord.setInn(20*i );
                organizationsRecord.setChecking( i*100 );
                organizationsRecord.store();
                nomenclaturesRecord.setCode( i*212 );
                nomenclaturesRecord.setName( "nom"+i );
                nomenclaturesRecord.store();
                waybillRecord.setDate( new Date(2000,12,i ) );
                waybillRecord.setId( i );
                waybillRecord.setOrganization( "org"+i );
                waybillRecord.store();
                waybillpositionsRecord.setId( i );
                waybillpositionsRecord.setNomenclature( "nom"+i );
                waybillpositionsRecord.setPrice( i*300 );
                waybillpositionsRecord.setQuantity( 20*i );
                waybillpositionsRecord.insert();

            }

        }catch ( DataAccessException e ){
            System.out.println("Table already been created" );

        } catch ( SQLException e ) {

        }
    }
   @After
    public void flushTables(){
        create.delete( WAYBILLPOSITIONS ).execute();
        create.delete( WAYBILL ).execute();
        create.delete( ORGANIZATIONS ).execute();
        create.delete( NOMENCLATURES ).execute();

   }

    @Test
    public void allRecordsCreate(){
        Assert.assertNull( create.fetchOne( NOMENCLATURES, NOMENCLATURES.NAME.eq( NOMEN_1 ) ) );
       nomenclaturesRecord=create.newRecord( NOMENCLATURES );
       nomenclaturesRecord.setCode( 123 );
       nomenclaturesRecord.setName( NOMEN_1 );
       nomenclaturesRecord.store();
       Assert.assertEquals(create.fetchOne( NOMENCLATURES, NOMENCLATURES.NAME.eq(NOMEN_1 ) ).getCode(), INT );

        Assert.assertNull( create.fetchOne( ORGANIZATIONS, ORGANIZATIONS.ORGANIZATION.eq( ORGAN_1 ) ) );
       organizationsRecord=create.newRecord( ORGANIZATIONS );
       organizationsRecord.setOrganization( ORGAN_1 );
       organizationsRecord.setChecking( INT );
       organizationsRecord.setInn( INT );
       organizationsRecord.store();
       Assert.assertEquals(create.fetchOne( ORGANIZATIONS, ORGANIZATIONS.ORGANIZATION.eq(ORGAN_1 ) ).getChecking(), INT );

        Assert.assertNull( create.fetchOne( WAYBILL, WAYBILL.ID.eq( 113 ) ) );
       waybillRecord=create.newRecord( WAYBILL );
        waybillRecord.setId( 113 );
        waybillRecord.setOrganization( ORGAN_1 );
        waybillRecord.setDate( DATE );
        waybillRecord.store();
       Assert.assertEquals(create.fetchOne( WAYBILL, WAYBILL.ID.eq(113 ) ).getDate(), DATE );

        Assert.assertNull( create.fetchOne( WAYBILLPOSITIONS, WAYBILLPOSITIONS.ID.eq( 113 ) ) );
       waybillpositionsRecord=create.newRecord( WAYBILLPOSITIONS );
       waybillpositionsRecord.setId( 113 );
       waybillpositionsRecord.setQuantity( INT );
       waybillpositionsRecord.setPrice( INT );
       waybillpositionsRecord.setNomenclature( NOMEN_1 );
       waybillpositionsRecord.store();
       Assert.assertEquals(create.fetchOne( WAYBILLPOSITIONS, WAYBILLPOSITIONS.ID.eq(113 ) ).getPrice(), INT );

    }
    @Test
    public  void allRecordsDeleteTest(){
        assertNotEquals(create.fetchOne( WAYBILLPOSITIONS,WAYBILLPOSITIONS.ID.eq( 1 ) ),null );
        waybillpositionsRecord=create.fetchOne( WAYBILLPOSITIONS,WAYBILLPOSITIONS.ID.eq( 1 ));
        waybillpositionsRecord.delete();
        Assert.assertNull( create.fetchOne( WAYBILLPOSITIONS, WAYBILLPOSITIONS.ID.eq( 1 ) ) );
        assertNotEquals(create.fetch( WAYBILLPOSITIONS),null );

        assertNotEquals(create.fetchOne( WAYBILL,WAYBILL.ID.eq( 1 ) ),null );
        waybillRecord=create.fetchOne( WAYBILL,WAYBILL.ID.eq( 1 ));
        waybillRecord.delete();
        Assert.assertNull( create.fetchOne( WAYBILL, WAYBILL.ID.eq( 1 ) ) );
        assertNotEquals(create.fetch( WAYBILL),null );

        assertNotEquals(create.fetchOne( NOMENCLATURES,NOMENCLATURES.NAME.eq( NOM_1 ) ),null );
        nomenclaturesRecord=create.fetchOne( NOMENCLATURES,NOMENCLATURES.NAME.eq( NOM_1));
        nomenclaturesRecord.delete();
        Assert.assertNull( create.fetchOne( NOMENCLATURES, NOMENCLATURES.NAME.eq( NOM_1 ) ) );
        assertNotEquals(create.fetch( NOMENCLATURES),null );

        assertNotEquals(create.fetchOne( ORGANIZATIONS,ORGANIZATIONS.ORGANIZATION.eq( ORG_1 ) ),null );
        organizationsRecord=create.fetchOne( ORGANIZATIONS,ORGANIZATIONS.ORGANIZATION.eq( ORG_1 ) );
        organizationsRecord.delete();
        Assert.assertNull( create.fetchOne( ORGANIZATIONS, ORGANIZATIONS.ORGANIZATION.eq( ORG_1 ) ) );
        assertNotEquals(create.fetch( ORGANIZATIONS),null );

    }
    @Test
    public  void allRecordsReadTest(){
        nomenclaturesRecord=create.fetchOne(NOMENCLATURES,NOMENCLATURES.NAME.eq( NOM_1 ) );
        Assert.assertEquals( nomenclaturesRecord.getCode(),(Integer)212 );

        organizationsRecord=create.fetchOne(ORGANIZATIONS,ORGANIZATIONS.ORGANIZATION.eq( ORG_1 ) );
        Assert.assertEquals( organizationsRecord.getChecking(),(Integer)100 );

        waybillRecord=create.fetchOne( WAYBILL,WAYBILL.ID.eq( 1 ));
        Assert.assertEquals( waybillRecord.getDate(), DATE1 );

        waybillpositionsRecord=create.fetchOne( WAYBILLPOSITIONS,WAYBILLPOSITIONS.ID.eq( 1 ) );
        Assert.assertEquals( waybillpositionsRecord.getPrice(),(Integer) 300 );


    }
    @Test
    public  void allRecordsUpdateTest(){
        Assert.assertEquals(create.fetchOne( WAYBILLPOSITIONS,WAYBILLPOSITIONS.ID.eq( 1 ) ).getPrice(),(Integer)300);
        waybillpositionsRecord=create.fetchOne( WAYBILLPOSITIONS,WAYBILLPOSITIONS.ID.eq( 1 ) );
        waybillpositionsRecord.setPrice( 320 );
        waybillpositionsRecord.store();
        Assert.assertEquals(create.fetchOne( WAYBILLPOSITIONS,WAYBILLPOSITIONS.ID.eq( 1 ) ).getPrice(),(Integer)320);

        Assert.assertEquals(create.fetchOne( WAYBILL,WAYBILL.ID.eq( 1 ) ).getDate(),DATE1);
        waybillRecord=create.fetchOne( WAYBILL,WAYBILL.ID.eq( 1 ) );
        waybillRecord.setDate( DATE );
        waybillRecord.store();
        Assert.assertEquals(create.fetchOne( WAYBILL,WAYBILL.ID.eq( 1 ) ).getDate(),DATE);

        Assert.assertEquals(create.fetchOne( ORGANIZATIONS,ORGANIZATIONS.ORGANIZATION.eq( ORG_1 ) ).getChecking(),(Integer)100);
        organizationsRecord=create.fetchOne( ORGANIZATIONS,ORGANIZATIONS.ORGANIZATION.eq( ORG_1 ) );
        organizationsRecord.setChecking( 120 );
        organizationsRecord.store();
        Assert.assertEquals(create.fetchOne( ORGANIZATIONS,ORGANIZATIONS.ORGANIZATION.eq( ORG_1 ) ).getChecking(),(Integer)120);

        Assert.assertEquals(create.fetchOne( NOMENCLATURES,NOMENCLATURES.NAME.eq( NOM_1 ) ).getCode(),(Integer)212);
        nomenclaturesRecord=create.fetchOne( NOMENCLATURES,NOMENCLATURES.NAME.eq( NOM_1 )  );
        nomenclaturesRecord.setCode( 322 );
        nomenclaturesRecord.store();
        Assert.assertEquals(create.fetchOne( NOMENCLATURES,NOMENCLATURES.NAME.eq( NOM_1 ) ).getCode(),(Integer)322);

    }


    }


