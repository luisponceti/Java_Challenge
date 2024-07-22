package com.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@ViewScoped
public class ProductBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private Date expirationDate;
    private Date registrationDate;
    private Double price;
    private Boolean active;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("productPU");


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void clear() {
        this.id = null;
        this.description = null;
        this.expirationDate = null;
        this.registrationDate = null;
        this.price = null;
        this.active = null;
    }

    public void save() {
        if (description == null || description.isEmpty() || price == null) {
            System.out.println("Descrição e Preço são obrigatórios.");
            return;
        }

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            Product product = new Product();
            product.setDescription(description);
            product.setExpirationDate(expirationDate);
            product.setRegistrationDate(registrationDate);
            product.setPrice(price);
            product.setActive(active);

            em.persist(product);
            em.getTransaction().commit();

            System.out.println("Produto salvo com sucesso!");

            clear();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
