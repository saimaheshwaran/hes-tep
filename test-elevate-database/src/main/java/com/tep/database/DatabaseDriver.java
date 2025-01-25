package com.tep.database;

import com.tep.database.config.MongoDB;
import com.tep.database.config.MySqlDB;
import com.tep.database.config.SqlDB;
import com.tep.database.dml.Retrieve;
import com.tep.utilities.Constants;
import com.tep.utilities.PropUtils;


public class DatabaseDriver {

    private MongoDB mongoDB;
    private MySqlDB mySqlDB;
    private SqlDB sqlDB;
    private PropUtils dbProps;
    public Retrieve retrive;
    public String DBType;


    public void getConnection() {
        dbProps = new PropUtils(Constants.DB_PROP_PATH);
        DBType = dbProps.get("DBType");
        switch (DBType.toUpperCase()) {
            case "MONGODB":
                mongoDB = new MongoDB();
                initializeDriver(mongoDB.connectDatabase(dbProps.get("MongoDB_ConnectionString"), dbProps.get("MongoDB_DataBase")), DBType);
                break;
            case "MYSQL":
                mySqlDB = new MySqlDB();
                initializeDriver(mySqlDB.connectDatabase(dbProps.get("MySql_ConnectionString"), dbProps.get("UserName") + "-" + dbProps.get("Password")), DBType);
                break;
            case "SQL":
                sqlDB = new SqlDB();
                initializeDriver(sqlDB.connectDatabase(dbProps.get("Sql_ConnectionString"), dbProps.get("SQLUserName") + "-" + dbProps.get("SQLPassword")), DBType);
                break;
        }
    }

    public void initializeDriver(Object obj, String DBType) {
        retrive = new Retrieve(obj, DBType);
    }


}
