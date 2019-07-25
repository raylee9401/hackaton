package com.volvocars.shuffl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShufflApplication.class})
@EnableAutoConfiguration(exclude = { CassandraAutoConfiguration.class, CassandraDataAutoConfiguration.class })
public class ShufflApplicationTests {

	@Test
	public void contextLoads() {
	}

}
