/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsi.helios.crypto;

import java.io.File;
import javax.crypto.Cipher;

/**
 *
 * @author robert
 */
public interface CryptoCipher {
    public void encrypt(String key, File inputFile, File outputFile);
    public void decrypt(String key, File inputFile, File outputFile); 
}
