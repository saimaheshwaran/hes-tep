package com.tep.database;

import com.tep.database.config.MongoDb;
import com.tep.database.config.MySqlDb;
import com.tep.database.config.SqlServerDb;
import com.tep.database.dml.Retrieve;
import com.tep.utilities.Constants;
import com.tep.utilities.PropUtils;


public class DbDriver {

    private MongoDb mongoDB;
    private MySqlDb mySqlDB;
    private SqlServerDb SQLServerDB;
    private PropUtils dbProps;
    public Retrieve retrive;
    public String DBType;


    public void getConnection() {
        dbProps = new PropUtils(Constants.DB_PROP_PATH);
        DBType = dbProps.get("DBType");
        switch (DBType.toUpperCase()) {
            case "MONGODB":
                mongoDB = new MongoDb();
                initializeDriver(mongoDB.connectDatabase(dbProps.get("MongoDB_ConnectionString"), dbProps.get("MongoDB_DataBase")), DBType);
                break;
            case "MYSQL":
                mySqlDB = new MySqlDb();
                initializeDriver(mySqlDB.connectDatabase(dbProps.get("MySql_ConnectionString"), dbProps.get("UserName") + "-" + dbProps.get("Password")), DBType);
                break;
            case "SQL":
                SQLServerDB = new SqlServerDb();
                initializeDriver(SQLServerDB.connectDatabase(dbProps.get("Sql_ConnectionString"), dbProps.get("SQLUserName") + "-" + dbProps.get("SQLPassword")), DBType);
                break;
        }
    }

    public void initializeDriver(Object obj, String DBType) {
        retrive = new Retrieve(obj, DBType);
    }


}
