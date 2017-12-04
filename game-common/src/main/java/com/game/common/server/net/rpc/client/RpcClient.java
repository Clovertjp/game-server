package com.game.common.server.net.rpc.client;

import com.game.pb.server.rpc.RPCServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author tangjp
 *
 */
public class RpcClient {
	
	private String ip;
	private int serverId;
	private int port;
	private ManagedChannel channel;
	private RPCServiceGrpc.RPCServiceFutureStub rpcClientStub;
	
	public RpcClient(String ip, int serverId, int port) {
		this(ManagedChannelBuilder.forAddress(ip, port).usePlaintext(true).build());
		this.ip = ip;
		this.serverId = serverId;
		this.port = port;
	}
	
	public RpcClient(ManagedChannel channel) {
		this.channel = channel;
		rpcClientStub = RPCServiceGrpc.newFutureStub(channel);
	}
	
	public void shutDown() {
		channel.shutdown();
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public ManagedChannel getChannel() {
		return channel;
	}
	public void setChannel(ManagedChannel channel) {
		this.channel = channel;
	}
	public RPCServiceGrpc.RPCServiceFutureStub getRpcClientStub() {
		return rpcClientStub;
	}

}
