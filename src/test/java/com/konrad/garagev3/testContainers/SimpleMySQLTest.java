package com.konrad.garagev3.testContainers;

import static org.junit.Assert.fail;
import static org.junit.Assume.assumeFalse;
import static org.rnorth.visibleassertions.VisibleAssertions.assertEquals;
import static org.rnorth.visibleassertions.VisibleAssertions.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//import org.apache.commons.lang.SystemUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.ContainerLaunchException;
//import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;


public class SimpleMySQLTest {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMySQLTest.class);

//    @ClassRule
//    public static MySQLContainer mysql = new MySQLContainer();
//    @ClassRule
//    public static MySQLContainer mysqlOldVersion = new MySQLContainer("mysql:5.5");
//    @ClassRule
//    public static MySQLContainer mysqlCustomConfig = new MySQLContainer("mysql:5.6")
//            .withConfigurationOverride("somepath/mysql_conf_override");

}
