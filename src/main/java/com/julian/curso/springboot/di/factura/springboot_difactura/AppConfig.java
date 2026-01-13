package com.julian.curso.springboot.di.factura.springboot_difactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.julian.curso.springboot.di.factura.springboot_difactura.models.Item;
import com.julian.curso.springboot.di.factura.springboot_difactura.models.Product;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

    @Bean
    List<Item> itemsInvoice(){

        Product p1 = new Product("Camara Sony", 800);
        Product p2 = new Product("Reloj  Samsung",1200);
        List<Item> items = Arrays.asList(new Item(p1,2),new Item(p2,4)); // Lista inmutable
        return items;
    }

    @Bean("default") // Definimos un nombre para el bean como "default" para usar en el qualifier en Invoice
    //@Primary
    List<Item> itemsInvoiceOffice(){
        Product p1 = new Product("Monitor MSI", 3400);
        Product p2 = new Product("Silla de Oficina",569);
        Product p3 = new Product("Escritorio", 124);
        Product p4 = new Product("Impresora HP",700);
        List<Item> items = Arrays.asList(new Item(p1,1),new Item(p2,6),new Item(p3,2),new Item(p4,3)); // Lista inmutable
        return items;
    }
}
