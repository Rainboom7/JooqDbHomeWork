
import db.tables.Waybill;
import db.tables.Waybillpositions;
import db.tables.records.NomenclaturesRecord;
import db.tables.records.OrganizationsRecord;
import db.tables.records.WaybillRecord;
import db.tables.records.WaybillpositionsRecord;
import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

import static db.Tables.WAYBILL;
import static db.Tables.WAYBILLPOSITIONS;
import static db.tables.Nomenclatures.NOMENCLATURES;
import static db.tables.Organizations.ORGANIZATIONS;
import static org.jooq.impl.DSL.sum;


@SuppressWarnings({"unchecked", "Duplicates", "deprecation"})
public final class Application {

    public static void main(@NotNull String[] args) {
       Flyway flyway=Flyway.configure().dataSource("jdbc:postgresql://localhost:3600/test","postgres","rainbow7" ).load();
       flyway.clean();
       flyway.migrate();

    try( Connection connection= DriverManager.getConnection( "jdbc:postgresql://localhost:3600/test","postgres","rainbow7" ) )
    {
        DSLContext create = DSL.using( connection, SQLDialect.POSTGRES_10 );
        taskPreparations( create );
     taskThreePointOne( create );
     taskThreePointTwo( create );
       taskThreePointThree( create );
        taskThreePointFour( create );
     taskThreePointFive( create );

    }
    catch(SQLException e){
        e.printStackTrace();
        }
    }

