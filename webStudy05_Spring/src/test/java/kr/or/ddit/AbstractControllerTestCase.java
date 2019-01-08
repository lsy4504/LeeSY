package kr.or.ddit;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@testCon
public class AbstractControllerTestCase {
	@Inject
	protected WebApplicationContext container;
	protected MockMvc mockMvc;
	@Before
	
	public void setUp() throws Exception{
		mockMvc=MockMvcBuilders.webAppContextSetup(container).build();
	}
	
}
