/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.q38_51.model;

/**
 *
 * @author LABS-ESPE
 */
public class G implements H{        //1

    @Override
    public void m(J j) {           //1
        System.out.println("method m of class G is using an ojbect j->" + j);
    }

    @Override 
    public J m() {
        return new J();
    }
    
    
}
