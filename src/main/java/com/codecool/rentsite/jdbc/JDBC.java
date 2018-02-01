package com.codecool.rentsite.jdbc;

import java.util.ArrayList;
import java.util.List;

public class JDBC {
private List returnValues = new ArrayList();

    public List getReturnValues() {
        returnValues.add(1);
        return returnValues;
    }
}
