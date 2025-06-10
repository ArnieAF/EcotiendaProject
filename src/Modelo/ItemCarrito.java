/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author user
 */
public class ItemCarrito {
    private Producto producto;
    private int cantidad;
    private float Subtotal;

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { 
        return producto; 
    }
    public int getCantidad() {
        return cantidad; 
    }

    public float getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSubtotal(int Subtotal) {
        this.Subtotal = Subtotal;
    }
    
    
}
