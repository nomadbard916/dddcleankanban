package ntut.csie.sslab.account.user.usecase.common;

import ntut.csie.sslab.account.user.framework.main.springboot.web.AccountWebMain;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(basePackages={"ntut.csie.sslab.account"}, excludeFilters= {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value= AccountWebMain.class)})
@EntityScan(basePackages={"ntut.csie.sslab.account"})
@SpringBootApplication
public abstract class JpaApplicationTest {
}
