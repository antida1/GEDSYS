/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.utils;

/**
 *
 * @author Robert
 */
public class CryptoException extends Exception {

    public CryptoException() {
        
    }
    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}
