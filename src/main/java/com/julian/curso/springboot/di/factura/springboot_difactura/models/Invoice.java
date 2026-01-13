package com.julian.curso.springboot.di.factura.springboot_difactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class Invoice {


    @Autowired
    private Client client;

    @Value("${invoice.descriptionoffice}")
    private String description;

    @Autowired
    @Qualifier("default") // Inyecta la lista de items con el nombre del bean "default" de AppConfig
    private List<Item> items;

    @PostConstruct // Metodo que se ejecuta despues del constructor y la inyeccion de dependencias 
    public void init(){
        System.out.println("Creando el componente de la Invoice");
        System.out.println(client.getName() + " " + client.getLastname());
        client.setName(client.getName().concat(" Alexander"));
        
    }
    
    @PreDestroy // Metodo que se ejecuta antes de destruir el bean
    public void destroy(){
        System.out.println("Destruyendo el componente de la Invoice");
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal(){

        // Una forma de tener el total de la factura con un for tradicional
        /*
        int total = 0;
        for (Item item : items) {
            total += item.getImporte();
        }*/

        // Otra forma usando programacion funcional con streams y un map reduce
        int total = items.stream().
        map(item -> item.getImporte()).
        reduce(0,(sum, importe) -> sum + importe);

        // Otra forma usando programacion funcional con streams y un reduce directo
        // int total = items.stream().reduce(0,(sum, item) -> sum + item.getImporte(), Integer::sum);



        return total;
    }


}
