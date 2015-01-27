/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security_pa1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

/**
 *
 * @author drees
 */
public class Decryptor {
    
    private File cipherFile;
    private int[] mapping;
    
    public Decryptor(File cipher, int[] map) {
        this.cipherFile = cipher;
        this.mapping = map;
    }
    
    public File decrypt() {
        File fout = new File("decrypted.txt");
        
        try {
        
            FileWriter writer = new FileWriter(fout);
            BufferedReader br = new BufferedReader(new FileReader(cipherFile));
            String line;
            while ((line = br.readLine()) != null) {
                String output = "";
                char[] lineChars = line.toCharArray();
                
                for(char ch : lineChars) {
                    if(ch >= 65 && ch <= 90 ) {
                       output += (char)(mapping[ch-65]+97);
                    } else {
                        output += ch;
                    }
                }
                output += "\n";
                writer.write(output);
            }
            
            br.close();
            writer.close();
            
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        
        return fout;
    }
    
    
    
}
