package DeviceRPC;


import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf.ByteString;
import com.google.protobuf.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.decryptiondevice.DecryptionDeviceProto;
import io.grpc.decryptiondevice.PublicKeyRequest;
//import io.grpc.examples.routeguide.RouteGuideGrpc.RouteGuideBlockingStub;
//import io.grpc.examples.routeguide.RouteGuideGrpc.RouteGuideStub;
import io.grpc.stub.StreamObserver;

import java.awt.Point;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.grpc.decryptiondevice.*;
import com.google.protobuf.ByteString;

public class DeviceClient {
	private static final Logger logger = Logger.getLogger(DeviceClient.class.getName());

	private final ManagedChannel channel;
	//	  private final RouteGuideBlockingStub blockingStub;
	//	  private final RouteGuideStub asyncStub;

	private Random random = new Random();
	//	private TestHelper testHelper;
	public DeviceClient(String host, int port) {
		this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
	}
	/** Construct client for accessing RouteGuide server using the existing channel. */
	public DeviceClient(ManagedChannelBuilder<?> channelBuilder) {
		channel = channelBuilder.build();
		//	  blockingStub = RouteGuideGrpc.newBlockingStub(channel);
		//	  asyncStub = RouteGuideGrpc.newStub(channel);
	}
	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	/**
	 * Blocking unary call example.  Calls getFeature and prints the response.
	 */
	public void getKey() {
		PublicKeyRequest keyRequest = PublicKeyRequest.newBuilder().setNonce(ByteString.copyFromUtf8("gfdgdg")).build();
		Quote quote =  Quote.newBuilder().build();
		ByteString s = quote.getRSAEncryptionKey();
		System.out.println(s);
		
		
//		DecryptionDeviceProto.(request)
	   
		 logger.info("Will try to get Key ...");
		    PublicKeyRequest request = PublicKeyRequest.newBuilder().setNonce(ByteString.copyFromUtf8("nonce")).build();
		    Quote response = Quote.newBuilder().build();
		    ByteString key = response.getQuoteBytes();
		    System.out.println(key);
//		    try {
//		      response = blockingStub.sayHello(request);
//		    } catch (StatusRuntimeException e) {
//		      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
//		      return;
//		    }
//		    logger.info("Greeting: " + response.getMessage());
	}


	



	public static void main(String[] args) throws InterruptedException {

		DeviceClient client = new DeviceClient("localhost", 50051);
		try {
//			// Looking for a valid feature
//			client.getFeature(409146138, -746188906);
//
//			// Feature missing.
//			client.getFeature(0, 0);
//
//			// Looking for features between 40, -75 and 42, -73.
//			client.listFeatures(400000000, -750000000, 420000000, -730000000);
//
//			// Record a few randomly selected points from the features file.
//			client.recordRoute(features, 10);

			// Send and receive some notes.
			client.getKey();

		} finally {
			client.shutdown();
		}
	}



	/**
	 * Only used for unit test, as we do not want to introduce randomness in unit test.
	 */
	@VisibleForTesting
	void setRandom(Random random) {
		this.random = random;
	}

	/**
	 * Only used for helping unit test.
	 */
	@VisibleForTesting
	interface TestHelper {
		/**
		 * Used for verify/inspect message received from server.
		 */
		void onMessage(Message message);

		/**
		 * Used for verify/inspect error received from server.
		 */
		void onRpcError(Throwable exception);
	}
	  private void info(String msg, Object... params) {
		    logger.log(Level.INFO, msg, params);
		}
}
