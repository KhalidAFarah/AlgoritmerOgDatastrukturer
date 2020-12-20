package eksempler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.jar.JarEntry;

public class HashMap {
    public static void main(String[] args){

        ArrayList<LinkedList<Integer>> hash_map = new ArrayList<>(7);

        for(int i = 0; i < 5; i++){
            hash_map.add(new LinkedList<>());
        }

        String[] string = new String[5];
        string[0]  = "hei";
        string[1]  = "på deg";
        string[2]  = "peder";
        string[3]  = "test";
        string[4]  = "hallo";

        for(int i = 0; i < hash_map.size(); i++){
            int hash = hash(string[i]);
            int hash_map_index = compute_hash_map_index(hash, hash_map.size());
            System.out.println("Legger inn: " + string[i] + " med hash: " + hash);
            hash_map.get(hash_map_index).add(hash);
        }


        int hash = hash("test");
        int hashMapIndex = compute_hash_map_index(hash, hash_map.size());
        System.out.println(hash_map.get(hashMapIndex));

    }

    static int compute_hash_map_index(int hash, int hash_map_size){
        return hash % hash_map_size;
    }

    static int hash(String data) {
        int hash = 0;

        //sum av alle bokstaver (ikke optimalt!).
        //for(int i = 0; i < data.length(); i++){
        //    hash = hash + (int) data.charAt(i);
        //}
        //(((i*31) + e)*31 + h)*31
        for(int i= data.length() -1 ; i >= 0; i--){
            char c = data.charAt(i);
            hash = (hash + c)*31;
        }
        if(hash< 0){
            hash = -hash;
        }
        return hash;
    }
}
