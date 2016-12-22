package kh.com.kshrd.ipcam.configuration.swagger;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan("kh.com.kshrd.ipcam.repository")
@ConfigurationProperties("application.properties")
@EnableSwagger
public class MybatisConfiguration {

    @Autowired private DataSource datasource;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver}")
    private String driver;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource getDataSource(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/ipcam");//120.136.24.174//IPCAM_MANAGEMENT_DB
        dataSource.setUsername("postgres");//IPCAM_USER
        dataSource.setPassword("Q");
        
        /*dataSource.setUrl("jdbc:postgresql://localhost/cctv");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456");*/
        
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlsessionfactorybean(){
        SqlSessionFactoryBean sqlsessionfactoryBean = new SqlSessionFactoryBean();
        sqlsessionfactoryBean.setDataSource(datasource);
        return sqlsessionfactoryBean;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(datasource);
    }

    public static void main(String [] arg){
        System.out.print(new MybatisConfiguration().driver);
    }
}
