package prior.prior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.tckj.tckj.user.dao")//扫描xml文件时，必须加入
public class PriorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriorApplication.class, args);
    }

}
