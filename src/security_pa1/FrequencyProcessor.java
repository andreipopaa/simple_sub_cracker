/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security_pa1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author drees
 */
public class FrequencyProcessor {
    
    private File frequencyFile;
    private int[] freq; 
    private double[] frequencies;
    private double characterCount;
    private boolean isCipher;
    
    public FrequencyProcessor(File frequencyFile) {
        this.frequencyFile = frequencyFile;
        this.frequencies = new double[26];
        this.freq = new int[26];
    }
    
    public int[] process(boolean isCipher) {
        this.isCipher = isCipher;
        try {
            BufferedReader br = new BufferedReader(new FileReader(frequencyFile));
            String line;
            while ((line = br.readLine()) != null) {
                char[] lineChars = line.toLowerCase().toCharArray();
                for(char ch : lineChars) {
                    if(ch >= 97 && ch <= 122 ) {                        
                        freq[ch - 97]++;
                        characterCount++;
                    }
                }    
            }
            br.close();
            
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return freq;
    }
    
    public String[] generateSortedFrequnecyChart() {
        int asciiAdd = 97;
        if(isCipher) {
            asciiAdd = 65;
        }
        
        String[] data = new String[26];
        for(int i = 0; i < freq.length; i++) {
            double num =(freq[i]/characterCount)*100;
            frequencies[i] = num;
        }
        
        ArrayList<Double> blahblah = new ArrayList<Double>();
        for(int x = 0; x < 26; x++) {
            blahblah.add(frequencies[x]);
        }
        Arrays.sort(frequencies);

        for(int i = 0; i < freq.length; i++) {
       
            String numFreq = String.format( "%.2f", frequencies[25-i]);
            int charIndex = blahblah.indexOf(frequencies[25-i]);
            String tmp = (char)(charIndex+asciiAdd) + " : " + numFreq + "% \n";
            data[i] = tmp;
        }
        return data;
        
    }
    
    
    // Getters
    public int[] getFrequency() {
        return freq;
    }
    
    public double getCharCount() {
        return characterCount;
    }
    
}
