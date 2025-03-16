# Guardians

1. Ejecucion Local
   * Instalar DynamoDb con puerto de ejecucion 8010 (se adjunta un docker-compose)
   * Crear la tabla manuscriptInventory con el siguiente comando
     aws dynamodb create-table --table-name manuscriptInventory --attribute-definitions AttributeName=manuscript,AttributeType=S --key-schema AttributeName=manuscript,KeyType=HASH --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5 --endpoint-url http://localhost:8010

2. EndPoint Ejecucion Local
   * Estado servicio (Peticion tipo GET)
       http://localhost:8080/actuator/health 
   * Analizar Manuscrito (Peticion tipo POST)
       http://localhost:8080/clue
   * Estadisticas (Peticion tipo GET)
        http://localhost:8080/stats

     Nota: No se adjuntan por el momento Endpoint en servicio Cloud.

 3. Firmas
    * Analizar Manuscrito
      {
          "manuscript":[
              "RTHGQW", "XRLORE", "NARURR", "REVRAL", "EGSILE", "BRINDS"
          ]
      }    
