package com.game.common.server.hotswap;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.server.config.Config;
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

/**
 * @author tangjp
 *
 */
public class HotSwapAgentManager {
	private static final Logger logger = LogManager.getLogger(HotSwapAgentManager.class);
	
	private HotSwapAgentManager() {}

    public static void reloadAgent() {
    	String pid = null;
        VirtualMachine vm = null;
        try {
        	pid = getPID();
            vm = VirtualMachine.attach(pid);
        } catch (AttachNotSupportedException e) {
            logger.error("attach " + pid, e);
        } catch (IOException e) {
            logger.error("attach " + pid, e);
        }
        if (vm == null) {
            return;
        }
        try {
            vm.loadAgent(Config.AGENT_JAR);
            vm.detach();
        } catch (AgentLoadException e) {
            logger.error("load agent " + pid, e);
        } catch (AgentInitializationException e) {
            logger.error("load agent " + pid, e);
        } catch (IOException e) {
            logger.error("load agent " + pid, e);
        }
    }

    private static String getPID() {
        String pidAtHost = ManagementFactory.getRuntimeMXBean().getName();
        String pid = StringUtils.split(pidAtHost, '@')[0];
        logger.info("pid is {}", pid);
        return pid;
    }
}