     private static void taskPreparations(@NotNull DSLContext create) {
       NomenclaturesRecord nomenclaturesRecord = create.newRecord( NOMENCLATURES );
      OrganizationsRecord  organizationsRecord=create.newRecord( ORGANIZATIONS );
        WaybillRecord waybillRecord = create.newRecord( Waybill.WAYBILL );
        WaybillpositionsRecord waybillpositionsRecord=create.newRecord( Waybillpositions.WAYBILLPOSITIONS );
        organizationsRecord.delete();
        int id=1;
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
            for (int j=1;j<6;j++){
                waybillpositionsRecord.setId( id );
            waybillpositionsRecord.setWaybillid( i );
            waybillpositionsRecord.setNomenclature( "nom"+i );
            waybillpositionsRecord.setPrice( j*300 );
            waybillpositionsRecord.setQuantity( 20*j*i);
            waybillpositionsRecord.store();
            id++;}

        }
    }

    private static void taskThreePointFive(@NotNull DSLContext create) {
        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter the first date of the interval(yyyy-mm-dd)" );
        LocalDate firstDate = LocalDate.parse( scanner.next());
        System.out.println("Enter the second date of the interval (yyyy-mm-dd)" );
        LocalDate secondDate = LocalDate.parse( scanner.next());
        Table idAndOrganizationsTable=create.select(WAYBILL.ID,WAYBILL.ORGANIZATION ).from( WAYBILL ).where( WAYBILL.DATE.between(java.sql.Date.valueOf(firstDate),java.sql.Date.valueOf(secondDate) ) ).groupBy(WAYBILL.ID ).asTable();
        Table stated=create.select(idAndOrganizationsTable.field( 1 ),WAYBILLPOSITIONS.NOMENCLATURE ).from( idAndOrganizationsTable,WAYBILLPOSITIONS ).where(idAndOrganizationsTable.field( 0 ).equal( WAYBILLPOSITIONS.WAYBILLID )).asTable();
        Result<Record1<String>> statedResult=  create.select(WAYBILL.ORGANIZATION,stated.field( 1 )).from( WAYBILL.leftJoin( stated ).on( WAYBILL.ORGANIZATION.eq( stated.field( 0 ) ) )).fetch();
        for (Record record:statedResult) {
            System.out.println(record.getValue( 0 )+"  "+record.getValue( 1 ) );
        }
    }

    private static void taskThreePointFour(@NotNull DSLContext create) {

        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter the first date of the interval(yyyy-mm-dd)" );
        LocalDate firstDate = LocalDate.parse( scanner.next());
        System.out.println("Enter the second date of the interval (yyyy-mm-dd)" );
        LocalDate secondDate = LocalDate.parse( scanner.next());
        Table tempTable=create.select(WAYBILL.ID,WAYBILL.DATE ).from( WAYBILL ).where( WAYBILL.DATE.between(java.sql.Date.valueOf(firstDate),java.sql.Date.valueOf(secondDate) ) ).groupBy(WAYBILL.ID ).asTable();
        Table tempResult =  create.select(sum(WAYBILLPOSITIONS.QUANTITY).as( "quaSum"),sum(WAYBILLPOSITIONS.PRICE).as( "priSum" ),tempTable.field( 1 )).from(tempTable,WAYBILLPOSITIONS ).where(WAYBILLPOSITIONS.WAYBILLID.eq( tempTable.field( 0 ) )  ).groupBy( tempTable.field( 1 ) ).orderBy( tempTable.field( 1 ) ).asTable();
    Result<Record> result=create.select( sum(tempResult.field( "priSum" )).divide(sum(tempResult.field( "quaSum" )))).from(tempResult).fetch();
    for (Record record:result){
        System.out.println(record.getValue( 0 ) );
    }

    }

    private static void taskThreePointThree(@NotNull DSLContext create) {
        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter the first date of the interval(yyyy-mm-dd)" );
        LocalDate firstDate = LocalDate.parse( scanner.next());
        System.out.println("Enter the second date of the interval (yyyy-mm-dd)" );
        LocalDate secondDate = LocalDate.parse( scanner.next());
        Table tempTable=create.select(WAYBILL.ID,WAYBILL.DATE ).from( WAYBILL ).where( WAYBILL.DATE.between(java.sql.Date.valueOf(firstDate),java.sql.Date.valueOf(secondDate) ) ).groupBy(WAYBILL.ID ).asTable();
        Result<Record> result =  create.select(sum(WAYBILLPOSITIONS.QUANTITY).as( "quaSum"),sum(WAYBILLPOSITIONS.PRICE).as( "priSum" ),tempTable.field( 1 )).from(tempTable,WAYBILLPOSITIONS ).where(WAYBILLPOSITIONS.WAYBILLID.eq( tempTable.field( 0 ) ) ).groupBy( tempTable.field( 1 ) ).orderBy( tempTable.field( 1 ) ).fetch();
        for (Record record: result
             ) {
            System.out.println("quantity: "+record.getValue( 0 )+
                    " revenue: "+ record.getValue( 1 )+
                    " date: "+ record.getValue( 2 ));

        }
    }


    private static void taskThreePointOne(@NotNull DSLContext create) {
       Table idAndSumTable = create.select(sum(WAYBILLPOSITIONS.QUANTITY), WAYBILLPOSITIONS.WAYBILLID ).from( WAYBILLPOSITIONS ).groupBy( WAYBILLPOSITIONS.WAYBILLID ).orderBy(sum(WAYBILLPOSITIONS.QUANTITY).desc()).asTable();
       Result<Record1<String>> result=create.select(WAYBILL.ORGANIZATION ).from( WAYBILL,idAndSumTable ).where(WAYBILL.ID.eq( idAndSumTable.field( 1 ) ) ).limit( 10 ).fetch();
       for (Record record:result
           ) {
         System.out.println(record.getValue( 0 ) );
       }

    }
    private static void taskThreePointTwo(@NotNull DSLContext create) {

        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter minimum sum that will be selected" );
        int expectedValue = scanner.nextInt();
        Table idAndSumTable = create.select(sum(WAYBILLPOSITIONS.QUANTITY), WAYBILLPOSITIONS.WAYBILLID ).from( WAYBILLPOSITIONS ).groupBy( WAYBILLPOSITIONS.WAYBILLID ).orderBy( sum(WAYBILLPOSITIONS.QUANTITY).desc()).asTable();
        Result<Record1<String>> result=create.select(WAYBILL.ORGANIZATION ).from( WAYBILL,idAndSumTable ).where(WAYBILL.ID.eq( idAndSumTable.field( 1 ) )).and( idAndSumTable.field( 0).greaterThan( expectedValue)).fetch();
        for (Record record:result
             ) {
            System.out.println(record.getValue( 0 ) );
        }
}


    }

