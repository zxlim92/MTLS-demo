## MTLS using **Self Signed Certificates** on 1 endpoint of server


### Features implemented
1. MTLS only https://localhost:1234/mtlsData rest is TLS
2. Count of unique clients
3. Front end



### Demo

Server start
![image](https://github.com/user-attachments/assets/70e93696-144e-4829-94b9-39056e699776)

Client Start and data being GET

![image](https://github.com/user-attachments/assets/252f6052-5c49-4a4a-b7d5-cdd77e0ca38f)


Sever FE

![image](https://github.com/user-attachments/assets/ffd236bf-058b-4f49-9aae-4848e6a23961)

Client change Keystore (this will make it look like a second client)

![image](https://github.com/user-attachments/assets/32502286-bbbd-4c46-ae94-ba5f2cd938fd)

- Sever FE
- ![image](https://github.com/user-attachments/assets/12279133-cac1-472f-9ce4-7c8a33ea0c74)


Using Curl to access MTLS protected endpoint 

![image](https://github.com/user-attachments/assets/2c8f60df-8d0b-4663-8623-d74a9c11e64b)

Using Curl to access non-protected endpoint

![image](https://github.com/user-attachments/assets/683a4be1-6f8b-4e3e-911f-acf36e40efeb)






