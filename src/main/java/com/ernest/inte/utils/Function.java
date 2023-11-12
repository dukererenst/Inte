/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ernest.inte.utils;

import java.util.UUID;

/**
 *
 * @author Ernest
 */
public class Function {
    
    public static String generateId()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
