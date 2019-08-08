/*
 * This file is generated by jOOQ.
 */
package db.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


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
public class Nomenclatures implements Serializable {

    private static final long serialVersionUID = -400445827;

    private final String  name;
    private final Integer code;

    public Nomenclatures(Nomenclatures value) {
        this.name = value.name;
        this.code = value.code;
    }

    public Nomenclatures(
        String  name,
        Integer code
    ) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Nomenclatures (");

        sb.append(name);
        sb.append(", ").append(code);

        sb.append(")");
        return sb.toString();
    }
}