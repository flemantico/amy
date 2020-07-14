package com.amy.serversecurity.util.enumerators;

import java.util.HashMap;
import java.util.Map;

public enum UnmEnviroment 
{
    PRD("https://prod.amy.com:1088/"), 
    SIT("https://sit.amy.com:2019/"), 
    CIT("https://cit.amy.com:8080/"), 
    DEV("https://dev.amy.com:2132/");
 
    private String url;
 
    UnmEnviroment(String envUrl) {
        this.url = envUrl;
    }
 
    public String getUrl() {
        return url;
    }

//****** Reverse Lookup Implementation************//
 
    //Lookup table
    private static final Map<String, UnmEnviroment> lookup = new HashMap<>();
  
    //Populate the lookup table on loading time
    static
    {
        for(UnmEnviroment env : UnmEnviroment.values())
        {
            lookup.put(env.getUrl(), env);
        }
    }
  
    //This method can be used for reverse lookup purpose
    public static UnmEnviroment get(String url) 
    {
        return lookup.get(url);
    }

}

/*
2. Iterar constantes enum
Para iterar sobre la lista enum , use el values()método en tipo enum que devuelve todas las constantes enum en una matriz.

//Get all enums
for(Environment env : Environment.values())
{
    System.out.println(env.name() + " :: "+ env.getUrl());
}
Salida:

PROD :: https://prod.domain.com:1088/
SIT :: https://sit.domain.com:2019/
CIT :: https://cit.domain.com:8080/
DEV :: https://dev.domain.com:21323/
3. Java enum a String
Para obtener un valor de enumeración único (p. Ej., Obtener la URL de la constante de enumeración), use el método de valor que creó.

//Using enum constant reference
String prodUrl = Environment.PROD.getUrl();
 
System.out.println(prodUrl);
Salida:

https://prod.domain.com:1088/
4. Obtenga enum por nombre - parámetro de cadena enum
Si desea obtener una enumeración constante utilizando su nombre, utilice el valueOf()método.

//First get enum constant reference from string
Environment sitUrl = Environment.valueOf("SIT");
 
System.out.println(sitUrl.getUrl());
Salida:

https://sit.domain.com:2019/




Para usar esta búsqueda inversa, en el enum.get()método de uso del código de la aplicación .

//Get enum constant by string - Reverse Lookup
String url = "https://sit.domain.com:2019/";
 
Environment env = Environment.get(url);
 
System.out.println(env);

Salida:
SIT

*/