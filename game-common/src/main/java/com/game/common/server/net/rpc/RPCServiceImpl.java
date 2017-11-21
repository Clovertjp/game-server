package com.game.common.server.net.rpc;

import com.game.pb.service.HelloReply;
import com.game.pb.service.HelloRequest;
import com.game.pb.service.RPCServiceGrpc.RPCServiceImplBase;

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
