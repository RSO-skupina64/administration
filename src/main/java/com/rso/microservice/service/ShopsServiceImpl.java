package com.rso.microservice.service;

import com.rso.microservice.api.mapper.ShopMapper;
import com.rso.microservice.grpc.ShopServiceGrpc;
import com.rso.microservice.grpc.Shops.ShopGrpc;
import com.rso.microservice.grpc.Shops.ShopsResponse;
import com.rso.microservice.grpc.Shops.ShopsRequest;
import com.rso.microservice.vao.ShopListVAO;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class ShopsServiceImpl extends ShopServiceGrpc.ShopServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(ShopsServiceImpl.class);

    private final ShopService shopService;
    private final ShopMapper shopMapper;

    public ShopsServiceImpl(ShopService shopService, ShopMapper shopMapper) {
        this.shopService = shopService;
        this.shopMapper = shopMapper;
    }


    @Override
    public void getShops(ShopsRequest request, StreamObserver<ShopsResponse> responseObserver) {
        log.info("getShops via grpc protocol");
        ShopListVAO list = shopService.getAllShops();

        Iterable<ShopGrpc> iterable = shopMapper.toModelGrpc(list.getShops());

        ShopsResponse response = ShopsResponse.newBuilder()
                .setCount(list.getCount())
                .addAllShops(iterable)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
