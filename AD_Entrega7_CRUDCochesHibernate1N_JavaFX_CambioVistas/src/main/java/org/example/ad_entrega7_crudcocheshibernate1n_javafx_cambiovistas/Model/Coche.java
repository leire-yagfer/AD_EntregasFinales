package org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity //indica que esta clase es una entidad mapeada a una tabla en la base de datos
@Table(name = "coches") //especifica el nombre de la tabla en la base de datos
public class Coche implements Serializable{

    //ATRIBUTOS
    @Id //indica que matricula es la clave primaria de la entidad
    @GeneratedValue(strategy = IDENTITY) // es autoincrement --> genera un id
    @Column(name="id")
    private int id;

    @Column(name = "matricula")
    private String matricula;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "tipo")
    private String tipo;


    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL, orphanRemoval = true) //indica que un coche (one) puede tener múltiples multas (many)
    private List<Multa> multas;


    //CONSTRUCTOR
    public Coche() {
    }

    public Coche(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }


    //GETTER Y SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}//class