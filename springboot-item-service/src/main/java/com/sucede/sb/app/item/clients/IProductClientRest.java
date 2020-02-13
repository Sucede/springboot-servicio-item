package com.sucede.sb.app.item.clients;

import org.springframework.cloud.openfeign.FeignClient;

/*spring.application.name del Servicio Productos*/
@FeignClient(name = "products-service", url = "localhost:8001")
public interface IProductClientRest {

}
