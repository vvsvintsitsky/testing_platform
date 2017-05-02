package wsvintsitsky.testing_platform.service;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testing-platform-service-context-test.xml" })
public class UserRoleServiceTest {

	@SuppressWarnings("unused")
	@Inject
	private UserRoleService userRoleService;
	
	@Before
	public void wipeDatabase() {
//		userRoleService.deleteAll();
	}
	
	@Test
	public void testInsertUserRole() {
//		Long userRoleSize = new Long(UserRole.values().length);
//		Assert.assertEquals(userRoleSize, userRoleService.count());
	}
}
