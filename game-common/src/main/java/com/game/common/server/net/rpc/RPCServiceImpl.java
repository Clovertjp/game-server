package com.game.common.server.net.rpc;

import com.game.pb.server.rpc.RPCServiceGrpc.RPCServiceImplBase;
import com.game.pb.server.rpc.hello.HelloReply;
import com.game.pb.server.rpc.hello.HelloRequest;

import io.grpc.stub.StreamObserver;

/**
 * @author tangjp
 *
 */
public class RPCServiceImpl extends RPCServiceImplBase {
	
	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		// TODO Auto-generated method stub
		super.sayHello(request, responseObserver);
	}

}
