package com.kidcools.testboot.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @ClassName SocketIOConfig
 * @date: 2021.09.08 16:14
 * @Author: zhanghang
 */
@Configuration
public class SocketIOConfig {

	@Value("${socketio.host}")
	private String host;

	@Value("${socketio.port}")
	private Integer port;

	@Value("${socketio.bossCount}")
	private int bossCount;

	@Value("${socketio.workCount}")
	private int workCount;

	@Value("${socketio.allowCustomRequests}")
	private boolean allowCustomRequests;

	@Value("${socketio.upgradeTimeout}")
	private int upgradeTimeout;

	@Value("${socketio.pingTimeout}")
	private int pingTimeout;

	@Value("${socketio.pingInterval}")
	private int pingInterval;

	/**
	 * 以下配置在上面的application.properties中已经注明
	 * @return
	 */
	@Bean
	public SocketIOServer socketIOServer() {
		SocketConfig socketConfig = new SocketConfig();
		socketConfig.setTcpNoDelay(true);
		socketConfig.setSoLinger(0);
		com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
		config.setSocketConfig(socketConfig);
		config.setHostname(host);
		config.setPort(port);
		config.setBossThreads(bossCount);
		config.setWorkerThreads(workCount);
		config.setAllowCustomRequests(allowCustomRequests);
		config.setUpgradeTimeout(upgradeTimeout);
		config.setPingTimeout(pingTimeout);
		config.setPingInterval(pingInterval);
		return new SocketIOServer(config);
	}

	/**
	 * 用于扫描netty-socketio的注解，比如 @OnConnect、@OnEvent
	 */
	@Bean
	public SpringAnnotationScanner springAnnotationScanner() {
		return new SpringAnnotationScanner(socketIOServer());
	}

}
