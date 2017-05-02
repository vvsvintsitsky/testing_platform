package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import javax.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;
import wsvintsitsky.testing_platform.dataaccess.repository.UserRoleRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserRole;

@Repository
public class UserRoleRepositoryImpl extends AbstractDaoImpl<UserRole, Long> implements UserRoleRepository, ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;
	
	protected UserRoleRepositoryImpl() {
		super(UserRole.class);
	}
	
	@PostConstruct
	public void init() throws Exception {
//		JpaTransactionManager txManager = (JpaTransactionManager) applicationContext.getBean("transactionManager");
//		TransactionTemplate tmpl = new TransactionTemplate(txManager);
//        tmpl.execute(new TransactionCallbackWithoutResult() {
//            @Override
//            protected void doInTransactionWithoutResult(TransactionStatus status) {
//                for (UserRole userRole : UserRole.values()) {
//					insert(userRole);
//				}
//            }
//        });
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}

}
