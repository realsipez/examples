package me.sina.client.rest.product;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("PRODUCT")
public interface ProductClient {
}
