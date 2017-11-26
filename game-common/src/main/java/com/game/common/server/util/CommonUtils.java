package com.game.common.server.util;

import org.apache.commons.lang3.StringUtils;

import com.game.common.server.config.Constants;

/**
 * @author tangjp
 *
 */
public class CommonUtils {
	
	/**
     * 判断 serverId中是否包含当前运行的服务器.
     * serverId的可以有以下格式:
     * 1.  单个server id : 2
     * 2.  表示所有服     : all
     * 3.  表示一组服     : 1,2,4,6-10
     * @param serverId
     * @return
     */
	public static boolean containCurrentServerId(String serverId){
		return isValidServerId(serverId,",");
	}
	
	public static boolean isValidServerId(String serverId, String separator) {
        if(StringUtils.isBlank(serverId)){
            return false;
        }
        if ("all".equalsIgnoreCase(serverId)) {
            return true;
        } else if (serverId.contains(separator)) {
            String[] serverArr = StringUtils.split(serverId, separator);
            for(String serverStr: serverArr){
                String[] server = StringUtils.split(serverStr, "-");
                if (server.length == 1) {
                    if (Constants.SERVER_ID == Integer.parseInt(server[0])) {
                       return true;
                    }
                } else {
                    if (Constants.SERVER_ID >= Integer.parseInt(server[0]) && Constants.SERVER_ID <= Integer.parseInt(server[1])) {
                        return true;
                    }
                }
            }
        } else if ("close".equalsIgnoreCase(serverId)) {
			return false;
		}
		return false;
    }

}
