package me.sina.client.rest.order;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ORDER")
public interface OrderClient {

}
